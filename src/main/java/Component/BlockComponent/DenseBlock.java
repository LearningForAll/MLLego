package Component.BlockComponent;

import Component.NumberOnlyTextField;

/**
 * Created by chaebyeonghun on 2018. 3. 24..
 */
public class DenseBlock extends LayerBlock {

    // 레이어 갯수와 아웃풋 차원 입력
    NumberOnlyTextField layerTextField;
    // 인풋 디멘션과 default로 같다 값이.
    NumberOnlyTextField outputDimensionTextField;

    public DenseBlock(int inputDimension){
        layerTextField = new NumberOnlyTextField(1, 1, 50);
        outputDimensionTextField = new NumberOnlyTextField(inputDimension, 1, 1000);
    }


    @Override
    String getBlockAttrStr() {
        return null;
    }

    @Override
    void registerNextBlock() {

    }

    @Override
    void registerPreviousBlock() {

    }
}
