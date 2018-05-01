package Component.BlockComponent;

import java.awt.*;

public abstract class LayerBlock extends ExtendableBlock{

    @Override
    public boolean isNextBlockConnected() {
        return (nextBlocks.size() != 0);
    }
    /*
    public LayerBlock(String name){
        super(name);
    }
    */

    public LayerBlock(){
        super();
    }
    @Override
    public boolean isPreviousBlockConnected() {
        return (previousBlocks.size() != 0);
    }

}
