package Component.BlockComponent;

import javax.swing.*;

public abstract class ExtendableBlock extends Block {

    JButton extendButton;

    @Override
    void registerNextBlock(Block block) {

    }

    @Override
    void registerPreviousBlock(Block block) {

    }

    @Override
    boolean isPreviousBlockConnectable(Block block) {
        return false;
    }

    class sibal{
        int i = 0;
    }

}
