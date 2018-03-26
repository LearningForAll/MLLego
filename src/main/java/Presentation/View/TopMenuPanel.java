package Presentation.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-03-26.
 */
public class TopMenuPanel extends JPanel {
    JButton btn_run=new JButton("run");
    JButton btn_pause=new JButton("pause");
    JButton btn_save=new JButton("save");
    JButton btn_load=new JButton("load");

    TopMenuPanel(){
        setLayout(null);

        btn_run.setSize(80,80);
        btn_run.setLocation(0, 10);
        btn_run.setContentAreaFilled(false);
        btn_run.setBorderPainted(false);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1200,100);
    }
}
