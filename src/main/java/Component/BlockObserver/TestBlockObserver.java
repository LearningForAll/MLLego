package Component.BlockObserver;

import Component.BlockComponent.Block;

public interface TestBlockObserver {

    void blinkBlock(Block block);
    void revertOrConnectBlock(Block block);
}