package Presentation.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-03-26.
 */
public class MyModelPanel extends JPanel {
    JButton btn_mymodel=new JButton("My Model");
    JButton btn_proceed=new JButton("Proceed");
    JButton btn_result=new JButton("Result");
    JButton btn_template=new JButton("Template");

    MyModelPanel(){

        btn_mymodel.setSize(80,80);
        btn_mymodel.setLocation(10,10);
        add(btn_mymodel);

        btn_template.setSize(80,80);
        btn_template.setLocation(10,10);
        add(btn_template);

        btn_result.setSize(80,80);
        btn_result.setLocation(10,10);
        add(btn_result);

        btn_proceed.setSize(80,80);
        btn_proceed.setLocation(10,10);
        add(btn_proceed);

        setBackground(Color.gray);
        setVisible(true);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1700,200);
    }
}
