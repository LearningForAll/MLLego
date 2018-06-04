package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;
import Component.BlockComponent.ClassifierBlock;
import Const.Classifier;

import javax.swing.border.LineBorder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public abstract class BlockTemplate implements Serializable {


    private static final long serialVersionUID = 6529685098267757690L;
    private int positionX;
    private int positionY;
    private String blockType;
    private List<BlockTemplate> previousBlocks = new ArrayList<>();
    private List<BlockTemplate> nextBlocks = new ArrayList<>();
    protected boolean extended;
    protected boolean reducted;

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
        this.extended = block.isBlockJustExtended();
        this.reducted = block.getReducted();

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
    public boolean isNextBlockTemplateConnected(){
        return (this.nextBlocks.size() != 0);
    }
    public boolean isPreviousBlockTemplateConnected(){
        return (this.previousBlocks.size() != 0);
    }
    public boolean isExtended(){
        return extended;
    }
    public boolean isReducted() { return reducted; }
}
