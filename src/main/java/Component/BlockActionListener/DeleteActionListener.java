package Component.BlockActionListener;

import Component.BlockComponent.Block;
import Component.TestBlockComponent.TestBlock;
import Presentation.Controller.BlockPlacementController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by LG on 2018-04-29.
 */
public class DeleteActionListener implements ActionListener {

    Block block;
    TestBlock testBlock;
    public DeleteActionListener(){
    }
    public DeleteActionListener(Block block){
        this.block=block;
    }

    public DeleteActionListener(TestBlock testBlock){this.testBlock=testBlock;}

    public void actionPerformed(ActionEvent e) {
        BlockPlacementController.getInstance().removeBlock(block);
    }
}

