package Presentation.View;

import Component.BlockComponent.Block;

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
        super(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setPreferredSize(new Dimension(1200,600));
        setBackground(Color.white);
        workspacePanel.setPreferredSize(new Dimension(7000,5000));
        workspacePanel.setBackground(Color.white);
        setViewportView(workspacePanel);

        setVisible(true);
        revalidate();
    }


    public void addNewBlock(Block block){
        workspacePanel.add(block);
        block.reductButton.setEnabled(true);
        block.checkExtendBlock(block);
        workspacePanel.repaint();
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
        workspacePanel.removeAll();
        revalidate();
    }

    public void deleteBlock(Block block){
        workspacePanel.remove(block);
        workspacePanel.revalidate();
        repaint();
    }

    public void deleteBlocks(List<Block> blockList){
        for(Block block : blockList){
            this.remove(block);
        }
        revalidate();
    }
}

