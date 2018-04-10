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
        // 현재 드래그 되고있는 블록은 리스트에서 제거하기 위해..
        List<Block> tempBlocks = this.blocks;
        tempBlocks.remove(block);

        for (Block block1 : tempBlocks) {
            //TODO isNextBlockConnectable 함수의 로직이 완성되면 뒤에  && !block1.isNextBlockConnected() 붙임
            if (checkCloseBlock(block, block1) && block1.isNextBlockConnectable(block)) {
                block1.blinkBottom();
                block.blinkTop();
            }else{
                // 거리에서 멀어진 블록들을 revert 시킨다.
                block.revertBlock();
                block1.revertBlock();
            }
            if (checkCloseBlock(block1, block) && block1.isPreviousBlockConnectable(block)){
                block1.blinkTop();
                block.blinkBottom();
            }else{
                block.revertBlock();
                block1.revertBlock();
            }
        }


    }

    // 드래그가 풀리면 블록을 원래대로 되돌리고
    @Override
    public void revertOrConnectBlock(Block block) {

        List<Block> tempBlocks = this.blocks;
        tempBlocks.remove(block);

        if(block.checkBorder()){
            if(block.checkTopBorder()){
                for(Block block1 : tempBlocks){
                    if(block1.checkBottomBorder()){
                        block.registerNextBlock(block1);
                        block1.registerPreviousBlock(block);
                    }
                }
            }else{// bottom이 빛날때
                for(Block block1 : tempBlocks){
                    if(block1.checkTopBorder()){
                        block.registerPreviousBlock(block1);
                        block1.registerNextBlock(block);
                    }
                }
            }

        }
        for (Block block1 : blocks) {
            block1.revertBlock();
        }

    }
    // block 을 기준으로 첫번째 인자가 기준블록이면 기준블록이 아래 그 반대의 경우는 인자 위치를 바꿔주면됌
    private boolean checkCloseBlock(Block block, Block block1){
        return (block.getX() - block1.getX() + block1.getWidth() < 100
                && block.getX() + block.getWidth() - block1.getX() > -100
                && block1.getHeight() + block1.getY() - block.getY() < 100);
    }


}
