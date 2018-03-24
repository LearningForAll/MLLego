package Component.BlockComponent;

import javax.swing.*;

/**
 * Created by chaebyeonghun on 2018. 3. 24..
 */
public class PoolingBlock extends LayerBlock {

    // 커널 사이즈랑 스트라이드 설정할 수 있게... 그 뒤에는 코드 generate가 알아서
    private int[] kernel_size;
    private int[] strides;
    private String paddingOption;


    // 커널 사이즈와 스트라이드를 설정할 수 있는 필드들 숫자만 입력할 수 있게 입력을 제한
    JTextField horizontalStrideTextField;
    JTextField verticalStrideTextField;
    JTextField horizontalKernelSize;
    JTextField verticalKernelSize;
    JComboBox paddingOptionCombobox;
    @Override
    String getBlockAttrStr() {
        return null;
    }
}
