package Component.BlockComponent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by chaebyeonghun on 2018. 3. 27..
 */
public class PreprocessorBlock extends Block {


    public PreprocessorBlock(){
        JLabel nameLabel=new JLabel("Preprocessor Block");
        nameLabel.setForeground(Color.white);
        add(nameLabel);
        setBackground(new Color(243,115,50));
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
        return (block instanceof InputBlock);
    }
}
