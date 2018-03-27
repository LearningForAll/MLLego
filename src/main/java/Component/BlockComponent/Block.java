package Component.BlockComponent;

import Models.Coords;

import javax.swing.*;
import java.awt.*;

public abstract class Block extends JPanel{
    Block[] beforeBlocks;
    Block[] afterBlocks;
    Coords coords;
    abstract String getBlockAttrStr();
}
