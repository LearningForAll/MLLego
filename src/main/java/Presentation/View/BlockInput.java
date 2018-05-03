package Presentation.View;

import Component.BlockComponent.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-03-30.
 */

//인풋과 관련된 블록들이 오는 패널

//Preprocessor, input이 옴
public class BlockInput extends JPanel {

    InputBlock inputBlock;
    PreprocessorBlock preprocessorBlock;

    public BlockInput(){
        setLayout(null);
        inputBlock=new InputBlock();
        preprocessorBlock=new PreprocessorBlock();
        inputBlock.setLocation(50,20);
        preprocessorBlock.setLocation(50,120);

        add(inputBlock);
        add(preprocessorBlock);
        setVisible(true);
    }

    public Block[] getBlockComponents() {
        return new Block[]{inputBlock,preprocessorBlock};
    }
}
