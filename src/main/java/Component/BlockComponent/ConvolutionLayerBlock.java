package Component.BlockComponent;

import Component.NumberOnlyTextField;
import Const.ActivationFunc;
import javax.swing.*;

public class ConvolutionLayerBlock extends LayerBlock {
    int convDimension = 2; // 1D or 2D for convolution

    ActivationFunc activationFunc;

    // 드롭아웃 레이트를 조정할 수 있는 JSlider
    JSlider keepprobJSlider;
    JComboBox<ActivationFunc> activationFunctionCombobox;
    // 필터 개수
    NumberOnlyTextField kernelNumTextField;
    NumberOnlyTextField horizontalKernelSize;
    NumberOnlyTextField verticalKernelSize;

    public ConvolutionLayerBlock(){
        keepprobJSlider = new JSlider();
        // Enum으로 값 ㅁ만듬
        activationFunctionCombobox = new JComboBox<>(ActivationFunc.values());

        // 커널 갯수 default 는 1 최소값도 1 max는 1000 유동적으로 조정
        kernelNumTextField = new NumberOnlyTextField(1, 1, 1000);
        horizontalKernelSize = new NumberOnlyTextField(2, 1, 1000);
        verticalKernelSize = new NumberOnlyTextField(2, 1, 1000);
    }


    @Override
    String getBlockAttrStr() {
        return null;
    }

    @Override
    public boolean isNextBlockConnectable(Block block) {
        return (block instanceof LayerBlock);
    }

    @Override
    public boolean isPreviousBlockConnectable(Block block) {
        // InputBlock, PoolingBlock, 그리고 같은 ConvolutionLayerBlock을 이전 블록으로 받을 수 있음.
        return (block instanceof InputBlock || block instanceof LayerBlock);
    }

    @Override
    public boolean isNextBlockConnected() {
        return false;
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return false;
    }
}
