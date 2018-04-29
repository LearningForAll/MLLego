package Presentation.View;

import Component.BlockComponent.Block;
import Const.Classifier;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by LG on 2018-03-26.
 */

//블록 배치 패널
public class BlockPlacementPanel extends JScrollPane {

    //스크롤 패널 위에 올라갈 JPanel
    JPanel workspacePanel = new JPanel(null);

    public BlockPlacementPanel() {
        super(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        setPreferredSize(new Dimension(1180,630));
        workspacePanel.setPreferredSize(new Dimension(3000,1500));
        setViewportView(workspacePanel);

        //scroll=new JScrollPane(this, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        //scroll.setPreferredSize(new Dimension(300,300));
        //add(scroll);
        setVisible(true);
        revalidate();
    }


    public void addNewBlock(Block block){
        workspacePanel.add(block);
        workspacePanel.revalidate();
    }

    public void addBlocks(List<Block> blockList){
        for(Block block : blockList){
            this.add(block);
        }
        revalidate();
    }


    public void deleteAllBlock(){
        System.out.println("모든 블록 삭제");
        this.removeAll();
        revalidate();
    }
}

