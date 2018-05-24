package Component.BlockComponent;

public class ConcatBlock extends Block {
    @Override
    protected String getBlockAttrStr() {
        return null;
    }

    @Override
    public boolean isNextBlockConnectable(Block block) {
        return false;
    }

    @Override
    public boolean isPreviousBlockConnectable(Block block) {
        return false;
    }

    @Override
    public boolean isNextBlockConnected() {
        return false;
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return false;
    }

}
