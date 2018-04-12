package Presentation.View;

/**
 * Created by LG on 2018-03-30.
 */
import Component.BlockComponent.Block;
import Component.BlockComponent.ClassifierBlock;
import Component.BlockComponent.ModelBlock;
import Component.BlockComponent.TrainingBlock;

import javax.swing.JPanel;


//처리와 관련된 블록들이 오는 패널
//Classifier, Training, Model이 온다.
public class BlockProcessing extends JPanel{
    ClassifierBlock classifierBlock;
    TrainingBlock trainingBlock;
    ModelBlock modelBlock;

    public BlockProcessing() {
        setLayout(null);
        modelBlock=new ModelBlock("Model Block");
        modelBlock.setSize(200,40);
        modelBlock.setLocation(150,20);
        trainingBlock=new TrainingBlock("Training Block");
        trainingBlock.setSize(200,75);
        trainingBlock.setLocation(150,90);
        classifierBlock = new ClassifierBlock();
        classifierBlock.setSize(200,50);
        classifierBlock.setLocation(150,210);

        add(modelBlock);
        add(trainingBlock);
        add(classifierBlock);

        setVisible(true);

    }

    public Block[] getBlockComponents() {
        return new Block[]{classifierBlock,trainingBlock,modelBlock};
    }
}
