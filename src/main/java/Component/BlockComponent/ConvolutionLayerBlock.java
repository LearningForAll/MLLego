package Component.BlockComponent;

import Component.BlockBatchModel.BlockTemplateComponent.ConvolutionLayerBlockTemplate;
import Component.NumberOnlyTextField;
import Const.ActivationFunc;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ConvolutionLayerBlock extends LayerBlock {
    int convDimension = 2; // 1D or 2D for convolution

    // 드롭아웃 레이트를 조정할 수 있는 JSlider
    JSlider keepprobJSlider;
    JComboBox<ActivationFunc> activationFunctionCombobox;
    // 필터 개수
    NumberOnlyTextField kernelNumTextField;
    NumberOnlyTextField horizontalKernelSize;
    NumberOnlyTextField verticalKernelSize;

    public ConvolutionLayerBlock(){
        super();
        nameLabel = new JLabel(getClass().getSimpleName());
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        keepprobJSlider = new JSlider();
        // Enum으로 값 ㅁ만듬
        activationFunctionCombobox = new JComboBox<>(ActivationFunc.values());

        // 커널 갯수 default 는 1 최소값도 1 max는 1000 유동적으로 조정
        kernelNumTextField = new NumberOnlyTextField(1, 1, 1000);
        horizontalKernelSize = new NumberOnlyTextField(2, 1, 1000);
        verticalKernelSize = new NumberOnlyTextField(2, 1, 1000);

        JPanel flowSubPanel=new JPanel(new FlowLayout(FlowLayout.LEADING,3,2));
        JLabel multiplyLabel=new JLabel("X");
        JLabel horizonLabel=new JLabel("Hor");
        JLabel verticalLabel=new JLabel("Ver");
        JLabel explainLabel=new JLabel("  Num kernel");
        multiplyLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        horizonLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        verticalLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        explainLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        horizontalKernelSize.setPreferredSize(new Dimension(20,20));
        verticalKernelSize.setPreferredSize(new Dimension(20,20));
        kernelNumTextField.setPreferredSize(new Dimension(20,20));
        flowSubPanel.add(horizonLabel);
        flowSubPanel.add(horizontalKernelSize);
        flowSubPanel.add(multiplyLabel);
        flowSubPanel.add(verticalLabel);
        flowSubPanel.add(verticalKernelSize);
        flowSubPanel.add(explainLabel);
        flowSubPanel.add(kernelNumTextField);

        GridLayout layout=new GridLayout(4,1);
        setLayout(layout);
        setSize(200,99);
        width=getWidth();
        add(flowPanel);
        add(keepprobJSlider);
        add(activationFunctionCombobox);
        add(flowSubPanel);
        flowPanel.add(nameLabel);
        flowPanel.add(extendButton);
        flowPanel.add(revertExtendButton);
        setVisible(true);
    }
    public ConvolutionLayerBlock(ConvolutionLayerBlockTemplate template){
        this();
        activationFunctionCombobox.setSelectedItem(template.getFunc());
        keepprobJSlider.setValue(template.getKeepProb());
        kernelNumTextField.setValue(template.getKernelSize());
        verticalKernelSize.setValue(template.getVerticalKernelSize());
        horizontalKernelSize.setValue(template.getHorizontalKernelSize());
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
        // InputBlock, PoolingBlock, 그리고 같은 ConvolutionLayerBlock을 이전 블록으로 받을 수 있음.
        return (block instanceof InputBlock || block instanceof PreprocessorBlock|| block instanceof LayerBlock);
    }


    public int getKeepProb(){
        return keepprobJSlider.getValue();
    }

    public ActivationFunc getActivationFunction() {
        return (ActivationFunc) activationFunctionCombobox.getSelectedItem();
    }

    public int getKernelNum(){
        return (int)kernelNumTextField.getValue();
    }

    public int getHorizonKernelSize(){
        return (int)horizontalKernelSize.getValue();
    }

    public int getVerticalKernelSize(){ return (int)verticalKernelSize.getValue(); }

}

