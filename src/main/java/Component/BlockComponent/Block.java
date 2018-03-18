package Component.BlockComponent;

import Models.Coords;

import java.awt.*;

public abstract class Block extends Component{
    abstract Coords getBlockCoords();
    abstract String getBlockAttrStr();
}
