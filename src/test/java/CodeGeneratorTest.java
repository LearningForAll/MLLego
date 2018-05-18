import Component.BlockComponent.*;
import Const.ActivationFunc;
import Const.PreprocessorType;
import ML.Core.Python.TensorFlow.TFBuilder;
import org.junit.Test;

public class CodeGeneratorTest {

    @Test
    public void testCodeGenerate(){
        TFBuilder tfBuilder = new TFBuilder();

        TrainingBlock trainingBlock = new TrainingBlock();
        ClassifierBlock classifierBlock = new ClassifierBlock();
        InputBlock inputX = new InputBlock();
        InputBlock inputY = new InputBlock();
        inputX.setXInput(true);
        inputY.setXInput(false);
        PreprocessorBlock preprocessorY = new PreprocessorBlock();
        preprocessorY.preprocessorTypeCombobox.setSelectedItem(PreprocessorType.ONE_HOT_ENCODING);
        DenseBlock xHidden = new DenseBlock(10);
        xHidden.activationFunctionCombobox.setSelectedItem(ActivationFunc.ACTIVATION_SIGMOID);
        DenseBlock xDenseBlock = new DenseBlock(10);
        xDenseBlock.activationFunctionCombobox.setSelectedItem(ActivationFunc.ACTIVATION_SIGMOID);

        trainingBlock.learningRateTextField.setText("0.001");
        trainingBlock.validRatioTextField.setText("0.3");


        //연결
        trainingBlock.getPreviousBlocks().add(classifierBlock);
        classifierBlock.getNextBlocks().add(trainingBlock);

        classifierBlock.setxPartBlock(xDenseBlock);
        xDenseBlock.getNextBlocks().add(classifierBlock);
        classifierBlock.setyPartBlock(preprocessorY);
        preprocessorY.getNextBlocks().add(classifierBlock);

        xDenseBlock.getPreviousBlocks().add(xHidden);
        xHidden.getNextBlocks().add(xDenseBlock);

        xHidden.getPreviousBlocks().add(inputX);
        inputX.getNextBlocks().add(xHidden);

        preprocessorY.getPreviousBlocks().add(inputY);
        inputY.getNextBlocks().add(preprocessorY);

        tfBuilder.generateCodeFile(trainingBlock);

        for (String code : tfBuilder.codeList){
            System.out.println(code);
        }
    }
}
