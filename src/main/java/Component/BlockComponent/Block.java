package Component.BlockComponent;

import Models.Coords;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class Block extends JPanel{
    List<Block> previousBlocks;
    List<Block> nextBlocks;
    Point point;
    abstract String getBlockAttrStr();
    abstract void registerNextBlock(Block block);
    abstract void registerPreviousBlock(Block block);
    abstract void getPoint();
    abstract boolean isNextBlockConnectable(Block block);
    abstract boolean isPreviousBlockConnectable(Block block);


}
