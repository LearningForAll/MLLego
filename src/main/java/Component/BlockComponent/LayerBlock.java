package Component.BlockComponent;

public abstract class LayerBlock extends ExtendableBlock{


    @Override
    public boolean isNextBlockConnected() {
        return false;
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return false;
    }
}
