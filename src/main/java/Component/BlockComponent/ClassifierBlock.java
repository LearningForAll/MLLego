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
        super();
        nameLabel = new JLabel(getClass().getSimpleName());
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        classifierComboBox = new JComboBox<>(Classifier.values());
        GridLayout layout=new GridLayout(2,1);
        setLayout(layout);
        setSize(400,50);
        flowPanel.setBackground(new Color(0,0,180));
        add(flowPanel);
        add(classifierComboBox);
        flowPanel.add(nameLabel);
        width=getWidth();

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
        return (block instanceof LayerBlock || block instanceof InputBlock || block instanceof PreprocessorBlock);
    }

    @Override
    public boolean isNextBlockConnected() {
        return (nextBlocks.size() != 0);
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return (previousBlocks.size() >= 2);
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
    public void deleteXYBlock(){
        this.xPartBlock = null;
        this.yPartBlock = null;
    }

    public Classifier getClassifier(){
        return (Classifier)classifierComboBox.getSelectedItem();
    }

    //TODO XY블록 해제하는것도 생각해봐야함
    public boolean setXYPartBlock(Block block){
        // 무조건 X블록 먼저 셋팅하게한다.
        if(this.xPartBlock == null){
            this.setxPartBlock(block);
            return true;
        }else if(this.yPartBlock == null && (block instanceof InputBlock || block instanceof PreprocessorBlock)){
            this.setyPartBlock(block);
            return false;
        }else{
            System.out.println("Can't connect the Block");
            return false;
        }
    }
    public boolean checkIfXYConnectable(Block block){
        if(this.xPartBlock == null){
            return true;
        }else if(this.yPartBlock == null && (block instanceof InputBlock || block instanceof PreprocessorBlock)){
            return false;
        }else{
            System.out.println("Can't connect the Block");
            return false;
        }
    }

}
