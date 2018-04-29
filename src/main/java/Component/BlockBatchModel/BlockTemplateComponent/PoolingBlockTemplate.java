package Component.BlockBatchModel.BlockTemplateComponent;

import Const.PaddingOption;
import Const.PoolingType;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class PoolingBlockTemplate extends BlockTemplate{

    int horizontalKernelSize;
    int verticalKernelSize;
    int horizontalStride;
    int verticalStride;
    PaddingOption paddingOption;
    PoolingType poolingType;

    public PoolingBlockTemplate(int positionX, int positionY, String blockType,
                                List<BlockTemplate> previousBlocks, List<BlockTemplate> nextBlocks,
                                int horizontalKernelSize, int verticalKernelSize,
                                int horizontalStride, int verticalStride, PaddingOption paddingOption,
                                PoolingType poolingType) {
        super(positionX, positionY, blockType, previousBlocks, nextBlocks);
        this.horizontalKernelSize = horizontalKernelSize;
        this.verticalKernelSize = verticalKernelSize;
        this.horizontalStride = horizontalStride;
        this.verticalStride = verticalStride;
        this.paddingOption = paddingOption;
        this.poolingType = poolingType;
    }
}
