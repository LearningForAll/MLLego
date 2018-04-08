package Presentation.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-03-26.
 */
public class TopMenuPanel extends JPanel {
    JButton btn_run=new JButton(new ImageIcon("images/icon/start.png"));
    JButton btn_pause=new JButton(new ImageIcon("images/icon/stop.png"));
    JButton btn_save=new JButton(new ImageIcon("images/icon/save.png"));
    JButton btn_load=new JButton(new ImageIcon("images/icon/folder.png"));

    TopMenuPanel(){
        btn_run.setBorderPainted(false);
        btn_run.setFocusPainted(false);
        btn_run.setContentAreaFilled(false);
        btn_run.setSize(80,80);
        btn_run.setLocation(0, 10);
        add(btn_run);

        btn_pause.setBorderPainted(false);
        btn_pause.setFocusPainted(false);
        btn_pause.setContentAreaFilled(false);
        btn_pause.setSize(80,80);
        btn_pause.setLocation(30, 10);
        add(btn_pause);

        btn_save.setBorderPainted(false);
        btn_save.setFocusPainted(false);
        btn_save.setContentAreaFilled(false);
        btn_save.setSize(80,80);
        btn_save.setLocation(60, 10);
        add(btn_save);

        btn_load.setBorderPainted(false);
        btn_load.setFocusPainted(false);
        btn_load.setContentAreaFilled(false);
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
