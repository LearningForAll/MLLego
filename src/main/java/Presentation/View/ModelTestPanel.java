package Presentation.View;

import Component.BlockComponent.Block;
import Presentation.Controller.BlockListClickListener;
import Util.ArrayUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-03-26.
 */
public class ModelTestPanel extends JPanel {
    ModelTestResultPanel modelTestResultPanel;
    BlockLayer blockLayer=new BlockLayer();
    BlockProcessing blockProcessing=new BlockProcessing();
    BlockInput blockInput=new BlockInput();

    public ModelTestPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.white);

        Block[] allComponents = ArrayUtil.merge1(blockInput.getBlockComponents(),blockLayer.getBlockComponents(),blockProcessing.getBlockComponents());
        //블록들의 마우스 이벤트 해제
        for (Block mockBlock : allComponents){
            mockBlock.removeMouseListener(mockBlock);
        }
        revalidate();
        setVisible(true);
    }

}
