package Presentation.Controller;

import Component.BlockComponent.Block;
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
    //리스트에 블록을 추가함과 동시에
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
        for (Block block1 : blocks) {
            // update의 인자로 들어온 블록이 다른 블록들의
            if (block.isNextBlockConnectable(block1) && !block1.isPreviousBlockConnected()) {
                block1.blinkBottom();
            }
            if (block.isPreviousBlockConnectable(block1) && !block1.isNextBlockConnected()) {
                block1.blinkTop();
            }
        }
    }

    // 구분하기 위해 메소드를 하나 더 만듬
    @Override
    public void revertBlock(Block block) {
        //다시 revert
        blinkBlock(block);
    }
}
