package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class ModelBlockTemplate extends BlockTemplate {

     public ModelBlockTemplate(int positionX, int positionY, String blockType) {
        super(positionX, positionY, blockType);
    }
    public ModelBlockTemplate(Block block){
        super(block.getX(), block.getY(), block.getClass().getSimpleName());
    }
}
