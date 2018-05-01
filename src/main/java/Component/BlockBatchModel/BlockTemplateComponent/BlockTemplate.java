package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;
import Component.BlockComponent.ClassifierBlock;
import Const.Classifier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public abstract class BlockTemplate implements Serializable {


    private static final long serialVersionUID = 6529685098267757690L;
    int positionX;
    int positionY;
    String blockType;
    List<BlockTemplate> previousBlocks;
    List<BlockTemplate> nextBlocks;

    public BlockTemplate(int positionX, int positionY, String blockType) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.blockType = blockType;
        previousBlocks = new ArrayList<>();
        nextBlocks = new ArrayList<>();

    }

    public BlockTemplate(Block block) {
        this.positionX = block.getX();
        this.positionY = block.getY();
        this.blockType = block.getClass().getSimpleName();

    }

    public void setPreviousBlocks(List<BlockTemplate> previousBlocks) {
        this.previousBlocks = previousBlocks;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getBlockType() {
        return blockType;
    }

    public void setBlockType(String blockType) {
        this.blockType = blockType;
    }

    public List<BlockTemplate> getPreviousBlocks() {
        return previousBlocks;
    }

    public List<BlockTemplate> getNextBlocks() {
        return nextBlocks;
    }

    public void setNextBlocks(List<BlockTemplate> nextBlocks) {
        this.nextBlocks = nextBlocks;
    }

    @Override
    public boolean equals(Object obj) {
        return(positionX == ((BlockTemplate)obj).getPositionX() && positionY ==  ((BlockTemplate)obj).getPositionY());
    }

    /* public static List<BlockTemplate> parsingBlocksType(List<Block> blocks){
        List<BlockTemplate> blockTemplates = new ArrayList<>();
        for(Block block: blocks){
            switch (block.getClass().getSimpleName()) {

                case "ClassifierBlock":
                    blockTemplates.add(new ClassifierBlockTemplate(block));
                    break;
                case "ConvolutionLayerBlock":
                    blockTemplates.add(new ConvolutionLayerBlockTemplate(block));
                    break;
                case "DenseBlock":

                    break;
                case "InputBlock":

                    break;
                case "LstmBlock":

                    break;
                case "ModelBlock":

                    break;
                case "PreprocessorBlock":

                    break;
                case "PoolingBlock":

                    break;
                case "TrainingBlock":

                    break;

            }
        }
        return blockTemplates;
    }*/
}
