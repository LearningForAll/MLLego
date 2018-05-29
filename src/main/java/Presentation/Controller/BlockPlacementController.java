package Presentation.Controller;

import App.MyApp;
import Component.BlockBatchModel.BlockTemplateComponent.*;
import Component.BlockComponent.*;
import Component.BlockObserver.BlockObserver;
import Presentation.View.BlockPlacementPanel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 1..
 */
public class BlockPlacementController implements BlockObserver {

    // singleton pattern
    private static BlockPlacementController instance = new BlockPlacementController();
    static boolean isBlockEventEvoked = false;
    // 패널을 가지고 있음 컨트롤러니까
    BlockPlacementPanel panel;
    List<Block> blocks;


    private BlockPlacementController() {
        blocks = new ArrayList<>();
    }

    public static BlockPlacementController getInstance() {
        return instance;
    }

    public void setPanel(BlockPlacementPanel panel) { this.panel = panel; }

    /**
     * 이 함수로 블록 BlockPlacementController에 블록 추가
     **/
    public void addBlock(Block block) {
        blocks.add(block);
        //항상 스택처럼 쌓일테니까 마지막블록에 등록해줌
        block.setObserver(this);
        panel.addNewBlock(block);
        // 패널에도 블록 추가
    }

    private void addAllBlock() {
        for (Block block : this.blocks) {
            block.setObserver(this);
            panel.addNewBlock(block);
        }
    }

    public void removeBlock(Block block) {
        //블록이 삭제되었을때 extend 버튼 활성화 시키기
        if(block instanceof ExtendableBlock) {
            if (((ExtendableBlock) block).isPreviousBlockConnected()) {
                if (((ExtendableBlock) block).getPreviousBlocks().get(block.getPreviousBlocks().size() - 1) instanceof ExtendableBlock) {
                    ((ExtendableBlock) block).getPreviousBlocks().get(block.getPreviousBlocks().size() - 1).disconnectNextBlock();
                    ((ExtendableBlock) ((ExtendableBlock) block).getPreviousBlocks().get(block.getPreviousBlocks().size() - 1)).activateButton();
                }
            }
            if (((ExtendableBlock) block).isNextBlockConnected()) {
                if (((ExtendableBlock) block).getNextBlocks().get(0) instanceof ExtendableBlock) {

                    ((ExtendableBlock) ((ExtendableBlock) block).getNextBlocks().get(0)).activateButton();
                }
            }
        }
        blocks.remove(block);
        panel.deleteBlock(block);
        // 패널에도 블록 삭제
        block.disconnectForBlock();
    }

