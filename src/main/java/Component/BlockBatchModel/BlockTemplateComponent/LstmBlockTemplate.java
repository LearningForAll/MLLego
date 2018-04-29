package Component.BlockBatchModel.BlockTemplateComponent;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class LstmBlockTemplate extends BlockTemplate {

    int keepProb;
    int stackSize;
    public LstmBlockTemplate(int positionX, int positionY, String blockType,
                             List<BlockTemplate> previousBlocks, List<BlockTemplate> nextBlocks,
                             int keepProb, int stackSize) {
        super(positionX, positionY, blockType, previousBlocks, nextBlocks);
        this.keepProb = keepProb;
        this.stackSize = stackSize;
    }
}
