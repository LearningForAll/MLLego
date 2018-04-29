package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;
import Component.BlockComponent.InputBlock;
import Component.BlockComponent.TrainingBlock;
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
        super(positionX, positionY, blockType);
        this.batchSize = batchSize;
        this.epochSize = epochSize;
        this.optimizer = optimizer;
        this.learningRate = learningRate;
        this.validRatio = validRatio;
    }

    public TrainingBlockTemplate(Block block){
        super(block.getX(), block.getY(), block.getClass().getSimpleName());
        this.batchSize = ((TrainingBlock)block).getBatchSize();
        this.epochSize = ((TrainingBlock)block).getEpoch();
        this.optimizer = ((TrainingBlock)block).getOptimizer();
        this.learningRate = ((TrainingBlock)block).getLearningRate();
        this.validRatio = ((TrainingBlock)block).getValidRatio();

    }


}
