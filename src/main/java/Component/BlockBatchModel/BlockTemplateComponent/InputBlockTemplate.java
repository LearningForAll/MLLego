package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;
import Component.BlockComponent.InputBlock;
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
        super(positionX, positionY, blockType);
        this.filePath = filePath;
        this.inputOption = inputOption;
    }
    public InputBlockTemplate(Block block){
        super(block.getX(), block.getY(), block.getClass().getSimpleName());
        //this.filePath = ((InputBlock)block).getpath();
        this.inputOption = ((InputBlock)block).getInputOption();

    }
}
