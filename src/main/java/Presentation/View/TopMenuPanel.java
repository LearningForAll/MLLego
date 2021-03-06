package Presentation.View;

import Presentation.Controller.TopMenuActionListener;
import Presentation.Controller.BlockPlacementController;
import Presentation.Controller.BlockPlacementDefaultController;
import Presentation.Controller.TopmenuController;
import Presentation.Observer.TopmenuPublisher;
import Util.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by LG on 2018-03-26.
 */
public class TopMenuPanel extends JPanel implements TopmenuPublisher{
    JButton btn_run;
    JButton btn_pause;
    JButton btn_save;
    JButton btn_load;
    JButton btn_reset;
    TopmenuController controller;


    public TopMenuPanel() {
        //setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
        setBackground(new Color(0, 71, 113));

        btn_run=new JButton(new ImageIcon(FileUtil.getResourcePath("icon/start.png")));
        btn_pause=new JButton(new ImageIcon(FileUtil.getResourcePath("icon/stop.png")));
        btn_save=new JButton(new ImageIcon(FileUtil.getResourcePath("icon/save.png")));
        btn_load=new JButton(new ImageIcon(FileUtil.getResourcePath("icon/folder.png")));
        btn_reset = new JButton(new ImageIcon(FileUtil.getResourcePath("icon/recycle.png")));

        btn_run.setBorderPainted(false);
        btn_run.setFocusPainted(false);
        btn_run.setContentAreaFilled(false);
        btn_run.setSize(20, 20);
        btn_run.setLocation(0, 10);
        add(btn_run);
        btn_run.addActionListener(new TopMenuActionListener("run"));

        btn_pause.setBorderPainted(false);
        btn_pause.setFocusPainted(false);
        btn_pause.setContentAreaFilled(false);
        btn_pause.setSize(10, 10);
        btn_pause.setLocation(30, 10);
        add(btn_pause);
        btn_pause.addActionListener(new TopMenuActionListener("stop"));

        btn_load.setBorderPainted(false);
        btn_load.setFocusPainted(false);
        btn_load.setContentAreaFilled(false);
        btn_load.setSize(10, 10);
        btn_load.setLocation(90, 10);
        add(btn_load);

        btn_save.setBorderPainted(false);
        btn_save.setFocusPainted(false);
        btn_save.setContentAreaFilled(false);
        btn_save.setSize(10, 10);
        btn_save.setLocation(60, 10);
        add(btn_save);

        btn_reset.setBorderPainted(false);
        btn_reset.setFocusPainted(false);
        btn_reset.setContentAreaFilled(false);
        btn_reset.setSize(10, 10);
        btn_reset.setLocation(60, 10);
        add(btn_reset);


        btn_load.addActionListener(new LoadButtonListener());
        btn_save.addActionListener(new SaveButtonListener());
        btn_reset.addActionListener(new ResetButtonListener());

        setVisible(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1700, 100);
    }

    @Override
    public void setObserver(TopmenuController controller) {
        this.controller = controller;
    }

    private class LoadButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.loadBatchModel();
        }
    }

    private class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.saveBatchModel();
        }
    }

    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){

            BlockPlacementDefaultController.getInstance().changeBlockPlacementTab();
           int result =  JOptionPane.showConfirmDialog(null, "현재 화면에있는 모든 블록을 삭제합니다. 계속 하시겠습니까?", "경고",JOptionPane.YES_NO_OPTION);

           if(result == JOptionPane.YES_OPTION){
                // if yes
               BlockPlacementController.getInstance().resetAllBlockPlacement();
           }else {

           }

        }
    }

}
