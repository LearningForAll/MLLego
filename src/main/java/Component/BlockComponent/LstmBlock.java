package Component.BlockComponent;

import Component.NumberOnlyTextField;
import Const.RnnOutputOption;

import javax.swing.*;
import java.awt.*;

/**
 * Created by chaebyeonghun on 2018. 3. 27..
 */
public class LstmBlock extends LayerBlock {

    NumberOnlyTextField stackSizeTextField;
    // 드롭아웃 레이트를 조정할 수 있는 JSlider
    JSlider keepProbJSlider;

    //todo cell size (output)
    NumberOnlyTextField cellSizeTextField;
    //todo rnn output type
    JComboBox<RnnOutputOption> rnnOutputOption;

    public LstmBlock(){
        //super("LSTM Block");
        super();
        blockName="LSTM Block";
        nameLabel = new JLabel(blockName);
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        keepProbJSlider = new JSlider();
        GridLayout layout=new GridLayout(2,1);
        setLayout(layout);
        setSize(200,50);
        add(flowPanel);
        add(keepProbJSlider);
        flowPanel.add(nameLabel);
        setVisible(true);
    }

    @Override
    String getBlockAttrStr() {
        return null;
    }

    @Override
    public boolean isNextBlockConnectable(Block block) {
        return (block instanceof LayerBlock || block instanceof ClassifierBlock);
    }

    @Override
    public boolean isPreviousBlockConnectable(Block block) {
        return (block instanceof InputBlock || block instanceof PreprocessorBlock|| block instanceof LayerBlock);
    }

    @Override
    public boolean isNextBlockConnected() {
        return (nextBlocks.size() != 0);
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return (previousBlocks.size() != 0);
    }

    public int getKeepProb() {
        return keepProbJSlider.getValue();
    }

    public int getStackSize(){
        return Integer.valueOf(stackSizeTextField.getText());
    }

    public int getCellSize(){
        return Integer.valueOf(cellSizeTextField.getText());
    }

    public RnnOutputOption getOutputOption(){
        return (RnnOutputOption)rnnOutputOption.getSelectedItem();
    }
}
