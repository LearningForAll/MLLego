package Presentation.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import Component.BlockComponent.Block;
import Component.TestBlockComponent.TestInputBlock;
import Component.TestBlockComponent.TestModelBlock;
import Presentation.Controller.BlockListClickListener;
import Presentation.Listener.TestBlockListClickListener;
import Util.ArrayUtil;
import Component.TestBlockComponent.TestBlock;
import Util.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by LG on 2018-03-26.
 */
public class ModelTestPanel extends JPanel {

    JPanel inputPanel, modelPanel;
    JLabel imageLabel;
    TestInputBlock testInputBlock;
    TestModelBlock testModelBlock = null;
    int xPosit = 160;
    int yPosit = 60;

    public ModelTestPanel(){
        setVisible(true);
        GridLayout gridLayout = new GridLayout(3, 1);
        setLayout(gridLayout);
        inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
        inputPanel.setBackground(Color.WHITE);
        testInputBlock = new TestInputBlock();
        inputPanel.add(testInputBlock);
        add(inputPanel);
        setBackground(Color.white);
        try{
            imageLabel = new JLabel(new ImageIcon(FileUtil.getResourcePath("icon/arrow_icon2.png")));
            add(imageLabel);
        }catch (Exception e){
            e.printStackTrace();
        }
        // 인풋 고정블록 추가


    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 3;
        int y = 3;
        //g.drawImage(image, x, y, this);
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
            modelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
            this.testModelBlock.setSize(300, 75);
            modelPanel.add(this.testModelBlock);
            modelPanel.setBackground(Color.white);
            this.add(modelPanel);
            this.repaint();
            this.revalidate();
        }else{
            this.testModelBlock = testModelBlock;
            modelPanel.removeAll();
            modelPanel.add(this.testModelBlock);
            this.testModelBlock.repaint();
            this.testModelBlock.revalidate();
        }
    }

}
