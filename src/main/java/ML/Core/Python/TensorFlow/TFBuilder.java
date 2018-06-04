package ML.Core.Python.TensorFlow;

import Component.BlockComponent.*;
import Const.RnnOutputOption;
import ML.Core.MLBuilder;
import ML.Core.ProcessListener;
import Util.CMDExecuteUtil;
import Util.FileUtil;
import Util.StringUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TFBuilder implements MLBuilder {
    ProcessListener processListener = null;
    String recentGenerateTrainingCodeURL = null;
    public List<String> codeList = new ArrayList<>();

    @Override
    public void setProcessListener(ProcessListener processListener) {
        this.processListener = processListener;
    }

    /*
      TODO :: 블록에 맞게 소스코드작성해서 URL 경로에 파일저장.
      TODO :: 블록별 소스코드 작성 모듈 생성해야함
      TODO :: 비동기가 아닌 동기 작업. 따라서 리스너 호출필요 X
      */

    /**
     * @param trainingBlock 트레이닝 블록부터 거꾸로 올라가면서 모든 블록파악 및 코드생성
     *                      양방향 트리 모양이 형성됨
     *                      <p>
     *                      거꾸로 올라가면서 n의 부모를 가질시
     *                      "왼쪽" 우선 순회
     * @return 성공, 실패 반환
     */
    @Override
    public boolean generateCodeFile(TrainingBlock trainingBlock, String name) {
        //resource load
        String curProjectDir = FileUtil.getCurrentProjectDirPath(name);
        if (curProjectDir == null) {
            JOptionPane.showMessageDialog(null, "Model 이름이 중복됩니다", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        recentGenerateTrainingCodeURL = curProjectDir;
        File classifierTemplateFile = FileUtil.resourceLoad("Python/Tensorflow/CodeTemplate/ClassifierTemplate.py");
        File fileReaderFile = FileUtil.resourceLoad("Python/Tensorflow/CodeTemplate/FileReader.py");
        File inferenceTemplateFile = FileUtil.resourceLoad("Python/Tensorflow/CodeTemplate/InferenceTemplate.py");
        File layerGeneratorFile = FileUtil.resourceLoad("Python/Tensorflow/CodeTemplate/LayerGenerator.py");
        File preProcessorFile = FileUtil.resourceLoad("Python/Tensorflow/CodeTemplate/PreProcessor.py");
        File utilsFile = FileUtil.resourceLoad("Python/Tensorflow/CodeTemplate/Utils.py");
        File trainerFile = FileUtil.resourceLoad("Python/Tensorflow/CodeTemplate/Trainer.py");
        File initFile = FileUtil.resourceLoad("Python/Tensorflow/CodeTemplate/__init__.py");
        if (classifierTemplateFile == null
                || fileReaderFile == null
                || inferenceTemplateFile == null
                || layerGeneratorFile == null
                || preProcessorFile == null
                || utilsFile == null
                || trainerFile == null
                || initFile == null)
            throw new RuntimeException("필수 리소스 존재 X");

        //read training block
        ClassifierBlock classifierBlock = trainingBlock.getClassifierBlock();
        if (classifierBlock == null) {
            //throw new NoClassificationException("classification 블록이 없습니다.", trainingBlock);
            JOptionPane.showMessageDialog(null, "classification 블록이 없습니다.", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        Block xPartBlock = classifierBlock.getxPartBlock();
        Block yPartBlock = classifierBlock.getyPartBlock();
        if (xPartBlock == null || yPartBlock == null) {
            // throw new IncompleteBlockException("x 또는 y 파트의 블록이 존재하지않습니다");
            JOptionPane.showMessageDialog(null, "x 또는 y 파트의 블록이 존재하지않습니다", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
        }


        // TODO :: classifier option is mocked.
        ClassifierOption classifierOption = new ClassifierOption(curProjectDir, XInputBlock.getXPath(), YInputBlock.getYPath(), XInputBlock.getInputOptionStr(), YInputBlock.getInputOptionStr(),
                classifierBlock.getClassifier(), trainingBlock.getBatchSize(), trainingBlock.getEpoch(),
                trainingBlock.getLearningRate(), trainingBlock.getOptimizer(), trainingBlock.getValidRatio());
        // generate classifier
        generateClassifier(classifierOption, classifierTemplateFile, curProjectDir + "/" + classifierTemplateFile.getName());

        ///copying files
        FileUtil.fileCopy(utilsFile, curProjectDir + "/" + utilsFile.getName());
        FileUtil.fileCopy(preProcessorFile, curProjectDir + "/" + preProcessorFile.getName());
        FileUtil.fileCopy(layerGeneratorFile, curProjectDir + "/" + layerGeneratorFile.getName());
        FileUtil.fileCopy(fileReaderFile, curProjectDir + "/" + fileReaderFile.getName());
        FileUtil.fileCopy(inferenceTemplateFile, curProjectDir + "/" + inferenceTemplateFile.getName());
        FileUtil.fileCopy(trainerFile, curProjectDir + "/" + trainerFile.getName());
        FileUtil.fileCopy(initFile, curProjectDir + "/" + initFile.getName());
        generateCodeRecursive(yPartBlock);
        generateCodeRecursive(xPartBlock);
        codeList.add("return " + xPartBlock.getUid());
        generateInferenceFile(codeList, curProjectDir + "/" + inferenceTemplateFile.getName());
        return true;
    }

    private void generateInferenceFile(List<String> codeList, String inferenceFilePath) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(inferenceFilePath, true));
            bw.newLine();
            for (String code : codeList) {
                bw.write("        " + code);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * TODO :: 파이썬 실행 모듈과 소켓통신하며 트레이닝함.
     * TODO :: 소켓통신 연결에 성공한경우 true, 실패한경우 false
     * TODO :: 새 쓰래드 생성하여 소켓에서 메세지에 따라 Listener 응답함.
     */
    @Override
    public boolean training() {
        String currentDir = System.getProperty("user.dir");
        String envDir = currentDir + "\\envs";
        String pythonDir = envDir + "\\python.exe";
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    CMDExecuteUtil.cmdExecute(new String[]{"cmd.exe", "/c", pythonDir + " " + recentGenerateTrainingCodeURL + "\\Trainer.py False"});
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();
        return true;
    }


    private void generateClassifier(ClassifierOption classifierOption, File resourceFile, String outputFileName) {
        String[] optionPy = ClassifierOption.getPythonOptionStrings();
        boolean isCheckFinish = false;
        try {
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(resourceFile)));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName)));
            while ((line = br.readLine()) != null) {
                String checkOption;
                if (line.contains("END_SETTING")) isCheckFinish = true;
                if (!isCheckFinish && (checkOption = StringUtil.stringContainsItemFromList(line, optionPy)) != null) {
                    line = line.replace("None", classifierOption.getClassifierOption(checkOption));
                }
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            br.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String generatePreprocessCode(PreprocessorBlock preprocessorBlock, boolean isDataX) {
        String dataType = "";
        if (isDataX) dataType = "x";
        else dataType = "y";
        String funcStr = "self.data_" + dataType + " = ";
        int xSize, ySize;
        switch (preprocessorBlock.getPreprocessorType()) {
            case RGB:
                xSize = preprocessorBlock.getImageSizeX();
                ySize = preprocessorBlock.getImageSizeY();
                funcStr += "pp.image_to_vector(self.data_" + dataType + ",size=[" + xSize + "," + ySize + "])";
                break;
            case BLACK_WHITE:
                xSize = preprocessorBlock.getImageSizeX();
                ySize = preprocessorBlock.getImageSizeY();
                funcStr += "pp.image_to_vector(self.data_" + dataType + ",option=\"BW\",size=[" + xSize + "," + ySize + "])";
                break;
            case ONE_HOT_ENCODING:
                funcStr += "pp.one_hot_encoding(self.data_" + dataType + ")";
                break;
            case DIMENSION_REDUCTION:
                throw new NotImplementedException();
            default:
        }
        return funcStr;
    }

    private String[] generateTensorWhenPreprocessFinish() {
        String[] strings = new String[2];
        strings[0] = "self.tensor_x = pp.make_placeholder_with_batch_space(self.data_x)";
        strings[1] = "self.tensor_y = pp.make_placeholder_with_batch_space(self.data_y)";
        return strings;
    }

    private String generateConvolutionCode(ConvolutionLayerBlock block, Block beforeBlock) {
        String funcStr = block.getUid();
        String beforeUid = "";
        if (beforeBlock == null) throw new IllegalArgumentException("이전 블록이 없는데 conv 를 만드려함");
        if (beforeBlock instanceof PreprocessorBlock) {
            beforeUid = "self.tensor_x";
        } else {
            beforeUid = beforeBlock.getUid();
        }
        funcStr += "=lg.conv2d(" + beforeUid + "," + block.getKernelNum() + ",[" + block.getHorizonKernelSize() + "," + block.getVerticalKernelSize() + "],\"" +
                block.getActivationFunction().toString() + "\",[1,1]," + "\'VALID\'" + ",scope=\'" + block.getUid() + "\')";
        return funcStr;
    }

    private String generateDenseCode(DenseBlock block, Block beforeBlock) {
        String funcStr = block.getUid();

        String beforeUid = "";
        if (beforeBlock instanceof PreprocessorBlock) {
            beforeUid = "self.tensor_x";
        } else {
            beforeUid = beforeBlock.getUid();
        }
        if (block.getNextBlocks().get(0) instanceof ClassifierBlock) {
            //todo 이게맞을까? ui 단에서 강제하는건 어떤가?
            funcStr += "=lg.linear(" + beforeUid + ",np.shape(self.data_y)[1],\"" + block.getActivationFunction().toString() + "\",scope=\'" + block.getUid() + "\')";
        } else {
            funcStr += "=lg.linear(" + beforeUid + "," + block.getOutputDim() + ",\"" + block.getActivationFunction().toString() + "\",scope=\'" + block.getUid() + "\')";
        }
        return funcStr;
    }

    private String generatePoolCode(PoolingBlock block, Block beforeBlock) {
        String funcStr = block.getUid();

        String beforeUid = "";
        if (beforeBlock instanceof PreprocessorBlock) {
            beforeUid = "self.tensor_x";
        } else {
            beforeUid = beforeBlock.getUid();
        }
        funcStr += "=lg.pool(" + beforeUid + ",\'" + block.getPoolingType().toString() + "\',[" + block.getHorizonKernel() + "," + block.getVerticalKernel() + "],["
                + block.getHorizonStride() + "," + block.getVerticalStride() + "],\'" + block.getPaddingOption().toString() + "\',scope=\'" + block.getUid() + "\')";
        return funcStr;
    }

    private String generateRnnCode(LstmBlock block, Block beforeBlock) {
        String funcStr = block.getUid();

        String beforeUid = "";
        if (beforeBlock instanceof PreprocessorBlock) {
            beforeUid = "self.tensor_x";
        } else {
            beforeUid = beforeBlock.getUid();
        }
        funcStr += "=lg.create_stack_rnn(" + beforeUid + "," + block.getCellSize() + "," + block.getStackSize() + ",";
        if (block.getOutputOption().equals(RnnOutputOption.ONLY_END)) {
            funcStr += "True" + "\',scope=\'" + block.getUid() + "\')";
            ;
        } else {
            funcStr += "False" + "\',scope=\'" + block.getUid() + "\')";
            ;
        }
        return funcStr;
    }

    private void generateCodeRecursive(Block block) {
        List<Block> previousBlocks = block.getPreviousBlocks();
        String previousUid = null;
        if (previousBlocks.size() > 1) {
            String concatCode = block.getUid() + "concat = tf.concat([";
            for (Block mBlock : previousBlocks) {
                generateCodeRecursive(mBlock);
                concatCode += mBlock.getUid() + ",";
            }
            concatCode = concatCode.subSequence(0, concatCode.length() - 1).toString();
            concatCode += "],axis=1)";
            //mblock 들 정보 이용해서 dim 합쳐서 block에 잇기
            previousUid = block.getUid() + "concat";
            codeList.add(concatCode);
        }
        if (block.isFirstBlock() && !(block instanceof InputBlock))
            throw new IllegalStateException("맨 첫 블록이 input블록이 아님");
        else if (block.isFirstBlock()) {
            if (block.getNextBlocks().get(0) instanceof ClassifierBlock) {
                if (block instanceof XInputBlock)
                    codeList.add(generateTensorWhenPreprocessFinish()[0]);
                else
                    codeList.add(generateTensorWhenPreprocessFinish()[1]);

                if (block instanceof PreprocessorBlock)
                    if (((PreprocessorBlock) block).isXData())
                        codeList.add(generateTensorWhenPreprocessFinish()[0]);
                    else
                        codeList.add(generateTensorWhenPreprocessFinish()[1]);
            }
            return;
        }
        if (block.isPreviousBlockConnected())
            generateCodeRecursive(block.getPreviousBlocks().get(0));
        Block previousBlock;
        if (previousUid == null) {
            previousBlock = previousBlocks.get(0);
        } else {
            ConcatBlock concatBlock = new ConcatBlock();
            concatBlock.setUid(previousUid);
            previousBlock = concatBlock;
        }
        if ((previousBlock instanceof PreprocessorBlock || previousBlock instanceof InputBlock) && !(block instanceof PreprocessorBlock)) {
            if (previousBlock instanceof InputBlock) {
                if (previousBlock instanceof XInputBlock) {
                    codeList.add(generateTensorWhenPreprocessFinish()[0]);
                    previousBlock.setUid("self.tensor_x");
                } else {
                    codeList.add(generateTensorWhenPreprocessFinish()[1]);
                    previousBlock.setUid("self.tensor_y");
                }
            } else { // previous 가 pre processor 임
                if (((PreprocessorBlock) previousBlock).isXData()) {
                    codeList.add(generateTensorWhenPreprocessFinish()[0]);
                    previousBlock.setUid("self.tensor_x");
                } else {
                    codeList.add(generateTensorWhenPreprocessFinish()[1]);
                    previousBlock.setUid("self.tensor_y");

                }
            }

        }
        // 블록코드생성 switch
        if (block instanceof ConvolutionLayerBlock) {
            codeList.add(generateConvolutionCode((ConvolutionLayerBlock) block, previousBlock));
        } else if (block instanceof DenseBlock) {
            codeList.add(generateDenseCode((DenseBlock) block, previousBlock));
        } else if (block instanceof LstmBlock) {
            codeList.add(generateRnnCode((LstmBlock) block, previousBlock));
        } else if (block instanceof PoolingBlock) {
            codeList.add(generatePoolCode((PoolingBlock) block, previousBlock));
        } else if (block instanceof PreprocessorBlock) {
            if (previousBlock instanceof PreprocessorBlock) {
                ((PreprocessorBlock) block).setXData(((PreprocessorBlock) previousBlock).isXData());
            } else if (previousBlock instanceof InputBlock) {
                if (previousBlock instanceof XInputBlock)
                    ((PreprocessorBlock) block).setXData(true);
                else
                    ((PreprocessorBlock) block).setXData(false);
            }
            codeList.add(generatePreprocessCode((PreprocessorBlock) block, ((PreprocessorBlock) block).isXData()));
        }

        if ((previousBlock instanceof PreprocessorBlock || previousBlock instanceof InputBlock) && block.getNextBlocks().get(0) instanceof ClassifierBlock && block instanceof PreprocessorBlock) {
            if (((PreprocessorBlock) block).isXData())
                codeList.add(generateTensorWhenPreprocessFinish()[0]);
            else
                codeList.add(generateTensorWhenPreprocessFinish()[1]);
        }
    }

    private static void generateModelTestCode(String modelName, String xPath,String yPath) {
        String currentDir = System.getProperty("user.dir");
        String folderDir = currentDir + "/bin/" + modelName;
        folderDir = folderDir.replaceAll("\\\\", "/");
        if (!new File(folderDir).isDirectory()) {
            JOptionPane.showMessageDialog(null, "해당 모델파일이 존재하지않습니다,", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }


        try {
            File testerFile = FileUtil.resourceLoad("Python/Tensorflow/CodeTemplate/Tester.py");
            if (testerFile == null) throw new RuntimeException("Tester 리소스 존재 X");
            File recentFile = new File(folderDir + "/Tester.py");
            if (recentFile.exists()) {
                boolean success = recentFile.delete();
                if (!success) {
                    JOptionPane.showMessageDialog(null, "최근 test 파일을 지우지 못했습니다", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(testerFile)));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(folderDir + "/Tester.py")));
            boolean isCheckFinish = false;
            while ((line = br.readLine()) != null) {
                if (line.contains("END_SETTING")) isCheckFinish = true;
                if (!isCheckFinish && line.contains("x_path")) {
                    line = line.replace("None", "\""+xPath+"\"");
                }
                if (!isCheckFinish && line.contains("y_path")) {
                    line = line.replace("None", "\""+yPath+"\"");
                }
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            br.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void runModelPredictTestBlock(String modelName, String xPath) {
        xPath = xPath.replaceAll("\\\\","/");
        generateModelTestCode(modelName, xPath,"None");
        String currentDir = System.getProperty("user.dir");
        String binDir = currentDir + "\\bin";
        String envDir = currentDir + "\\envs";
        String pythonDir = envDir + "\\python.exe";
        String folderDir = binDir + "\\" + modelName;
        try {
            Runtime.getRuntime().exec(pythonDir + " " + folderDir + "\\Tester.py False");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
