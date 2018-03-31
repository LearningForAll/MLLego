package Component.BlockComponent;

/**
 * Created by chaebyeonghun on 2018. 3. 27..
 */
public class PreprocessorBlock extends Block {


    public PreprocessorBlock(){


    }
    @Override
    String getBlockAttrStr() {
        return null;
    }




    @Override
    boolean isNextBlockConnectable(Block block) {
        return (block instanceof LayerBlock);
    }

    @Override
    boolean isPreviousBlockConnectable(Block block) {
        return (block instanceof InputBlock);
    }
}
