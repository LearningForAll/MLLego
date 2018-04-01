package Component.BlockComponent;

import Const.Classifier;
import Models.Coords;

import javax.swing.*;

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
    boolean isNextBlockConnectable(Block block) {
        return (block instanceof ModelBlock);
    }

    @Override
    boolean isPreviousBlockConnectable(Block block) {
        return (block instanceof DenseBlock);

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
