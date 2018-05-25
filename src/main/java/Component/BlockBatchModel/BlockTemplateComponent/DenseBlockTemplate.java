package Component.BlockBatchModel.BlockTemplateComponent;

import Component.BlockComponent.Block;
import Component.BlockComponent.DenseBlock;
import Const.ActivationFunc;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 29..
 */
public class DenseBlockTemplate extends BlockTemplate {

    int layerNum;
    int outputDim;
    ActivationFunc activationFunc;
    int extendSize;
    int connectedSize;

    public DenseBlockTemplate(int positionX, int positionY, String blockType,
                              int layerNum, int outputDim, ActivationFunc activationFunc) {
        super(positionX, positionY, blockType);
        this.layerNum = layerNum;
        this.outputDim = outputDim;
        this.activationFunc = activationFunc;
    }
    public DenseBlockTemplate(Block block){
        super(block.getX(), block.getY(), block.getClass().getSimpleName());
        this.layerNum = ((DenseBlock)block).getLayerNum();
        this.outputDim = ((DenseBlock)block).getOutputDim();
        this.activationFunc = ((DenseBlock)block).getActivationFunction();
        this.extendSize = ((DenseBlock) block).getExtendSize();
        this.connectedSize = ((DenseBlock) block).getConnectedSize();
        this.extended = block.isBlockJustExtended();
    }

    public int getOutputDim() {
        return outputDim;
    }

    public void setOutputDim(int outputDim) {
        this.outputDim = outputDim;
    }

    public ActivationFunc getActivationFunc() {
        return activationFunc;
    }

    public void setActivationFunc(ActivationFunc activationFunc) {
        this.activationFunc = activationFunc;
    }

    public int getLayerNum() {
        return layerNum;
    }

    public void setLayerNum(int layerNum) {
        this.layerNum = layerNum;
    }

    public int getExtendSize(){
        return extendSize;
    }
    public int getConnectedSize(){
        return connectedSize;
    }




}
