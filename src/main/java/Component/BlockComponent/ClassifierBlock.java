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
    void registerNextBlock() {

    }

    @Override
    void registerPreviousBlock() {

    }
}
