package Presentation.View;

import Component.TestBlockComponent.TestBlock;
import Component.TestBlockComponent.TestInputBlock;
import Component.TestBlockComponent.TestPreProcessorBlock;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BlockTestInputPanel extends JPanel {

    TestInputBlock testInputBlock;
    TestPreProcessorBlock testPreProcessorBlock;


    public BlockTestInputPanel(){
        setLayout(null);
        setBackground(Color.white);
        testInputBlock = new TestInputBlock();
        testPreProcessorBlock = new TestPreProcessorBlock();

        testInputBlock.setLocation(50,20);
        testPreProcessorBlock.setLocation(50,120);

        add(testInputBlock);
        add(testPreProcessorBlock);
        setVisible(true);
    }

    public TestBlock[] getAllComponent(){
        return new TestBlock[]{testInputBlock, testPreProcessorBlock};
    }

}
