package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;
import Component.BlockComponent.YInputBlock;
import Const.FileType;
import Const.InputOption;

public class YInputBlockTemplate extends BlockTemplate {


    FileType fileType;
    InputOption inputOption;
    String filePath;

    public YInputBlockTemplate(int positionX, int positionY, String blockType
                            ,String filePath, InputOption inputOption) {
        super(positionX, positionY, blockType);
        this.filePath = filePath;
        this.inputOption = inputOption;
    }

    public YInputBlockTemplate(Block block) {
        super(block);
        this.filePath = YInputBlock.getYPath();
        this.inputOption = ((YInputBlock)block).getInputOption();
    }

    public String getFilePath() {
        return filePath;
    }

    public InputOption getInputOption() {
        return inputOption;
    }
}
