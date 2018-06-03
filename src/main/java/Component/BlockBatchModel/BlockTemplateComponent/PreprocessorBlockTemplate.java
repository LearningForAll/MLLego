package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;
import Component.BlockComponent.InputBlock;
import Component.BlockComponent.PreprocessorBlock;
import Const.FileType;
import Const.InputOption;
import Const.PreprocessorType;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class PreprocessorBlockTemplate extends BlockTemplate {

    FileType fileType;
    PreprocessorType preprocessorType;
    boolean isXdata;


    public PreprocessorBlockTemplate(int positionX, int positionY, String blockType,
                                     FileType fileType, PreprocessorType preprocessorType) {
        super(positionX, positionY, blockType);
        this.fileType = fileType;
        this.preprocessorType = preprocessorType;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public PreprocessorType getPreprocessorType() {
        return preprocessorType;
    }

    public void setPreprocessorType(PreprocessorType preprocessorType) {
        this.preprocessorType = preprocessorType;
    }

    public PreprocessorBlockTemplate(Block block) {
        super(block.getX(), block.getY(), block.getClass().getSimpleName());
        //this.fileType = ((PreprocessorBlock)block).getFileType();
        this.preprocessorType = ((PreprocessorBlock) block).getPreprocessorType();
        this.reducted = block.getReducted();

    }
}
