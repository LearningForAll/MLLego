package Component.BlockComponent;

public abstract class LayerBlock extends ExtendableBlock{


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
}
