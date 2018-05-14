package Component.BlockActionListener;

import Component.BlockComponent.Block;

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

    public ExtendActionListener(Block block) {
        this.block = block;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        beforeWidth = block.getWidth();
        beforeHeight = block.getHeight();
        block.setSize(beforeWidth + 200, beforeHeight);
        block.revalidate();
    }
}
