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

    XInputBlock xInputBlock;
    YInputBlock yInputBlock;
    PreprocessorBlock preprocessorBlock;

    public BlockInput(){
        int location=30;

        setLayout(null);
        setBackground(Color.white);

        xInputBlock=new XInputBlock();
        yInputBlock=new YInputBlock();
        preprocessorBlock=new PreprocessorBlock();
        xInputBlock.setLocation(50,location);
        location+=50+xInputBlock.getHeight();
        yInputBlock.setLocation(50,location);
        location+=50+yInputBlock.getHeight();
        preprocessorBlock.setLocation(50,location);

        add(xInputBlock);
        add(yInputBlock);
        add(preprocessorBlock);
        setVisible(true);
    }

    public Block[] getBlockComponents() {
        return new Block[]{xInputBlock,yInputBlock,preprocessorBlock};
    }
}
