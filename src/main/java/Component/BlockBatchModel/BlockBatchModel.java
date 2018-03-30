package Component.BlockBatchModel;

import Component.BlockComponent.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 3. 30..
 */
public class BlockBatchModel {

    private List<Block> blocks;



    public void addBlock(Block block){
        blocks.add(block);
    }
    public void deleteBlock(Block block){
        blocks.remove(block);
    }
}
