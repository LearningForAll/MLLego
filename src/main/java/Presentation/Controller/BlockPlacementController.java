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

    // 패널을 가지고 있음 컨트롤러니까
    BlockPlacementPanel panel;
    List<Block> blocks;

    public BlockPlacementController(BlockPlacementPanel panel){
        blocks = new ArrayList<>();
        this.panel = panel;
        // 만약 패널에 블록 리스트를 가지고 있으면

    }

    public void blinkComponent(){
        while(true){


        }
    }

    //리스트에 블록을 추가함과 동시에
    public void addBlock(Block block){

        blocks.add(block);
        //항상 스택처럼 쌓일테니까 마지막블록에 등록해줌
        blocks.get(blocks.size() - 1).setObserver(this);
        // 패널에도 블록 추가
    }
    public void removeBlock(Block block){
        blocks.remove(block);
        // 패널에도 블록 삭제
    }

    // 블록이 눌렸을때 이벤트
    @Override
    public void update(Block block) {
      


    }
}
