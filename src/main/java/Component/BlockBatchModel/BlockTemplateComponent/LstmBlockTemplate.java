package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;
import Component.BlockComponent.LstmBlock;
import Const.RnnOutputOption;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class LstmBlockTemplate extends BlockTemplate {

    int keepProb;
    int stackSize;
    int cellSize;
    RnnOutputOption rnnOutputOption;

    public LstmBlockTemplate(int positionX, int positionY, String blockType,
                             int keepProb, int stackSize) {
        super(positionX, positionY, blockType);
        this.keepProb = keepProb;
        this.stackSize = stackSize;
    }

    public int getKeepProb() {
        return keepProb;
    }

    public void setKeepProb(int keepProb) {
        this.keepProb = keepProb;
    }

    public int getStackSize() {
        return stackSize;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    public LstmBlockTemplate(Block block){
        super(block.getX(), block.getY(), block.getClass().getSimpleName());
       // this.stackSize = ((LstmBlock)block).getStackSize();
        this.keepProb = ((LstmBlock)block).getKeepProb();
        this.extended = block.isBlockJustExtended();
    }
}
