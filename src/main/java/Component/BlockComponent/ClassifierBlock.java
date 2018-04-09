package Component.BlockComponent;

import Const.Classifier;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ClassifierBlock extends ExtendableBlock{
    private Block xPartBlock,yPartBlock;

    // 분류기 종류
    Classifier classifier;
    JComboBox <Classifier> classifierComboBox;
    public ClassifierBlock(){
        classifierComboBox = new JComboBox<>(Classifier.values());
        JLabel nameLabel=new JLabel("Classifier Block");
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        TitledBorder line=new TitledBorder(new LineBorder(Color.black));
        GridLayout layout=new GridLayout(2,1);

        setLayout(layout);
        setBorder(line);
        setBackground(new Color(0,0,180));
        add(nameLabel);
        add(classifierComboBox);
        setVisible(true);
    }
    @Override
    String getBlockAttrStr() {
        return null;
    }

    @Override
    public boolean isNextBlockConnectable(Block block) {
        return (block instanceof ModelBlock);
    }

    @Override
    public boolean isPreviousBlockConnectable(Block block) {
        return (block instanceof DenseBlock);
    }

    @Override
    public boolean isNextBlockConnected() {
        return false;
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return false;
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

    public Classifier getClassifier(){
        return (Classifier)classifierComboBox.getSelectedItem();
    }


}
