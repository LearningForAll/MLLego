package Presentation.View;

import Presentation.Controller.BlockPlacementController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by LG on 2018-03-26.
 */
public class TopMenuPanel extends JPanel {
    JButton btn_run=new JButton(new ImageIcon("images/icon/start.png"));
    JButton btn_pause=new JButton(new ImageIcon("images/icon/stop.png"));
    JButton btn_save=new JButton(new ImageIcon("images/icon/save.png"));
    JButton btn_load=new JButton(new ImageIcon("images/icon/folder.png"));

    public TopMenuPanel(){
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

        btn_load.addActionListener(new LoadButtonListener());

        setVisible(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1700,100);
    }

    private class LoadButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jFileChooser = new JFileChooser();
            //폴더와 디렉토리 둘다 가능
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            // TODO 확장자 정리
            // 폴더를 선택했을 떄의 옵션도 필요하기때문에 주석처
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG","jpg");
            jFileChooser.setFileFilter(filter);
            int ret = jFileChooser.showOpenDialog(null);
            if(ret != JFileChooser.APPROVE_OPTION){
                JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.","경고", JOptionPane.WARNING_MESSAGE);
            }

            //TODO 폴경로는 다 확보했으나 폴더를 받았을 때 파일리스트를 넘겨줄 것인가 아니면 알아서 할 것인지 논의 필요
            String filePath = jFileChooser.getSelectedFile().getPath();
            BlockPlacementController.getInstance().loadBlockBatch(filePath);
        }
    }
    private class SaveButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog(null, "저장할 블록배치 모델의 이름을 입력해주세요");
            BlockPlacementController.getInstance().saveBlockBatch(name);
        }
    }

}