    @Override
    public void blinkBlock(Block block) {
        // 현재 드래그 되고있는 블록은 리스트에서 제거하기 위해..
        List<Block> tempBlocks = new ArrayList<>();
        // Complete 복사를 위해서
        //TODO 연결된 블록도 같이 해야함
        tempBlocks.addAll(blocks);
        tempBlocks.remove(block);

        if (block.isNextBlockConnected() && !block.isPreviousBlockConnected()) {
            //다음 블록이 있을때..
            //LOGIC 가장 최 하단의 블록을 구해서 조건을 걸어야함

            for (Block block1 : tempBlocks) {
                if (checkTopCloseBlock(block, block1) && block1.isNextBlockConnectable(block) && !block1.isNextBlockConnected() && block.isPreviousBlockConnectable(block1)) {
                    //만약 아래에서 위로 갔을 경우 즉 드래그 되는 블록이 탑에서 반짝거릴 경우
                    block.blinkTop();
                    block1.blinkBottom();
                    break;
                } else {
                    // 위에서 아래로 갔을 경우
                    if (checkBottomCloseBlock(block.getLastConnectedBlock(), block1) && block1.isPreviousBlockConnectable(block.getLastConnectedBlock()) && !block1.isPreviousBlockConnected() && block.getLastConnectedBlock().isNextBlockConnectable(block1)) {
                        block.getLastConnectedBlock().blinkBottom();
                        block1.blinkTop();
                        break;
                    } else {
                        block.revertBlock();
                        block.getLastConnectedBlock().revertBlock();
                        block1.revertBlock();
                    }
                }
            }


        } else {
            if (block.isPreviousBlockConnected() ||
                    (block instanceof ClassifierBlock && ((ClassifierBlock) block).isAnyBlockConnected())
                    || (block instanceof ExtendableBlock && ((ExtendableBlock) block).isAnyBlockConnected())) {

                if (!block.checkTopBorder()) {
                    //블록이 끊길때
                    block.disconnectBlock();

                }
            } else {

                for (Block block1 : tempBlocks) {
                    if (checkTopCloseBlock(block, block1) && block1.isNextBlockConnectable(block) && !block1.isNextBlockConnected() && block.isPreviousBlockConnectable(block1)) {
                        //아래에서 위로 갔을 경우
                        block.blinkTop();
                        block1.blinkBottom();
                        break;
                    } else if (checkBottomCloseBlock(block, block1) && block1.isPreviousBlockConnectable(block) && !block1.isPreviousBlockConnected() && block.isNextBlockConnectable(block1)) {

                        block.blinkBottom();
                        block1.blinkTop();
                        break;
                    } else {
                        //TODO 오류 찾음 여기서 아마 배열순서때문에 오류가 발생했을 가능성이 농후
                        block.revertBlock();
                        block1.revertBlock();
                    }
                }
            }
        }

    }

    // 드래그가 풀리면 블록을 원래대로 되돌리고
    @Override
    public void revertOrConnectBlock(Block block) {

        List<Block> tempBlocks = new ArrayList<>();
        tempBlocks.addAll(blocks);
        tempBlocks.remove(block);
        //TODO 만약 최하단의 블록이 조건이라면 그에맞는 분기도 필요
        if (block.checkBorder()) {// 먼저 현재 블록의 Border을 체크
            if (block.checkTopBorder()) {// 그다음 위에가 활성화 되었는지 체크
                for (Block block1 : tempBlocks) {// 모든 블록을 검사하면서 바텀이 빛나는 블록이있는지 체크
                    if (block1.checkBottomBorder()) {
                        block1.registerNextBlock(block);
                        block.registerPreviousBlock(block1);
                        // 등록하면 종료
                        break;
                    }
                }
            } else {// bottom이 빛날때
                for (Block block1 : tempBlocks) {
                    if (block1.checkTopBorder()) {
                        block.registerNextBlock(block1);
                        block1.registerPreviousBlock(block);
                        break;
                    }
                }
            }
            //블록 원상태로 복구
            for (Block block1 : blocks) {
                block1.revertBlock();
            }
        } else {
            //드래그 되고있는 블록에 대해 연결된 블록중 마지막 블록이 빛나는지 체크
            if (block.getLastConnectedBlock().checkBorder()) {
                if (!block.checkTopBorder()) {// bottom이 빛날때
                    for (Block block1 : tempBlocks) {
                        if (block1.checkTopBorder()) {
                            block.getLastConnectedBlock().registerNextBlock(block1);
                            block1.registerPreviousBlock(block.getOneNotLastConnectedBlock());
                            break;
                        }
                    }
                }
            }
            //블록 원상태로 복구.
            for (Block block1 : blocks) {
                block1.revertBlock();
            }

        }


    }
    // block 을 기준으로 첫번째 인자가 기준블록이면 기준블록이 아래 그 반대의 경우는 인자 위치를 바꿔주면됌

