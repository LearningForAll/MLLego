package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;
import Component.BlockComponent.PoolingBlock;
import Const.PaddingOption;
import Const.PoolingType;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class PoolingBlockTemplate extends BlockTemplate {

    int horizontalKernelSize;
    int verticalKernelSize;
    int horizontalStride;
    int verticalStride;
    PaddingOption paddingOption;
    PoolingType poolingType;
    int extendSize;
    int connectSize;

    public int getHorizontalKernelSize() {
        return horizontalKernelSize;
    }

    public void setHorizontalKernelSize(int horizontalKernelSize) {
        this.horizontalKernelSize = horizontalKernelSize;
    }

    public int getVerticalKernelSize() {
        return verticalKernelSize;
    }

    public void setVerticalKernelSize(int verticalKernelSize) {
        this.verticalKernelSize = verticalKernelSize;
    }

    public int getHorizontalStride() {
        return horizontalStride;
    }

    public void setHorizontalStride(int horizontalStride) {
        this.horizontalStride = horizontalStride;
    }

    public int getVerticalStride() {
        return verticalStride;
    }

    public void setVerticalStride(int verticalStride) {
        this.verticalStride = verticalStride;
    }

    public PaddingOption getPaddingOption() {
        return paddingOption;
    }

    public void setPaddingOption(PaddingOption paddingOption) {
        this.paddingOption = paddingOption;
    }

    public PoolingType getPoolingType() {
        return poolingType;
    }

    public void setPoolingType(PoolingType poolingType) {
        this.poolingType = poolingType;
    }

    public PoolingBlockTemplate(int positionX, int positionY, String blockType,
                                int horizontalKernelSize, int verticalKernelSize,
                                int horizontalStride, int verticalStride, PaddingOption paddingOption,
                                PoolingType poolingType) {
        super(positionX, positionY, blockType);
        this.horizontalKernelSize = horizontalKernelSize;
        this.verticalKernelSize = verticalKernelSize;
        this.horizontalStride = horizontalStride;
        this.verticalStride = verticalStride;
        this.paddingOption = paddingOption;
        this.poolingType = poolingType;
    }

    public PoolingBlockTemplate(Block block) {
        super(block.getX(), block.getY(), block.getClass().getSimpleName());
        this.horizontalKernelSize = ((PoolingBlock) block).getHorizonKernel();
        this.verticalKernelSize = ((PoolingBlock) block).getVerticalKernel();
        this.horizontalStride = ((PoolingBlock) block).getHorizonStride();
        this.verticalStride = ((PoolingBlock) block).getVerticalStride();
        this.paddingOption = ((PoolingBlock) block).getPaddingOption();
        this.poolingType = ((PoolingBlock) block).getPoolingType();
        this.extendSize = ((PoolingBlock) block).getExtendSize();
        this.connectSize = ((PoolingBlock) block).getConnectedSize();
        this.extended = block.isBlockJustExtended();
        this.reducted = block.getReducted();
    }

    public int getExtendSize(){
        return extendSize;
    }
    public int getConnectSize(){
        return connectSize;
    }
}
