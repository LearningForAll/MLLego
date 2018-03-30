package Component.BlockComponent;

import Component.NumberOnlyTextField;
import Const.ActivationFunc;
import Models.Coords;

import javax.swing.*;

public class ConvolutionLayerBlock extends LayerBlock {

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
    void registerNextBlock() {

    }

    @Override
    void registerPreviousBlock() {

    }
}
