package Presentation.View;

import javax.swing.*;
import java.awt.*;
import Component.BlockComponent.Block;
import Presentation.Controller.BlockListClickListener;
import Presentation.Listener.TestBlockListClickListener;
import Util.ArrayUtil;
import Component.TestBlockComponent.TestBlock;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-03-26.
 */
public class ModelTestPanel extends JPanel {

    BlockTestInputPanel blockTestInput=new BlockTestInputPanel();
    int xPosit=260;
    int yPosit=50;

    public ModelTestPanel(){
        setLayout(null);
        setBackground(Color.white);
        TestBlock[] allComponents=ArrayUtil.merge1(blockTestInput.getBlockComponents());
        //블록들의 마우스 이벤트 해제
        for (TestBlock mockBlock : allComponents){
            mockBlock.removeMouseListener(mockBlock);
            mockBlock.addMouseListener(new TestBlockListClickListener());
        }
        revalidate();
        setVisible(true);
        this.setLayout(null);
    }

    public void addTestBlocks(TestBlock block){
        this.add(block);
        block.setLocation(xPosit, yPosit);
        yPosit+=60+block.getHeight();
        this.repaint();
        this.revalidate();
    }

}
