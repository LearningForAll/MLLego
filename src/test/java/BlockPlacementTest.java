import Presentation.View.BlockListPanel;
import Presentation.View.BlockPlacementPanel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

/**
 * Created by LG on 2018-04-11.
 */
public class BlockPlacementTest {
    BlockListPanel blockListPanel;
    BlockPlacementPanel blockPlacementPanel;
    JFrame initFrame;

    @Before
    public void init(){
        initFrame=new JFrame();
        initFrame.setSize(1700,800);
        initFrame.setVisible(true);
    }

    @Test
    public void blockPlacementTest(){
        blockPlacementPanel=new BlockPlacementPanel();
        blockListPanel=new BlockListPanel();
        blockPlacementPanel.setLocation(0,0);
        blockListPanel.setLocation(800,0);

        initFrame.add(blockListPanel);
        initFrame.add(blockPlacementPanel);
    }
}
