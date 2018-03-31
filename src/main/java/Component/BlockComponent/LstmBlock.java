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


    public LstmBlock(){
        keepprobJSlider = new JSlider();
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
