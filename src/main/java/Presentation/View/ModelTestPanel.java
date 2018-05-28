package Presentation.View;

import Component.TestBlockComponent.TestBlock;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-03-26.
 */
public class ModelTestPanel extends JPanel {



    public ModelTestPanel(){
        setBackground(Color.white);
        this.setLayout(null);
    }

    public void addTestBlock(TestBlock block){

        this.add(block);
        this.revalidate();
    }
}
