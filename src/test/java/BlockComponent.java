import Component.BlockComponent.Block;
import Component.BlockComponent.ClassifierBlock;
import Component.BlockComponent.ConvolutionLayerBlock;
import Component.BlockComponent.InputBlock;
import Presentation.View.BlockPlacementDefault;
import Presentation.View.BlockPlacementPanel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-04-02.
 */
public class BlockComponent {
    JFrame blockTestFrame;
    ClassifierBlock classifierBlock;
    ConvolutionLayerBlock convolutionLayerBlock;
    InputBlock inputBlock;

    @Before
    public void initFrame(){
        System.out.println("프레임초기화");
        blockTestFrame = new JFrame();
        blockTestFrame.setVisible(true);
        blockTestFrame.setSize(1300,700);
        blockTestFrame.setLayout(null);
    }

    @Test
    public void testClassifierBlock() throws InterruptedException {
        classifierBlock = new ClassifierBlock();
        classifierBlock.setSize(200,80);
        classifierBlock.setLocation(150,0);
        blockTestFrame.add(classifierBlock);
        classifierBlock.setVisible(true);
        blockTestFrame.validate();
        Thread.sleep(5000);
    }

    @Test
    public void testConvolutionBlock() throws InterruptedException{
        convolutionLayerBlock=new ConvolutionLayerBlock();
        convolutionLayerBlock.setSize(200,105);
        convolutionLayerBlock.setLocation(150,130);
        blockTestFrame.add(convolutionLayerBlock);
        convolutionLayerBlock.setVisible(true);
        blockTestFrame.validate();
        Thread.sleep(5000);
    }

    @Test
    public void simpletest() throws InterruptedException {
        BlockPlacementPanel blockPlacementPanel;
        blockPlacementPanel=new BlockPlacementPanel();
        blockPlacementPanel.setVisible(true);
        blockTestFrame.validate();
        Thread.sleep(5000);
    }

    @Test
    public void testInputBlock() throws InterruptedException{
        inputBlock=new InputBlock();
        inputBlock.setSize(200,105);
        inputBlock.setLocation(150,130);
        blockTestFrame.add(inputBlock);
        inputBlock.setVisible(true);
        blockTestFrame.validate();
        Thread.sleep(50000);
    }

}
