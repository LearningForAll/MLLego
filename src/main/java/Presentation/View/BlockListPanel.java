package Presentation.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-03-26.
 */
public class BlockListPanel extends JPanel {
    BlockListPanel(){
        setBackground(Color.LIGHT_GRAY);
        setVisible(true);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,600);
    }
}
