package Component.BlockComponent;

import Const.Classifier;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ClassifierBlock extends ExtendableBlock{
    private Block xPartBlock,yPartBlock;

    // 분류기 종류
    Classifier classifier;
    JComboBox optimizerComboBox;

    public ClassifierBlock(){
        //setLocation(1400,100);
        optimizerComboBox = new JComboBox();
        JLabel nameLabel=new JLabel("Classifier Block");
        nameLabel.setForeground(Color.white);
        TitledBorder line=new TitledBorder(new LineBorder(Color.black));
        GridLayout layout=new GridLayout(2,1);
        setLayout(layout);
        setBorder(line);
        setBackground(new Color(0,0,180));
        add(nameLabel);
        add(optimizerComboBox);
        setVisible(true);
    }
    @Override
    String getBlockAttrStr() {
        return null;
    }

    @Override
    boolean isNextBlockConnectable(Block block) {
        return (block instanceof ModelBlock);
    }

    @Override
    boolean isPreviousBlockConnectable(Block block) {
        return (block instanceof DenseBlock);
    }

    public Block getxPartBlock() {
        return xPartBlock;
    }

    public void setxPartBlock(Block xPartBlock) {
        this.xPartBlock = xPartBlock;
    }

    public Block getyPartBlock() {
        return yPartBlock;
    }

    public void setyPartBlock(Block yPartBlock) {
        this.yPartBlock = yPartBlock;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200,70);
    }

}
