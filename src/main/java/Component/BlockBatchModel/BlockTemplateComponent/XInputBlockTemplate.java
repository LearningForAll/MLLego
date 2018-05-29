package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;
import Component.BlockComponent.InputBlock;
import Component.BlockComponent.XInputBlock;
import Const.FileType;
import Const.InputOption;

public class XInputBlockTemplate extends BlockTemplate {

    FileType fileType;
    String filePath;
    InputOption inputOption;
    boolean isXinput;

    public XInputBlockTemplate(int positionX, int positionY, String blockType,
                              String filePath, InputOption inputOption) {
        super(positionX, positionY, blockType);
        this.filePath = filePath;
        this.inputOption = inputOption;
    }
    public XInputBlockTemplate(Block block){
        super(block.getX(), block.getY(), block.getClass().getSimpleName());
        this.filePath = XInputBlock.getXPath();
        this.inputOption = ((XInputBlock)block).getInputOption();

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public InputOption getInputOption() {
        return inputOption;
    }

    public void setInputOption(InputOption inputOption) {
        this.inputOption = inputOption;
    }

}
