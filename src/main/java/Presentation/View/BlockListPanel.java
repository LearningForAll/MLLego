package Presentation.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-03-26.
 */

//BlockLayer, BlockPentagon, BlockHexagon, BlockRhombus가 패널로 들어간 탭패널
public class BlockListPanel extends JTabbedPane {

    BlockLayer blockLayer=new BlockLayer();
    BlockProcessing blockProcessing=new BlockProcessing();
    BlockInput blockInput=new BlockInput();

    public BlockListPanel(){
        add("Input", blockInput);
        add("Layer", blockLayer);
        add("Processing", blockProcessing);

        setVisible(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,600);
    }

}