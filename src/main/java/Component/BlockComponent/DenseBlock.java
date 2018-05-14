package Component.BlockComponent;

import Component.BlockBatchModel.BlockTemplateComponent.BlockTemplate;
import Component.BlockBatchModel.BlockTemplateComponent.DenseBlockTemplate;
import Component.NumberOnlyTextField;
import Const.ActivationFunc;

import javax.swing.*;
import java.awt.*;

/**
 * Created by chaebyeonghun on 2018. 3. 24..
 */
public class DenseBlock extends LayerBlock {

    // 레이어 갯수와 아웃풋 차원 입력
    NumberOnlyTextField layerTextField;
    // 인풋 디멘션과 default로 같다 값이.
    NumberOnlyTextField outputDimensionTextField;
    // todo Activation 배치해야함
    public JComboBox<ActivationFunc> activationFunctionCombobox;

    public DenseBlock(){
        super();
        blockName="Dense Block";
        nameLabel = new JLabel(blockName);
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        layerTextField = new NumberOnlyTextField(1, 1, 50);
        //TODO inputDimentsion?
        int tempInputDimension = 30;
        outputDimensionTextField = new NumberOnlyTextField(tempInputDimension, 1, 1000);
        activationFunctionCombobox = new JComboBox<>(ActivationFunc.values());

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

        GridLayout layout=new GridLayout(3,1);
        setLayout(layout);
        setSize(200,75);
        add(flowPanel);
        add(activationFunctionCombobox);
        add(flowSubPanel);
        flowPanel.add(nameLabel);
        flowPanel.add(extendButton);
        setVisible(true);
    }

    public DenseBlock(DenseBlockTemplate blockTemplate){
        this();
        layerTextField.setText(String.valueOf(blockTemplate.getLayerNum()));
        outputDimensionTextField.setText(String.valueOf(blockTemplate.getOutputDim()));
        activationFunctionCombobox.setSelectedItem(blockTemplate.getActivationFunc());

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

    public int getLayerNum(){
        return (int)layerTextField.getValue();
    }

    public int getOutputDim(){
        return (int)outputDimensionTextField.getValue();
    }

    public ActivationFunc getActivationFunction() {
        return (ActivationFunc) activationFunctionCombobox.getSelectedItem();
    }

}
