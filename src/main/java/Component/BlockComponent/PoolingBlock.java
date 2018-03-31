package Component.BlockComponent;

import Component.NumberOnlyTextField;
import Const.PaddingOption;

import javax.swing.*;

/**
 * Created by chaebyeonghun on 2018. 3. 24..
 */
public class PoolingBlock extends LayerBlock {

    // 커널 사이즈와 스트라이드를 설정할 수 있는 필드들 숫자만 입력할 수 있게 입력을 제한
    NumberOnlyTextField horizontalStrideTextField;
    NumberOnlyTextField verticalStrideTextField;
    NumberOnlyTextField horizontalKernelSizeTextField;
    NumberOnlyTextField verticalKernelSizeTextField;

    JComboBox<PaddingOption> paddingOptionCombobox;

    public PoolingBlock(){
        paddingOptionCombobox = new JComboBox<>(PaddingOption.values());
        horizontalKernelSizeTextField  = new NumberOnlyTextField(2, 1, 10000);
        verticalKernelSizeTextField = new NumberOnlyTextField(2, 1, 10000);
        horizontalStrideTextField = new NumberOnlyTextField(1, 1, 10000);
        verticalStrideTextField = new NumberOnlyTextField(1, 1, 10000);

    }

    @Override
    String getBlockAttrStr() {
        return null;
    }

    @Override
    void registerNextBlock(Block block) {

    }

    @Override
    void registerPreviousBlock(Block block) {

    }


    @Override
    void getPoint() {

    }

    @Override
    boolean isNextBlockConnectable(Block block) {
        return false;
    }

    @Override
    boolean isPreviousBlockConnectable(Block block) {
        return false;
    }
}
