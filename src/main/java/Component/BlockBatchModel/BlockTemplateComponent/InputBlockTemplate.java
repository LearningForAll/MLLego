package Component.BlockBatchModel.BlockTemplateComponent;

import Const.InputOption;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class InputBlockTemplate extends BlockTemplate {

    String filePath;
    InputOption inputOption;

    public InputBlockTemplate(int positionX, int positionY, String blockType,
                              List<BlockTemplate> previousBlocks, List<BlockTemplate> nextBlocks,
                              String filePath, InputOption inputOption) {
        super(positionX, positionY, blockType, previousBlocks, nextBlocks);
        this.filePath = filePath;
        this.inputOption = inputOption;
    }
}
