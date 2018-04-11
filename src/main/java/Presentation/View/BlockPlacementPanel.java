package Presentation.View;

import Component.BlockComponent.Block;

import javax.swing.*;
import java.awt.*;

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

    }
}

