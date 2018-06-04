package Presentation.View;

/**
 * Created by LG on 2018-03-30.
 */
import Component.BlockComponent.Block;
import Component.BlockComponent.ClassifierBlock;
import Component.BlockComponent.ModelBlock;
import Component.BlockComponent.TrainingBlock;

import javax.swing.JPanel;
import java.awt.*;


//처리와 관련된 블록들이 오는 패널
//Classifier, Training, Model이 온다.
public class BlockProcessingPanel extends JPanel{
    ClassifierBlock classifierBlock;
    TrainingBlock trainingBlock;
    ModelBlock modelBlock;

    public BlockProcessingPanel() {
        int location=30;
        setLayout(null);
        setBackground(Color.white);

        modelBlock=new ModelBlock();
        modelBlock.setLocation(50,location);
        location+=modelBlock.getHeight()+50;
        trainingBlock=new TrainingBlock();
        trainingBlock.setLocation(50,location);
        location+=trainingBlock.getHeight()+50;
        classifierBlock = new ClassifierBlock();
        classifierBlock.setSize(290,50);
        classifierBlock.setLocation(5,location);

        add(modelBlock);
        add(trainingBlock);
        add(classifierBlock);

        setVisible(true);

    }

    public Block[] getBlockComponents() {
        return new Block[]{classifierBlock,trainingBlock,modelBlock};
    }
}
