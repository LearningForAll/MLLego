package Component.BlockComponent;

import Component.BlockBatchModel.BlockTemplateComponent.ClassifierBlockTemplate;
import Const.Classifier;

import javax.swing.*;
import java.awt.*;

public class ClassifierBlock extends Block{
    private Block xPartBlock,yPartBlock;

    // 분류기 종류
    Classifier classifier;
    JComboBox <Classifier> classifierComboBox;

    public ClassifierBlock(){
        super("Classifier Block");
        classifierComboBox = new JComboBox<>(Classifier.values());
        GridLayout layout=new GridLayout(2,1);
        setLayout(layout);
        setSize(200,50);
        flowPanel.setBackground(new Color(0,0,180));
        add(flowPanel);
        add(classifierComboBox);
        setVisible(true);
    }
    public ClassifierBlock(ClassifierBlockTemplate template){
        this();
        classifier = template.getClassifier();
        setLocation(template.getPositionX(), template.getPositionY());

    }


    @Override
    String getBlockAttrStr() {
        return null;
    }

    @Override
    public boolean isNextBlockConnectable(Block block) {
        return (block instanceof TrainingBlock);
    }

    @Override
    public boolean isPreviousBlockConnectable(Block block) {
        return (block instanceof LayerBlock);
    }

    @Override
    public boolean isNextBlockConnected() {
        return (nextBlocks.size() != 0);
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return (previousBlocks.size() != 0);
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
