package Component.BlockBatchModel.BlockTemplateComponent;

import Const.Optimizer;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class TrainingBlockTemplate extends BlockTemplate{

    int batchSize;
    int epochSize;
    Optimizer optimizer;
    float learningRate;
    float validRatio;

    public TrainingBlockTemplate(int positionX, int positionY, String blockType,
                                 List<BlockTemplate> previousBlocks, List<BlockTemplate> nextBlocks,
                                 int batchSize, int epochSize, Optimizer optimizer, float learningRate, float validRatio) {
        super(positionX, positionY, blockType, previousBlocks, nextBlocks);
        this.batchSize = batchSize;
        this.epochSize = epochSize;
        this.optimizer = optimizer;
        this.learningRate = learningRate;
        this.validRatio = validRatio;
    }


}
