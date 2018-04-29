package Component.BlockBatchModel.BlockTemplateComponent;

import Const.ActivationFunc;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class ConvolutionLayerBlockTemplate extends BlockTemplate{


    int kernelSize;
    int horizontalKernelSize;
    int verticalKernelSize;
    int keepProb;
    ActivationFunc func;

    public ConvolutionLayerBlockTemplate(int positionX, int positionY, String blockType,
                                         List<BlockTemplate> previousBlocks, List<BlockTemplate> nextBlocks,
                                         int kernelSize, int horizontalKernelSize, int verticalKernelSize,
                                         int keepProb, ActivationFunc func) {
        super(positionX, positionY, blockType, previousBlocks, nextBlocks);
        this.kernelSize = kernelSize;
        this.horizontalKernelSize = horizontalKernelSize;
        this.verticalKernelSize = verticalKernelSize;
        this.keepProb = keepProb;
        this.func = func;
    }
}
