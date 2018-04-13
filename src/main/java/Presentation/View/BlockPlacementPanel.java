package Presentation.View;

import Component.BlockComponent.Block;
import Const.Classifier;

import javax.swing.*;
import java.util.List;

/**
 * Created by LG on 2018-03-26.
 */

//블록 배치 패널
public class BlockPlacementPanel extends JPanel {
    JScrollPane scroll;

    public BlockPlacementPanel() {
        setLayout(null);
        scroll = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS) {
            //this.add(scroll);
            //setVisible(true);
        };
    }

    public void addNewBlock(Block block){
        System.out.print("추가 시도오오오!"+block.getSize().toString());
        this.add(block);
        revalidate();
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

