package Presentation.View;

import javax.swing.*;
import java.awt.*;
import Component.BlockComponent.Block;
import Presentation.Controller.BlockListClickListener;
import Util.ArrayUtil;
import Component.TestBlockComponent.TestBlock;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-03-26.
 */
public class ModelTestPanel extends JPanel {

    ModelTestResultPanel modelTestResultPanel=new ModelTestResultPanel();
    BlockLayer blockLayer=new BlockLayer();
    BlockProcessingPanel blockProcessing=new BlockProcessingPanel();
    BlockInput blockInput=new BlockInput();
    BlockTestInputPanel blockTestInput=new BlockTestInputPanel();

    public ModelTestPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.white);

        TestBlock[] allComponents=ArrayUtil.merge1(blockTestInput.getBlockComponents());
        //블록들의 마우스 이벤트 해제
        for (TestBlock mockBlock : allComponents){
            mockBlock.removeMouseListener(mockBlock);
        }
        revalidate();
        setVisible(true);
        this.setLayout(null);
    }

    public void addTestBlock(TestBlock block){
        this.add(block);
        this.revalidate();
    }

}
