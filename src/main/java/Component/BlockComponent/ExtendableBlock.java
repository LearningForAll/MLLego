package Component.BlockComponent;

import javax.swing.*;
import java.awt.*;

public abstract class ExtendableBlock extends Block {

    JButton extendButton;
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200,70);
    }

}
