package Component.BlockComponent;

import Const.FileType;
import Const.InputOption;

public abstract class InputBlock extends Block{
    public abstract FileType getFileType();
    public abstract InputOption getInputOption();
}
