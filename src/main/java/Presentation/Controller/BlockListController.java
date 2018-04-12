package Presentation.Controller;

import Component.BlockComponent.Block;
import Component.BlockComponent.ClassifierBlock;
import Const.Classifier;
import Presentation.View.BlockListPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
