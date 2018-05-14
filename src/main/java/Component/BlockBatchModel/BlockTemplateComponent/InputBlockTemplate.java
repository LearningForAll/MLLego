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
    boolean isXinput;

    public InputBlockTemplate(int positionX, int positionY, String blockType,
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
