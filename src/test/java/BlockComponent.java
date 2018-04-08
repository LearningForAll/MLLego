import Component.BlockComponent.ClassifierBlock;
import Component.BlockComponent.ConvolutionLayerBlock;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-04-02.
 */
public class BlockComponent {
    JFrame blockTestFrame;
    ClassifierBlock classifierBlock=new ClassifierBlock();
    ConvolutionLayerBlock convolutionLayerBlock=new ConvolutionLayerBlock();

    @Before
    public void initFrame(){
        System.out.println("프레임초기화");
        blockTestFrame = new JFrame();
        blockTestFrame.setVisible(true);
        blockTestFrame.setSize(500,500);
        blockTestFrame.setLayout(null);
    }

    @Test
    public void testClassifierBlock() throws InterruptedException {
        classifierBlock = new ClassifierBlock();
        convolutionLayerBlock=new ConvolutionLayerBlock();

        classifierBlock.setSize(200,80);
        classifierBlock.setLocation(150,0);
        blockTestFrame.add(classifierBlock);
        convolutionLayerBlock.setSize(200,105);
        convolutionLayerBlock.setLocation(150,130);
        //blockTestFrame.add(convolutionLayerBlock);
        classifierBlock.setVisible(true);
        convolutionLayerBlock.setVisible(true);
        blockTestFrame.validate();
        Thread.sleep(5000);
    }
    public static void main(String[] args){
        //blockTestFrame.add(block);
        //block.setVisible(true);
        BlockComponent com=new BlockComponent();
    }
}