    // TODO 논리 수정 필요 말이안됌
    private boolean checkTopCloseBlock(Block block, Block block1) {
        //내가드래그 하는 블록이 아래쪽에서 위쪽으로 접근할때
        //block이 드래그하는 블록 block1이 드래그 못하는 블록
        if (block1 instanceof ExtendableBlock) {
            if (((ExtendableBlock) block1).isBlockExtended() || block1.isBlockJustExtended()) {
                if (block1.getX() > block.getX()) {
                    return ((block1.getX() - block.getX() < 50)
                            && (block1.getX() - block.getX() > 0)
                            && (block.getY() + block1.getHeight() - block.getY() > -30)
                            && (block.getY() + block1.getHeight() - block.getY() < 0));
                } else {
                    return ((block.getX() - block1.getX() > 0)
                            && (block.getX() - block1.getX() < block1.getWidth() - block.getWidth() + 50)
                            && (block1.getY() + block1.getHeight() - block.getY() > -30)
                            && (block1.getY() + block1.getHeight() - block.getY() < 0));
                }
            } else {
                if (block1.getX() > block.getX()) {
                    return ((block1.getX() - block.getX() < 50)
                            && (block1.getX() - block.getX() > 0)
                            && (block.getY() + block1.getHeight() - block.getY() > -30)
                            && (block.getY() + block1.getHeight() - block.getY() < 0));
                } else {
                    return ((block.getX() - block1.getX() >= 0)
                            && (block.getX() - block1.getX() < 50)
                            && (block1.getY() + block1.getHeight() - block.getY() > -30)
                            && (block1.getY() + block1.getHeight() - block.getY() < 0));
                }
            }
        }


        if (block1.getX() > block.getX()) {
            return ((block1.getX() - block.getX() < 50)
                    && (block1.getX() - block.getX() > 0)
                    && (block1.getY() + block1.getHeight() - block.getY() > -30)
                    && (block1.getY() + block1.getHeight() - block.getY() < 0));
        } else {
            return ((block.getX() - block1.getX() >= 0)
                    && (block.getX() - block1.getX() < 50)
                    && (block1.getY() + block1.getHeight() - block.getY() > -30)
                    && (block1.getY() + block1.getHeight() - block.getY() < 0));
        }

    }

    private boolean checkBottomCloseBlock(Block block, Block block1) {
        if (block1 instanceof ExtendableBlock) {
            if (((ExtendableBlock) block1).isBlockExtended()) {
                if (block1.getX() > block.getX()) {
                    return ((block1.getX() - block.getX() < 50)
                            && (block1.getX() - block.getX() > 0)
                            && (block.getY() + block.getHeight() - block1.getY() > -30)
                            && (block.getY() + block.getHeight() - block1.getY() < 0));
                } else {
                    return ((block.getX() - block1.getX() > 0)
                            && (block.getX() - block1.getX() < block1.getWidth() - block.getWidth() + 50)
                            && (block.getY() + block.getHeight() - block1.getY() > -30)
                            && (block.getY() + block.getHeight() - block1.getY() < 0));
                }
            } else {
                if (block1.getX() > block.getX()) {
                    return ((block1.getX() - block.getX() < 50)
                            && (block1.getX() - block.getX() > 0)
                            && (block.getY() + block.getHeight() - block1.getY() > -30)
                            && (block.getY() + block.getHeight() - block1.getY() < 0));
                } else {
                    return ((block.getX() - block1.getX() >= 0)
                            && (block.getX() - block1.getX() < 50)
                            && (block.getY() + block.getHeight() - block1.getY() > -30)
                            && (block.getY() + block.getHeight() - block1.getY() < 0));
                }
            }
        }
        if (block1 instanceof ClassifierBlock) {
            if (block1.getX() > block.getX()) {
                return ((block1.getX() - block.getX() < 50)
                        && (block1.getX() - block.getX() > 0)
                        && (block.getY() + block.getHeight() - block1.getY() > -30)
                        && (block.getY() + block.getHeight() - block1.getY() < 0));
            } else {
                return ((block.getX() - block1.getX() > 0)
                        && (block.getX() - block1.getX() < block1.getWidth() - block.getWidth() + 50)
                        && (block.getY() + block.getHeight() - block1.getY() > -30)
                        && (block.getY() + block.getHeight() - block1.getY() < 0));
            }
        }


        if (block1.getX() > block.getX()) {
            return ((block1.getX() - block.getX() < 50)
                    && (block1.getX() - block.getX() > 0)
                    && (block.getY() + block.getHeight() - block1.getY() > -30)
                    && (block.getY() + block.getHeight() - block1.getY() < 0));
        } else {
            return ((block.getX() - block1.getX() >= 0)
                    && (block.getX() - block1.getX() < 50)
                    && (block.getY() + block.getHeight() - block1.getY() > -30)
                    && (block.getY() + block.getHeight() - block1.getY() < 0));
        }

    }

