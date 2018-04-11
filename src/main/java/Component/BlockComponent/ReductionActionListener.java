package Component.BlockComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by LG on 2018-04-11.
 */
public class ReductionActionListener implements ActionListener {
    Block block;
    String blockName;
    boolean isReducted = false;
    int blockHeight;
    int panelHeight;
    public ReductionActionListener(Block block) {
        this.block = block;
        this.blockName=block.blockName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        blockHeight=block.getHeight();
        panelHeight=block.flowPanel.getHeight();

        if (isReducted){
            //TODO :: 축소되있으면 확장

        }else {
            //TODO :: 확장되있으면 축소
            block.setPreferredSize(new Dimension(block.getWidth(), panelHeight));
            block.setVisible(true);
            isReducted=true;
        }
    }
}
