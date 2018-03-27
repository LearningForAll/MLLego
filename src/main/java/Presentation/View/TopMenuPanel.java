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
        setBackground(Color.white);

        btn_run.setSize(80,80);
        btn_run.setLocation(0, 10);
        add(btn_run);

        btn_pause.setSize(80,80);
        btn_pause.setLocation(30, 10);
        add(btn_pause);

        btn_save.setSize(80,80);
        btn_save.setLocation(60, 10);
        add(btn_save);

        btn_load.setSize(80,80);
        btn_load.setLocation(90, 10);
        add(btn_load);

        setVisible(true);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1700,100);
    }


}
