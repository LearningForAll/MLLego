package Component.BlockComponent;

import Component.NumberOnlyTextField;
import Const.PaddingOption;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
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

    public PoolingBlock(){
        paddingOptionCombobox = new JComboBox<>(PaddingOption.values());
        horizontalKernelSizeTextField  = new NumberOnlyTextField(2, 1, 10000);
        verticalKernelSizeTextField = new NumberOnlyTextField(2, 1, 10000);
        horizontalStrideTextField = new NumberOnlyTextField(1, 1, 10000);
        verticalStrideTextField = new NumberOnlyTextField(1, 1, 10000);

        JPanel flowPanel=new JPanel(new FlowLayout(FlowLayout.LEADING,1,2));
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
        flowPanel.add(horizonLabel);
        flowPanel.add(horizontalKernelSizeTextField);
        flowPanel.add(multiplyLabel);
        flowPanel.add(verticalLabel);
        flowPanel.add(verticalKernelSizeTextField);
        flowPanel.add(blankLabel);
        flowPanel.add(horizonLabel2);
        flowPanel.add(horizontalStrideTextField);
        flowPanel.add(multiplyLabel2);
        flowPanel.add(verticalLabel2);
        flowPanel.add(verticalStrideTextField);


        JLabel nameLabel=new JLabel("Pooling Block");
        TitledBorder line=new TitledBorder(new LineBorder(Color.black));
        GridLayout layout=new GridLayout(3,1);
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        setLayout(layout);
        setBorder(line);
        add(nameLabel);
        add(paddingOptionCombobox);
        add(flowPanel);
        setBackground(new Color(150, 0, 205));
        setVisible(true);

    }

    @Override
    String getBlockAttrStr() {
        return null;
    }

    @Override
    boolean isNextBlockConnectable(Block block) {
        return (block instanceof LayerBlock);
    }

    @Override
    boolean isPreviousBlockConnectable(Block block) {
        return (block instanceof LayerBlock);
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

}
