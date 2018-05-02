package Presentation.View;

import javax.swing.*;

/**
 * Created by LG on 2018-03-26.
 */
public class TemplatePanel extends JPanel {

    JButton mnistCnn;
    JButton xorDnn;
    public TemplatePanel(){
        mnistCnn=new JButton("MNist CNN");
        xorDnn=new JButton("Xor-DNN");
        add(mnistCnn);
        add(xorDnn);
        setVisible(true);
    }
}
