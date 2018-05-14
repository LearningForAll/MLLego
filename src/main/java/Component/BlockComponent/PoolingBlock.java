package Component.BlockComponent;

import Component.BlockBatchModel.BlockTemplateComponent.PoolingBlockTemplate;
import Component.NumberOnlyTextField;
import Const.PaddingOption;
import Const.PoolingType;
import javax.swing.*;
import java.awt.*;

/**
 * Created by chaebyeonghun on 2018. 3. 24..
 */
public class PoolingBlock extends LayerBlock {

    // 커널 사이즈와 스트라이드를 설정할 수 있는 필드들 숫자만 입력할 수 있게 입력을 제한
    NumberOnlyTextField horizontalStrideTextField;
    NumberOnlyTextField verticalStrideTextField;
    NumberOnlyTextField horizontalKernelSizeTextField;
    NumberOnlyTextField verticalKernelSizeTextField;

    JComboBox<PaddingOption> paddingOptionCombobox;

    // todo pool 옵션 지정할 수 있게끔
    JComboBox<PoolingType> poolOptionCombobox;

    public PoolingBlock(){
        super();
        blockName="Pooling Block";
        nameLabel = new JLabel(blockName);
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        paddingOptionCombobox = new JComboBox<>(PaddingOption.values());
        poolOptionCombobox=new JComboBox<>(PoolingType.values());
        horizontalKernelSizeTextField  = new NumberOnlyTextField(2, 1, 10000);
        verticalKernelSizeTextField = new NumberOnlyTextField(2, 1, 10000);
        horizontalStrideTextField = new NumberOnlyTextField(1, 1, 10000);
        verticalStrideTextField = new NumberOnlyTextField(1, 1, 10000);

        JPanel flowSubPanel=new JPanel(new FlowLayout(FlowLayout.LEADING,1,2));
        JLabel multiplyLabel=new JLabel("X");
        JLabel multiplyLabel2=new JLabel("X");
        JLabel horizonLabel=new JLabel("Hor");
        JLabel horizonLabel2=new JLabel("Hor");
        JLabel verticalLabel=new JLabel("Ver");
        JLabel verticalLabel2=new JLabel("Ver");
        JLabel blankLabel=new JLabel("   ");
        multiplyLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        horizonLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        horizonLabel2.setFont(new Font("BOLD", Font.BOLD, 11));
        verticalLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        verticalLabel2.setFont(new Font("BOLD", Font.BOLD, 11));
        horizontalKernelSizeTextField.setPreferredSize(new Dimension(20,20));
        verticalKernelSizeTextField.setPreferredSize(new Dimension(20,20));
        horizontalStrideTextField.setPreferredSize(new Dimension(20,20));
        verticalStrideTextField.setPreferredSize(new Dimension(20,20));
        flowSubPanel.add(horizonLabel);
        flowSubPanel.add(horizontalKernelSizeTextField);
        flowSubPanel.add(multiplyLabel);
        flowSubPanel.add(verticalLabel);
        flowSubPanel.add(verticalKernelSizeTextField);
        flowSubPanel.add(blankLabel);
        flowSubPanel.add(horizonLabel2);
        flowSubPanel.add(horizontalStrideTextField);
        flowSubPanel.add(multiplyLabel2);
        flowSubPanel.add(verticalLabel2);
        flowSubPanel.add(verticalStrideTextField);

        GridLayout layout=new GridLayout(4,1);
        setLayout(layout);
        setSize(200,99);
        add(flowPanel);
        add(paddingOptionCombobox);
        add(poolOptionCombobox);
        add(flowSubPanel);

        flowPanel.add(nameLabel);
        flowPanel.add(extendButton);
        setVisible(true);

    }
    public PoolingBlock(PoolingBlockTemplate template){
        this();
        paddingOptionCombobox.setSelectedItem(template.getPaddingOption());
        horizontalStrideTextField.setText(String.valueOf(template.getHorizontalStride()));
        verticalStrideTextField.setText(String.valueOf(template.getVerticalStride()));
        horizontalKernelSizeTextField.setText(String.valueOf(template.getHorizontalKernelSize()));
        verticalKernelSizeTextField.setText(String.valueOf(template.getVerticalKernelSize()));
        setLocation(template.getPositionX(), template.getPositionY());
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

    public PaddingOption getPaddingOption(){ return (PaddingOption) paddingOptionCombobox.getSelectedItem(); }

    public int getHorizonKernel(){
        return (int)horizontalKernelSizeTextField.getValue();
    }

    public int getVerticalKernel(){
        return (int)verticalKernelSizeTextField.getValue();
    }

    public int getHorizonStride(){
        return (int)horizontalStrideTextField.getValue();
    }

    public int getVerticalStride(){
        return (int)verticalStrideTextField.getValue();
    }

    public PoolingType getPoolingType(){
        return (PoolingType)poolOptionCombobox.getSelectedItem();
    }
}
