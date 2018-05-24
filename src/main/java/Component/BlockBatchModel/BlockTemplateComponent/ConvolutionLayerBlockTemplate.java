package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;
import Component.BlockComponent.ConvolutionLayerBlock;
import Component.BlockComponent.LayerBlock;
import Const.ActivationFunc;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class ConvolutionLayerBlockTemplate extends BlockTemplate{


    private int kernelSize;
    private int horizontalKernelSize;
    private int verticalKernelSize;
    private int keepProb;
    private int convDimension;

    private int extendSize;
    private int connectedSize;
    private ActivationFunc func;

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
        this.extendSize = ((LayerBlock)block).getExtendSize();
        this.connectedSize = ((LayerBlock)block).getConnectedSize();
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

    public int getExtendSize(){
        return extendSize;
    }
    public int getConnectedSize(){
        return connectedSize;
    }

}
