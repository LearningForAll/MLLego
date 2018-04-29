package Component.BlockComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by LG on 2018-04-29.
 */

public class ExtendActionListener implements ActionListener{
    Block block;
    String blockName;

    //boolean isExtended = false;
    int beforeWidth;
    int originalWidth;
    int beforeHeight;
    LayoutManager beforeLayout;

    public ExtendActionListener(Block block) {
        this.block = block;
        this.blockName=block.blockName;
        this.originalWidth=block.flowPanel.getWidth();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        block.setLayout(beforeLayout);
        block.setLayout(new BoxLayout(block,BoxLayout.Y_AXIS));
        beforeWidth=block.getWidth();
        beforeHeight=block.getHeight();
        block.setSize(beforeWidth+originalWidth, beforeHeight);

    }
}
