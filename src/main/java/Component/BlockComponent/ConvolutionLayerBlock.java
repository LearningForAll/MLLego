package Component.BlockComponent;

import Models.Coords;

import javax.swing.*;

public class ConvolutionLayerBlock extends LayerBlock {

    private String activationFunction;
    private float keep_prob;
    private int filterNum;
    private int[] kernelSize;
    private int filterSize;

    // 드롭아웃 레이트를 조정할 수 있는 JSlider
    JSlider keepprobJSlider;
    JComboBox<String> activationFunctionCombobox;
    JTextField fileterNumTextField;
    JTextField filterSizeTextField;
    JTextField horizontalKernelSize;
    JTextField verticalKernelSize;

    @Override
    String getBlockAttrStr() {
        return null;
    }
}
