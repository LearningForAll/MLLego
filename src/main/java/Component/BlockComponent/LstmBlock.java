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
    JSlider keepprobJSlider;


    public LstmBlock(){
        keepprobJSlider = new JSlider();

        JLabel nameLabel=new JLabel("LSTM Block");
        TitledBorder line=new TitledBorder(new LineBorder(Color.black));
        GridLayout layout=new GridLayout(2,1);
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        setLayout(layout);
        setBorder(line);
        add(nameLabel);
        add(keepprobJSlider);
        setBackground(new Color(150, 0, 205));
        setVisible(true);
    }

    @Override
    String getBlockAttrStr() {
        return null;
    }




    @Override
    boolean isNextBlockConnectable(Block block) {
        return (block instanceof LayerBlock);
    }

    @Override
    boolean isPreviousBlockConnectable(Block block) {
        return (block instanceof InputBlock || block instanceof LayerBlock);
    }
}
