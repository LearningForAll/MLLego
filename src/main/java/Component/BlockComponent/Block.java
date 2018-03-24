package Component.BlockComponent;

import Models.Coords;

import java.awt.*;

public abstract class Block extends Component{
    Block[] beforeBlocks;
    Block[] afterBlocks;
    Coords coords;
    abstract String getBlockAttrStr();
}
