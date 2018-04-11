package Presentation.Controller;

import Component.BlockComponent.Block;
import Presentation.View.BlockListPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by chaebyeonghun on 2018. 4. 8..
 */
public class BlockListController {

    private static BlockListController instance = new BlockListController();
    BlockListPanel panel;

    private BlockListController(){

    }

    public void setPanel(BlockListPanel panel){
        this.panel = panel;
    }

    public static BlockListController getInstance() {
        return instance;
    }

    public class BlockListActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            BlockPlacementController.getInstance().addBlock((Block)e.getSource());
        }
    }
}
