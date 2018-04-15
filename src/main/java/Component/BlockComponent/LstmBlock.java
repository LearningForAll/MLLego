package Component.BlockComponent;

import Component.NumberOnlyTextField;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by chaebyeonghun on 2018. 3. 27..
 */
public class LstmBlock extends LayerBlock {

    NumberOnlyTextField stackSizeTextField;
    // 드롭아웃 레이트를 조정할 수 있는 JSlider
    JSlider keepProbJSlider;


    public LstmBlock(){
        super("LSTM Block");

        keepProbJSlider = new JSlider();
        GridLayout layout=new GridLayout(2,1);
        setLayout(layout);
        setSize(200,50);
        add(flowPanel);
        add(keepProbJSlider);
        setVisible(true);
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

    public int getKeepProb() {
        return keepProbJSlider.getValue();
    }
}
