package Component.BlockComponent;

import Const.Classifier;
import Models.Coords;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class ClassifierBlock extends ExtendableBlock{
    private Block xPartBlock,yPartBlock;

    // 분류기 종류
    Classifier classifier;

    JComboBox optimizerCombobox;
    @Override
    String getBlockAttrStr() {
        return null;
    }

    @Override
    public boolean isNextBlockConnectable(Block block) {
        return (block instanceof ModelBlock);
    }

    @Override
    public boolean isPreviousBlockConnectable(Block block) {
        return (block instanceof DenseBlock);
    }

    @Override
    public boolean isNextBlockConnected() {
        return false;
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return false;
    }

    public Block getxPartBlock() {
        return xPartBlock;
    }

    public void setxPartBlock(Block xPartBlock) {
        this.xPartBlock = xPartBlock;
    }

    public Block getyPartBlock() {
        return yPartBlock;
    }

    public void setyPartBlock(Block yPartBlock) {
        this.yPartBlock = yPartBlock;

    }

}
