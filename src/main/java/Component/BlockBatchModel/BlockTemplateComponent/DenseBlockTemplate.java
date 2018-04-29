package Component.BlockBatchModel.BlockTemplateComponent;

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
        super(positionX, positionY, blockType, previousBlocks, nextBlocks);
        this.layerNum = layerNum;
        this.outputDim = outputDim;
        this.activationFunc = activationFunc;
    }
}
