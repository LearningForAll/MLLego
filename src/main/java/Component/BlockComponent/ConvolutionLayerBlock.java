package Component.BlockComponent;

import Component.NumberOnlyTextField;
import Const.ActivationFunc;
import Models.Coords;

import javax.swing.*;

public class ConvolutionLayerBlock extends LayerBlock {
    int convDimension = 2; // 1D or 2D for convolution

    ActivationFunc activationFunc;

    // 드롭아웃 레이트를 조정할 수 있는 JSlider
    JSlider keepprobJSlider;
    JComboBox<String> activationFunctionCombobox;
    // 필터 개수
    NumberOnlyTextField kernelNumTextField;
    NumberOnlyTextField horizontalKernelSize;
    NumberOnlyTextField verticalKernelSize;

    @Override
    String getBlockAttrStr() {
        return null;
    }
}
