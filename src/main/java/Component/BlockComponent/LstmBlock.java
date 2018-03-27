package Component.BlockComponent;

import Component.NumberOnlyTextField;

import javax.swing.*;

/**
 * Created by chaebyeonghun on 2018. 3. 27..
 */
public class LstmBlock extends LayerBlock {


    NumberOnlyTextField stackSizeTextField;
    // 드롭아웃 레이트를 조정할 수 있는 JSlider
    JSlider keepprobJSlider;

    @Override
    String getBlockAttrStr() {
        return null;
    }
}
