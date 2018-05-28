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

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public int getEpochSize() {
        return epochSize;
    }

    public void setEpochSize(int epochSize) {
        this.epochSize = epochSize;
    }

    public Optimizer getOptimizer() {
        return optimizer;
    }

    public void setOptimizer(Optimizer optimizer) {
        this.optimizer = optimizer;
    }

    public float getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(float learningRate) {
        this.learningRate = learningRate;
    }

    public float getValidRatio() {
        return validRatio;
    }

    public void setValidRatio(float validRatio) {
        this.validRatio = validRatio;
    }

    public TrainingBlockTemplate(int positionX, int positionY, String blockType,

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
        this.extended = block.isBlockJustExtended();

    }


}
