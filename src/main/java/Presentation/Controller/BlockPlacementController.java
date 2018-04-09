package Presentation.Controller;

import Component.BlockComponent.Block;
import Component.BlockEventThread.BlockDragEventThread;
import Component.BlockObserver.BlockObserver;
import Presentation.View.BlockPlacementPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 1..
 */
public class BlockPlacementController implements BlockObserver {

    // singleton pattern
    private static BlockPlacementController instance = new BlockPlacementController();
    static boolean isBlockEventEvoked = false;
    // 패널을 가지고 있음 컨트롤러니까
    BlockPlacementPanel panel;
    List<Block> blocks;


    private BlockPlacementController(){

    }
    public static BlockPlacementController getInstance(){
        return instance;
    }

    public void setPanel(BlockPlacementPanel panel){
        this.panel = panel;
    }
    /**
     * 이 함수로 블록 BlockPlacementController에 블록 추가
    * **/
    public void addBlock(Block block) {

        blocks.add(block);
        //항상 스택처럼 쌓일테니까 마지막블록에 등록해줌
        blocks.get(blocks.size() - 1).setObserver(this);
        // 패널에도 블록 추가
    }

    public void removeBlock(Block block) {
        blocks.remove(block);
        // 패널에도 블록 삭제
    }


    @Override
    public void blinkBlock(Block block) {

        BlockDragEventThread thread = new BlockDragEventThread();
        thread.run(blocks, block);
        // stop
        if(!isBlockEventEvoked){
            thread.triggerEvent();
        }


    }

    // 구분하기 위해 메소드를 하나 더 만듬
    @Override
    public void revertBlock(Block block) {
        isBlockEventEvoked = false;
        for(Block block1 : blocks){
            block1.revertBlock();
        }
    }
    // block 을 기준으로 첫번째 인자가 기준블록이면 기준블록이 아래 그 반대의 경우는 인자 위치를 바꿔주면됌
    private boolean checkCloseBlock(Block block, Block block1){
        return (block.getX() - block1.getX() > 100 && block.getX() - block1.getX() > -100 && block1.getY() - block.getY() > 100);
    }


}
