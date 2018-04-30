package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;
import Component.BlockComponent.ClassifierBlock;
import Const.Classifier;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class ClassifierBlockTemplate extends BlockTemplate{


    Classifier classifier;
    private BlockTemplate xPartBlock,yPartBlock;


    public ClassifierBlockTemplate(int positionX, int positionY, String blockType,
                                   Classifier classifier) {
        super(positionX, positionY, blockType);
        this.classifier = classifier;
        this.xPartBlock = xPartBlock;
        this.yPartBlock = yPartBlock;

    }
    public ClassifierBlockTemplate(Block block){
        super(block.getX(), block.getY(), block.getClass().getSimpleName());
        this.classifier = ((ClassifierBlock)block).getClassifier();
    }

    public void setxPartBlock(BlockTemplate xPartBlock) {
        this.xPartBlock = xPartBlock;
    }

    public void setyPartBlock(BlockTemplate yPartBlock) {
        this.yPartBlock = yPartBlock;
    }

    public BlockTemplate getxPartBlock() {
        return xPartBlock;
    }

    public BlockTemplate getyPartBlock() {
        return yPartBlock;
    }

    public Classifier getClassifier() {
        return classifier;
    }
}
