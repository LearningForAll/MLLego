package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;
import Component.BlockComponent.InputBlock;
import Const.FileType;
import Const.InputOption;
import Const.PreprocessorType;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class PreprocessorBlockTemplate extends BlockTemplate{

    FileType fileType;
    PreprocessorType preprocessorType;
    InputOption inputOption;


    public PreprocessorBlockTemplate(int positionX, int positionY, String blockType,
                                     List<BlockTemplate> previousBlocks, List<BlockTemplate> nextBlocks,
                                     FileType fileType, PreprocessorType preprocessorType) {
        super(positionX, positionY, blockType);
        this.fileType = fileType;
        this.preprocessorType = preprocessorType;
    }
    public PreprocessorBlockTemplate(Block block){
        super(block.getX(), block.getY(), block.getClass().getSimpleName());
        this.fileType = ((InputBlock)block).getFileType();
        this.inputOption = ((InputBlock)block).getInputOption();

    }
}