    public void saveBlockBatch(String name) {
        // save 해야할 것
        try {
            //  ".block으로 저장
            try {

                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getStorePath(name)));
                List<BlockTemplate> blockTemplates = convertBlocksToTemplate(blocks);
                updateTemplateConnectionInfo(blockTemplates, blocks);

                oos.writeObject(blockTemplates);
                oos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public void loadBlockBatch(String filePath) {
        try {
            // 직렬화된 객체를 로드해옴.
            List<BlockTemplate> blockTemplateList;
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));

            blockTemplateList = (List<BlockTemplate>) ois.readObject();
            ois.close();

            // 패널의 모든 블록삭제
            this.blocks.clear();
            panel.deleteAllBlock();

            List<Block> tempList = blockTemplatesToBlocks(blockTemplateList);
            this.blocks.addAll(tempList);

            addAllBlock();
            updateBlockConnectionInfo(blockTemplateList, this.blocks);
            for (Block block : this.blocks) {
                if (block instanceof ClassifierBlock) {
                    System.out.println(((ClassifierBlock) block).getxPartBlock());
                    System.out.println(((ClassifierBlock) block).getyPartBlock());
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO 만약 블록이 PlacementController에 있다면 그 배치를 저장하겠냐고 메시지를 띄운다.
    }

    public String getStorePath(String name) {

        //파일 이름을 받아서 파일 전체 저장 경로를 구하는 함수
        File file = new File("");
        return file.getAbsolutePath() + "/blocks/" + MyApp.projectTitle + "/" + name + ".block";
    }


    private List<BlockTemplate> convertBlocksToTemplate(List<Block> blocks) {
        List<BlockTemplate> blockTemplates = new ArrayList();

        for (Block block : blocks) {
            switch (block.getClass().getSimpleName()) {
                case "ClassifierBlock":
                    blockTemplates.add(new ClassifierBlockTemplate(block));
                    break;
                case "ConvolutionLayerBlock":
                    blockTemplates.add(new ConvolutionLayerBlockTemplate(block));
                    break;
                case "DenseBlock":
                    blockTemplates.add(new DenseBlockTemplate(block));
                    break;
                case "XInputBlock":
                    blockTemplates.add(new XInputBlockTemplate(block));
                    break;
                case "YInputBlock":
                    blockTemplates.add(new YInputBlockTemplate(block));
                    break;
                case "LstmBlock":
                    blockTemplates.add(new LstmBlockTemplate(block));
                    break;
                case "ModelBlock":
                    blockTemplates.add(new ModelBlockTemplate(block));
                    break;
                case "PreprocessorBlock":
                    blockTemplates.add(new PreprocessorBlockTemplate(block));
                    break;
                case "PoolingBlock":
                    blockTemplates.add(new PoolingBlockTemplate(block));
                    break;
                case "TrainingBlock":
                    blockTemplates.add(new TrainingBlockTemplate(block));
                    break;
            }
        }

        return blockTemplates;
    }

    public BlockTemplate convertOneBlockToTemplate(Block block) {
        switch (block.getClass().getSimpleName()) {
            case "ClassifierBlock":
                return (new ClassifierBlockTemplate(block));
            case "ConvolutionLayerBlock":
                return (new ConvolutionLayerBlockTemplate(block));
            case "DenseBlock":
                return (new DenseBlockTemplate(block));
            case "LstmBlock":
                return (new LstmBlockTemplate(block));
            case "ModelBlock":
                return (new ModelBlockTemplate(block));
            case "PreprocessorBlock":
                return (new PreprocessorBlockTemplate(block));
            case "PoolingBlock":
                return (new PoolingBlockTemplate(block));
            case "TrainingBlock":
                return (new TrainingBlockTemplate(block));
            case "XInputBlock":
                return (new XInputBlockTemplate(block));
            case "YInputBlock":
                return (new YInputBlockTemplate(block));
        }
        return null;
    }

    // 저장할때
    private void updateTemplateConnectionInfo(List<BlockTemplate> blockTemplates, List<Block> blocks) {
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).isNextBlockConnected()) {
                for (int j = 0; j < blockTemplates.size(); j++) {
                    // 어차피 다음 블록은 하나만 오기때문에 상관없음..
                    if (blockTemplates.get(j).equals(convertOneBlockToTemplate(blocks.get(i)))) {
                        blockTemplates.get(j).setNextBlocks(convertBlocksToTemplate(blocks.get(i).getNextBlocks()));
                    }
                }
            }

            if (blocks.get(i).isPreviousBlockConnected()) {
                for (int j = 0; j < blockTemplates.size(); j++) {

                    if (blockTemplates.get(j).equals(convertOneBlockToTemplate(blocks.get(i)))) {
                        //setNextBlock은 통째로 넘겨주기때문에 break;
                        blockTemplates.get(j).setPreviousBlocks(convertBlocksToTemplate(blocks.get(i).getPreviousBlocks()));

                    }

                }
            }
            //TODO Classifier xpart, ypart 추가해야함

            if (blocks.get(i).getClass().getSimpleName().equals("ClassifierBlock")) {
                for (int j = 0; j < blockTemplates.size(); j++) {
                    if (((ClassifierBlock) blocks.get(i)).getxPartBlock() != null) {
                        //해당 블록의 x
                        if (compareBlock(((ClassifierBlock) blocks.get(i)).getxPartBlock(), blockTemplates.get(j))) {
                            ((ClassifierBlockTemplate) blockTemplates.get(i)).setxPartBlock(blockTemplates.get(j));
                        }
                    }
                    if (((ClassifierBlock) blocks.get(i)).getyPartBlock() != null) {
                        if (compareBlock(((ClassifierBlock) blocks.get(i)).getyPartBlock(), blockTemplates.get(j))) {
                            ((ClassifierBlockTemplate) blockTemplates.get(i)).setyPartBlock(blockTemplates.get(j));
                        }

                    }
                }
            }

        }
    }

    // 불러 올
    private void updateBlockConnectionInfo(List<BlockTemplate> blockTemplates, List<Block> blocks) {
        for (int i = 0; i < blockTemplates.size(); i++) {
            if (blockTemplates.get(i).isNextBlockTemplateConnected()) {
                for (int j = 0; j < blocks.size(); j++) {
                    // 어차피 다음 블록은 하나만 오기때문에 상관없음..
                    if (compareBlock(blocks.get(i), blockTemplates.get(j))) {
                        blocks.get(i).registerInitialNextBlock(blocks.get(getIndexForBlockTemplates(blockTemplates, blockTemplates.get(j).getNextBlocks().get(0))));
                    }
                }
            }
            if (blockTemplates.get(i).isPreviousBlockTemplateConnected()) {
                for (int j = 0; j < blocks.size(); j++) {
                    if (compareBlock(blocks.get(i), blockTemplates.get(j))) {
                        for (int k = 0; k < blockTemplates.get(j).getPreviousBlocks().size(); k++) {
                            blocks.get(i).registerPreviousBlock(blocks.get(getIndexForBlockTemplates(blockTemplates, blockTemplates.get(j).getPreviousBlocks().get(k))));
                        }
                    }
                }
            }

            //TODO Classifier xpart, ypart 추가해야함
            if (blockTemplates.get(i).getClass().getSimpleName().equals("ClassifierBlockTemplate")) {
                if (((ClassifierBlockTemplate) blockTemplates.get(i)).getxPartBlock() != null) {
                    ((ClassifierBlock) blocks.get(i)).setxPartBlock(blocks.get(getIndexForBlockTemplates(blockTemplates, ((ClassifierBlockTemplate) blockTemplates.get(i)).getxPartBlock())));
                }
                if (((ClassifierBlockTemplate) blockTemplates.get(i)).getyPartBlock() != null) {
                    ((ClassifierBlock) blocks.get(i)).setyPartBlock(blocks.get(getIndexForBlockTemplates(blockTemplates, ((ClassifierBlockTemplate) blockTemplates.get(i)).getyPartBlock())));
                }
            }
        }

        initializeAllBlockTypeResize(blocks);

    }

    private List<Block> blockTemplatesToBlocks(List<BlockTemplate> blockTemplates) {
        List<Block> tempBlocks = new ArrayList<>();
        for (BlockTemplate blockTemplate : blockTemplates) {
            String str = blockTemplate.getClass().getSimpleName();
            switch (str) {
                case "ClassifierBlockTemplate":
                    tempBlocks.add(new ClassifierBlock((ClassifierBlockTemplate) blockTemplate));
                    break;
                case "ConvolutionLayerBlockTemplate":
                    tempBlocks.add(new ConvolutionLayerBlock((ConvolutionLayerBlockTemplate) blockTemplate));
                    break;
                case "DenseBlockTemplate":
                    tempBlocks.add(new DenseBlock((DenseBlockTemplate) blockTemplate));
                    break;
                case "XInputBlockTemplate":
                    // 뷰어모드로 들어가야함
                    XInputBlock xInputBlock = new XInputBlock((XInputBlockTemplate) blockTemplate);
                    xInputBlock.setViewerMode();
                    tempBlocks.add(xInputBlock);
                    break;
                case "YInputBlockTemplate":
                    // 뷰어모드로 들어가야함
                    YInputBlock yInputBlock = new YInputBlock((YInputBlockTemplate) blockTemplate);
                    yInputBlock.setViewerMode();
                    tempBlocks.add(yInputBlock);
                    break;
                case "LstmBlockTemplate":
                    tempBlocks.add(new LstmBlock((LstmBlockTemplate) blockTemplate));
                    break;
                case "ModelBlockTemplate":
                    tempBlocks.add(new ModelBlock((ModelBlockTemplate) blockTemplate));
                    break;
                case "PreprocessorBlockTemplate":
                    tempBlocks.add(new PreprocessorBlock((PreprocessorBlockTemplate) blockTemplate));
                    break;
                case "PoolingBlockTemplate":
                    tempBlocks.add(new PoolingBlock((PoolingBlockTemplate) blockTemplate));
                    break;
                case "TrainingBlockTemplate":
                    tempBlocks.add(new TrainingBlock((TrainingBlockTemplate) blockTemplate));
                    break;
                default:
                    break;
            }
        }
        return tempBlocks;
    }


    private boolean compareBlock(Block block, BlockTemplate blockTemplate2) {

        return (block.getX() == blockTemplate2.getPositionX() && block.getY() == blockTemplate2.getPositionY());
    }

    private int getIndexForBlockTemplates(List<BlockTemplate> blockTemplates, BlockTemplate blockTemplate) {
        return blockTemplates.indexOf(blockTemplate);
    }

    private void initializeAllBlockTypeResize(List<Block> blocks) {

        for (Block block : blocks) {
            if (block instanceof ExtendableBlock) {
                ((ExtendableBlock) block).setInitialExtendableBlockSize();
            }
        }
        for (Block block : blocks) {
            if (block instanceof ClassifierBlock) {
                ((ClassifierBlock) block).setInitialClassifierBlockSize();
            }
        }
        for (Block block : blocks) {
            // 크기만 커진 블록들 사이즈 재조정
            if (block.isBlockJustExtended()) {
                block.setSize(block.getPreviousBlocks().get(0).getWidth(), block.getHeight());
            }

        }
    }
}
