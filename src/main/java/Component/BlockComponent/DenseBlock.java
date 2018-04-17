package Component.BlockComponent;

import Component.NumberOnlyTextField;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by chaebyeonghun on 2018. 3. 24..
 */
public class DenseBlock extends LayerBlock {

    // 레이어 갯수와 아웃풋 차원 입력
    NumberOnlyTextField layerTextField;
    // 인풋 디멘션과 default로 같다 값이.
    NumberOnlyTextField outputDimensionTextField;

    public DenseBlock(int inputDimension){
        super("Dense Block");
        layerTextField = new NumberOnlyTextField(1, 1, 50);
        outputDimensionTextField = new NumberOnlyTextField(inputDimension, 1, 1000);

        JPanel flowSubPanel=new JPanel(new FlowLayout(FlowLayout.LEADING,6,2));
        JLabel layerNumLabel=new JLabel("Num layer");
        JLabel outputDimLabel=new JLabel("     Dimension");
        layerNumLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        outputDimLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        layerTextField.setPreferredSize(new Dimension(20,20));
        outputDimensionTextField.setPreferredSize(new Dimension(20,20));
        flowSubPanel.add(layerNumLabel);
        flowSubPanel.add(layerTextField);
        flowSubPanel.add(outputDimLabel);
        flowSubPanel.add(outputDimensionTextField);

        GridLayout layout=new GridLayout(2,1);
        setLayout(layout);
        setSize(200,50);
        add(flowPanel);
        add(flowSubPanel);
        setVisible(true);
    }

    @Override
    String getBlockAttrStr() {
        return null;
    }


    @Override
    public boolean isNextBlockConnectable(Block block) {
        return (block instanceof LayerBlock);
    }

    @Override
    public boolean isPreviousBlockConnectable(Block block) {
        return (block instanceof InputBlock || block instanceof LayerBlock);
    }

    @Override
    public boolean isNextBlockConnected() {
        return (nextBlocks.size() != 0);
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return (previousBlocks.size() != 0);
    }

    public int getLayerNum(){
        return (int)layerTextField.getValue();
    }

    public int getOutputDim(){
        return (int)outputDimensionTextField.getValue();
    }


}
