package Component.BlockComponent;

import javax.swing.*;

public abstract class ExtendableBlock extends Block {

    JButton extendButton;

    @Override
    public boolean isNextBlockConnected() {
        return false;
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return false;
    }
}
