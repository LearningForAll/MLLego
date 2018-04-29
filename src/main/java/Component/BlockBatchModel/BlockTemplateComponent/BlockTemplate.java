package Component.BlockBatchModel.BlockTemplateComponent;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public abstract class BlockTemplate implements Serializable {

    int positionX;
    int positionY;
    String blockType;
    List<BlockTemplate> previousBlocks;
    List<BlockTemplate> nextBlocks;

    public BlockTemplate(int positionX, int positionY, String blockType, List<BlockTemplate> previousBlocks, List<BlockTemplate> nextBlocks){
        this.positionX = positionX;
        this.positionY = positionY;
        this.blockType = blockType;
    }


}
