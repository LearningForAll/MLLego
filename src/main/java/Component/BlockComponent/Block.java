package Component.BlockComponent;

import Component.BlockException.BlockException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Block extends JPanel{
    protected List<Block> previousBlocks;
    protected List<Block> nextBlocks;
    abstract String getBlockAttrStr();

    // 인자로 들어온 블록이 현재 블록의 다음 블록으로 연결 될 수 있는지 확인하는 메소드
    abstract boolean isNextBlockConnectable(Block block);
    // 인자로 들어온 블록이 현재 블록의 이전 블록으로 연결 될 수 있는지 확인하는 메소드
    abstract boolean isPreviousBlockConnectable(Block block);

    public Block(){
        nextBlocks = new ArrayList<>();
        previousBlocks = new ArrayList<>();

    }

    //TODO boolean을 return 하거나 Exception 으로 Handle할수 있게
    // 인자로 넘어온 블록을 다음 블록으로 등록하는 함수
    public void registerNextBlock(Block block) throws BlockException{
        if(isNextBlockConnectable(block)){
            if (block.isPreviousBlockConnectable(this)){
                this.nextBlocks.add(block);
                block.previousBlocks.add(this);
            }else{
                throw new BlockException(block.getClass().getSimpleName() + "is not connectable Previous block for" + this.getClass().getSimpleName());
            }
        }else{
            throw new BlockException(block.getClass().getSimpleName() + "is not connectable Next block for" + this.getClass().getSimpleName());
        }
    }
    // 인자로 넘어온 블록을 이전 블록으로 등록하는 함수
    public void registerPreviousBlock(Block block) throws BlockException{
        if(isPreviousBlockConnectable(block)){
            if (block.isNextBlockConnectable(this)){
                this.previousBlocks.add(block);
                block.nextBlocks.add(this);
            }else {
                throw new BlockException(block.getClass().getSimpleName() + "is not connectable Next block for" + this.getClass().getSimpleName());

            }
        }else{
            throw new BlockException(block.getClass().getSimpleName() + "is not connectable Previous block for" + this.getClass().getSimpleName());

        }
    }


}
