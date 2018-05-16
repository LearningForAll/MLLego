package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;
import Component.BlockComponent.ConvolutionLayerBlock;
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
    int convDimension;
    ActivationFunc func;

    public ConvolutionLayerBlockTemplate(int positionX, int positionY, String blockType,
                                         int kernelSize, int horizontalKernelSize, int verticalKernelSize,
                                         int keepProb, ActivationFunc func) {
        super(positionX, positionY, blockType);
        this.kernelSize = kernelSize;
        this.horizontalKernelSize = horizontalKernelSize;
        this.verticalKernelSize = verticalKernelSize;
        this.keepProb = keepProb;
        this.func = func;
    }
    public ConvolutionLayerBlockTemplate(Block block){
        super(block.getX(), block.getY(), block.getClass().getSimpleName());
        this.kernelSize = ((ConvolutionLayerBlock)block).getKernelNum();
        this.keepProb = ((ConvolutionLayerBlock)block).getKeepProb();
        this.horizontalKernelSize = ((ConvolutionLayerBlock)block).getHorizonKernelSize();
        this.verticalKernelSize = ((ConvolutionLayerBlock)block).getVerticalKernelSize();
        this.func = ((ConvolutionLayerBlock)block).getActivationFunction();
    }

    public ActivationFunc getFunc() {
        return func;
    }

    public int getHorizontalKernelSize() {
        return horizontalKernelSize;
    }

    public int getKeepProb() {
        return keepProb;
    }

    public int getKernelSize() {
        return kernelSize;
    }

    public int getVerticalKernelSize() {
        return verticalKernelSize;
    }
}
