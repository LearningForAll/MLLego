package Component.BlockComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Block extends JPanel{
    private List<Block> previousBlocks;
    private List<Block> nextBlocks;
    // 좌표
    private Point point;
    abstract String getBlockAttrStr();

    // 인자로 들어온 블록이 현재 블록의 다음 블록으로 연결 될 수 있는지 확인하는 메소드
    abstract boolean isNextBlockConnectable(Block block);
    // 인자로 들어온 블록이 현재 블록의 이전 블록으로 연결 될 수 있는지 확인하는 메소드
    abstract boolean isPreviousBlockConnectable(Block block);

    public Block(){
        nextBlocks = new ArrayList<>();
        previousBlocks = new ArrayList<>();
        point = new Point();
    }

    public void registerNextBlock(Block block){
        if(isNextBlockConnectable(block)){
            if (block.isPreviousBlockConnectable(this)){
                this.nextBlocks.add(block);
                block.previousBlocks.add(this);
            }
        }
    }
    public void registerPreviousBlock(Block block){
        if(isPreviousBlockConnectable(block)){
            if (block.isNextBlockConnectable(this)){
                this.previousBlocks.add(block);
                block.nextBlocks.add(this);
            }
        }
    }

    public Point getPoint(){
        return this.point;
    }


}
