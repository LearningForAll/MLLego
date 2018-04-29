package Component.BlockBatchModel.BlockTemplateComponent;

import Const.FileType;
import Const.PreprocessorType;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class PreprocessorBlockTemplate extends BlockTemplate{

    FileType fileType;
    PreprocessorType preprocessorType;


    public PreprocessorBlockTemplate(int positionX, int positionY, String blockType,
                                     List<BlockTemplate> previousBlocks, List<BlockTemplate> nextBlocks,
                                     FileType fileType, PreprocessorType preprocessorType) {
        super(positionX, positionY, blockType, previousBlocks, nextBlocks);
        this.fileType = fileType;
        this.preprocessorType = preprocessorType;
    }
}
