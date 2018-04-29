package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;
import Component.BlockComponent.DenseBlock;
import Const.ActivationFunc;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class DenseBlockTemplate extends BlockTemplate {

    int layerNum;
    int outputDim;
    ActivationFunc activationFunc;
    public DenseBlockTemplate(int positionX, int positionY, String blockType,
                              List<BlockTemplate> previousBlocks, List<BlockTemplate> nextBlocks,
                              int layerNum, int outputDim, ActivationFunc activationFunc) {
        super(positionX, positionY, blockType);
        this.layerNum = layerNum;
        this.outputDim = outputDim;
        this.activationFunc = activationFunc;
    }
    public DenseBlockTemplate(Block block){
        super(block.getX(), block.getY(), block.getClass().getSimpleName());
        this.layerNum = ((DenseBlock)block).getLayerNum();
        this.outputDim = ((DenseBlock)block).getOutputDim();
        this.activationFunc = ((DenseBlock)block).getActivationFunction();
    }
}
