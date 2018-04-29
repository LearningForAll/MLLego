package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;
import Const.Classifier;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class ClassifierBlockTemplate extends BlockTemplate{


    Classifier classifier;
    private BlockTemplate xPartBlock,yPartBlock;

    public ClassifierBlockTemplate(int positionX, int positionY, String blockType,
                                   List<BlockTemplate> previousBlocks, List<BlockTemplate> nextBlocks,
                                   Classifier classifier, BlockTemplate xPartBlock, BlockTemplate yPartBlock) {
        super(positionX, positionY, blockType, previousBlocks, nextBlocks);
        this.classifier = classifier;
        this.xPartBlock = xPartBlock;
        this.yPartBlock = yPartBlock;

    }
}
