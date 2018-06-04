package Component.BlockComponent;

import Component.BlockActionListener.ReductionActionListener;
import Component.BlockBatchModel.BlockTemplateComponent.LstmBlockTemplate;
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
        super();
        stackSizeTextField = new NumberOnlyTextField(1,1,10);
        cellSizeTextField = new NumberOnlyTextField(1,1,10);
        rnnOutputOption=new JComboBox<>(RnnOutputOption.values());
        JPanel flowSubPanel=new JPanel(new FlowLayout(FlowLayout.LEADING,3,2));
        nameLabel = new JLabel(getClass().getSimpleName());
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        keepProbJSlider = new JSlider();

        stackSizeTextField.setPreferredSize(new Dimension(20,20));
        cellSizeTextField.setPreferredSize(new Dimension(20,20));
        JLabel stackLabel=new JLabel("stack");
        JLabel cellLabel=new JLabel("cell");
        stackLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        cellLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        GridLayout layout = new GridLayout(4,1);
        setLayout(layout);
        setSize(200,99);
        width=getWidth();
        add(flowPanel);
        add(keepProbJSlider);
        flowSubPanel.add(stackLabel);
        flowSubPanel.add(stackSizeTextField);
        flowSubPanel.add(cellLabel);
        flowSubPanel.add(cellSizeTextField);
        flowPanel.add(nameLabel);
        flowPanel.add(extendButton);
        flowPanel.add(revertExtendButton);
        add(flowSubPanel);
        add(rnnOutputOption);
        setVisible(true);
    }
    public LstmBlock(LstmBlockTemplate template){
        keepProbJSlider.setValue(template.getKeepProb());
        //TODO 주석풀어야함
        stackSizeTextField.setValue(template.getStackSize());
        // 크기만 늘어났는지 체크
        this.setExtended(template.isExtended());
        this.setReducted(template.isReducted());
        reductButton.removeActionListener(getReductButton().getActionListeners()[0]);
        reductButton.addActionListener(new ReductionActionListener(this));
        setLocation(template.getPositionX(), template.getPositionY());
    }

    @Override
    protected String getBlockAttrStr() {
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
