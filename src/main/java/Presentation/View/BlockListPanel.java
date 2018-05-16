package Presentation.View;

import Component.BlockActionListener.DeleteActionListener;
import Component.BlockActionListener.ExtendActionListener;
import Component.BlockActionListener.ReductionActionListener;
import Component.BlockComponent.Block;
import Presentation.Controller.BlockListClickListener;
import Presentation.Controller.BlockListController;
import Util.ArrayUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Created by LG on 2018-03-26.
 */

//blockLayer, blockPrecossing, blockInput이 패널로 들어간 탭패널
public class BlockListPanel extends JTabbedPane {

    BlockLayer blockLayer=new BlockLayer();
    BlockProcessing blockProcessing=new BlockProcessing();
    BlockInput blockInput=new BlockInput();

    public BlockListPanel(){
        setBackground(Color.white);
        add("Input", blockInput);
        add("Layer", blockLayer);
        add("Processing", blockProcessing);

        Block[] allComponents = ArrayUtil.merge1(blockInput.getBlockComponents(),blockLayer.getBlockComponents(),blockProcessing.getBlockComponents());
        //BlockListPanel에 있는 블록들에는 드래그 안되고 마우스 클릭만 가능하게 함
        for (Block mockBlock : allComponents){
            mockBlock.removeMouseListener(mockBlock);
            mockBlock.addMouseListener(new BlockListClickListener());
        }
        revalidate();
        setVisible(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300,600);
    }

}