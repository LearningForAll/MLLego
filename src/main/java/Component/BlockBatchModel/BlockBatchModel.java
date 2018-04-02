package Component.BlockBatchModel;

import Component.BlockComponent.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 3. 30..
 */
public class BlockBatchModel {

    //TODO 좌표 값도 기억해야하지만 블록간 연결 여부도 생각해 봐야함.
    private List<Block> blocks;

    public void addBlock(Block block){
        blocks.add(block);
    }
    public void deleteBlock(Block block){
        blocks.remove(block);
    }

    //TODO Controller로 옮기기
    public void saveBlockBatchModel(){

    }
}
