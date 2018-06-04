package Component.BlockComponent;

import Component.BlockActionListener.ReductionActionListener;
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
    public JSlider keepProbJSlider;

    public DenseBlock(){
        super();
        nameLabel = new JLabel(getClass().getSimpleName());
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
        JSlider keepProbJSlider=new JSlider();
        layerNumLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        outputDimLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        layerTextField.setPreferredSize(new Dimension(20,20));
        outputDimensionTextField.setPreferredSize(new Dimension(20,20));
        flowSubPanel.add(layerNumLabel);
        flowSubPanel.add(layerTextField);
        flowSubPanel.add(outputDimLabel);
        flowSubPanel.add(outputDimensionTextField);

        GridLayout layout=new GridLayout(4,1);
        setLayout(layout);
        setSize(200,99);
        width = getWidth();
        add(flowPanel);
        add(keepProbJSlider);
        add(activationFunctionCombobox);
        add(flowSubPanel);

        flowPanel.add(nameLabel);
        flowPanel.add(extendButton);
        flowPanel.add(revertExtendButton);
        setVisible(true);
    }

    public DenseBlock(DenseBlockTemplate blockTemplate){
        this();

        layerTextField.setText(String.valueOf(blockTemplate.getLayerNum()));
        outputDimensionTextField.setText(String.valueOf(blockTemplate.getOutputDim()));
        activationFunctionCombobox.setSelectedItem(blockTemplate.getActivationFunc());
        this.setConnectedSize(blockTemplate.getConnectedSize());
        this.setExtendSize(blockTemplate.getExtendSize());
        // 크기만 늘어났는지 체크
        this.setExtended(blockTemplate.isExtended());
        this.setReducted(blockTemplate.isReducted());
        setLocation(blockTemplate.getPositionX(), blockTemplate.getPositionY());
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


    public int getLayerNum(){

        return Integer.parseInt(layerTextField.getText());
    }

    public int getOutputDim(){

        return Integer.parseInt(outputDimensionTextField.getText());
    }

    public ActivationFunc getActivationFunction() {
        return (ActivationFunc)activationFunctionCombobox.getSelectedItem();
    }

    public int getKeepProb() {
        return keepProbJSlider.getValue();
    }
}
