package Component.BlockActionListener;

import Component.BlockComponent.Block;
import Component.BlockComponent.ExtendableBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by LG on 2018-04-29.
 */

public class ExtendActionListener implements ActionListener{
    Block block;
    int beforeWidth;
    int beforeHeight;

    public ExtendActionListener(ExtendableBlock block) {
        this.block = block;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        beforeWidth=block.getWidth();
        beforeHeight=block.getHeight();
        block.setSize(beforeWidth+200, beforeHeight);
       
        beforeHeight = block.getHeight();
        beforeWidth=block.getWidth();
        block.setSize(beforeWidth+block.width, beforeHeight);
        ((ExtendableBlock)block).addExtendSize();  
        block.revertExtendButton.setEnabled(true);

        block.revalidate();
    }

}
