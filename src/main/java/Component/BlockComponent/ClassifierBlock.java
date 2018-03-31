package Component.BlockComponent;

import Const.Classifier;
import Models.Coords;

import javax.swing.*;

public class ClassifierBlock extends ExtendableBlock{

    // 분류기 종류
    Classifier classifier;

    JComboBox optimizerCombobox;
    @Override
    String getBlockAttrStr() {
        return null;
    }

    @Override
    void registerNextBlock(Block block) {

    }

    @Override
    void registerPreviousBlock(Block block) {

    }

    @Override
    void getPoint() {

    }

    @Override
    boolean isNextBlockConnectable(Block block) {
        return false;
    }

    @Override
    boolean isPreviousBlockConnectable(Block block) {
        return false;
    }
}
