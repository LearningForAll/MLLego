package Presentation.View;

import javax.swing.*;
import java.awt.*;
import Component.BlockComponent.Block;
import Component.TestBlockComponent.TestInputBlock;
import Component.TestBlockComponent.TestModelBlock;
import Presentation.Controller.BlockListClickListener;
import Presentation.Listener.TestBlockListClickListener;
import Util.ArrayUtil;
import Component.TestBlockComponent.TestBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by LG on 2018-03-26.
 */
public class ModelTestPanel extends JPanel {

    BlockTestInputPanel blockTestInput = new BlockTestInputPanel();
    TestInputBlock testInputBlock;
    TestModelBlock testModelBlock = null;
    int xPosit = 160;
    int yPosit = 60;

    public ModelTestPanel(){
        setLayout(null);
        setBackground(Color.white);
        testInputBlock = new TestInputBlock();
        //블록들의 마우스 이벤트 해제
        /*
        for (TestBlock mockBlock : allComponents){
            mockBlock.removeMouseListener(mockBlock);
            mockBlock.addMouseListener(new TestBlockListClickListener());
        }
        revalidate();
        */
        setVisible(true);

        // 인풋 고정블록 추가
        addTestBlocks(testInputBlock);

    }

    public void addTestBlocks(TestBlock testBlock){
        this.add(testBlock);
        testBlock.setLocation(xPosit, yPosit);
        yPosit += 120 + testBlock.getHeight();
        this.repaint();
        this.revalidate();
    }
    public void addArrowImage(){

    }

    //My Model에서 이 함수 호출
    public void addModelBlock(TestModelBlock testModelBlock){

        if(this.testModelBlock == null){

            this.testModelBlock = testModelBlock;
            this.add(this.testModelBlock);
            this.testModelBlock.setLocation(xPosit, yPosit);
            this.repaint();
            this.revalidate();
        }else{
            // 패널에서 삭제
            this.remove(this.testModelBlock);
            this.testModelBlock = testModelBlock;
            this.add(this.testModelBlock);
            this.testModelBlock.setLocation(xPosit, yPosit);
            this.repaint();
            this.revalidate();
        }
    }

}
