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
