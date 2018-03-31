package Component.BlockComponent;

import Models.Coords;

public class ModelBlock extends Block {

    @Override
    String getBlockAttrStr() {
        return null;
    }

    @Override
    void registerNextBlock(Block block) {

    }

    @Override
    void registerPreviousBlock(Block block) {

    }


    @Override
    void getPoint() {

    }

    @Override
    boolean isNextBlockConnectable(Block block) {
        return false;
    }

    @Override
    boolean isPreviousBlockConnectable(Block block) {
        return false;
    }
}
