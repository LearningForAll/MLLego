package Component.BlockComponent;

import Component.NumberOnlyTextField;
import Const.PaddingOption;

import javax.swing.*;

/**
 * Created by chaebyeonghun on 2018. 3. 24..
 */
public class PoolingBlock extends LayerBlock {

    PaddingOption paddingOption;

    // 커널 사이즈와 스트라이드를 설정할 수 있는 필드들 숫자만 입력할 수 있게 입력을 제한
    NumberOnlyTextField horizontalStrideTextField;
    NumberOnlyTextField verticalStrideTextField;
    NumberOnlyTextField horizontalKernelSize;
    NumberOnlyTextField verticalKernelSize;

    JComboBox paddingOptionCombobox;
    @Override
    String getBlockAttrStr() {
        return null;
    }
}
