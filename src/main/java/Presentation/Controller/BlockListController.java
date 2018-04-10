package Presentation.Controller;

import Presentation.View.BlockListPanel;

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
}
