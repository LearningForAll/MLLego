package Component.BlockBatchModel.BlockTemplateComponent;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class InputBlockTemplate extends BlockTemplate {

    String filePath;

    public InputBlockTemplate(int positionX, int positionY, String blockType, List<BlockTemplate> previousBlocks, List<BlockTemplate> nextBlocks) {
        super(positionX, positionY, blockType, previousBlocks, nextBlocks);
    }
}
