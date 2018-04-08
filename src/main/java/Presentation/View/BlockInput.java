package Presentation.View;

import Component.BlockComponent.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-03-30.
 */

//인풋과 관련된 블록들이 오는 패널
public class BlockInput extends JPanel {

    InputBlock inputBlock;
    PreprocessorBlock preprocessorBlock;

    public BlockInput(){
        setLayout(null);
        inputBlock=new InputBlock();
        preprocessorBlock=new PreprocessorBlock();
        inputBlock.setSize(200,80);
        inputBlock.setLocation(150,20);
        preprocessorBlock.setSize(200,50);
        preprocessorBlock.setLocation(150,120);

        add(inputBlock);
        add(preprocessorBlock);

        setVisible(true);
    }

}
