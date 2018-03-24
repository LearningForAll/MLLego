package Component.BlockComponent;

import Models.Coords;

import javax.swing.*;

public class ClassifierBlock extends ExtendableBlock{

    // 분류기 종류
    String classifier;

    JComboBox optimizerCombobox;
    @Override
    String getBlockAttrStr() {
        return null;
    }
}
