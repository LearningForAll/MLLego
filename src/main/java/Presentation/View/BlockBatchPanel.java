package Presentation.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-03-26.
 */
public class BlockBatchPanel extends JPanel {
    BlockBatchPanel(){
        setBackground(Color.darkGray);
        setVisible(true);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1200,600);
    }
}