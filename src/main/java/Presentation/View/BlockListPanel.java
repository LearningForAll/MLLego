package Presentation.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-03-26.
 */
public class BlockListPanel extends JTabbedPane {

    BlockSquare blocksquare=new BlockSquare();
    BlockPentagon blockpentagon=new BlockPentagon();
    BlockHexagon blockhexagon=new BlockHexagon();
    BlockRhombus blockrhombus=new BlockRhombus();


    BlockListPanel(){

        add("square", blocksquare);
        add("hexagon", blockhexagon);
        add("pentagon", blockpentagon);
        add("rhombus", blockrhombus);

        setBackground(Color.LIGHT_GRAY);
        setVisible(true);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,600);
    }
}