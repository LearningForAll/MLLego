package Presentation.View;

import App.MyApp;
import Presentation.Controller.BlockPlacementController;
import Presentation.Controller.TemplateController;
import Util.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LG on 2018-03-26.
 */
public class TemplatePanel extends JPanel {

    JButton mnistCnn;
    JButton xorDnn;
    JLabel mnistCnnLabel;
    JLabel xorDnnLabel;
    TemplateController templateController;

    public TemplatePanel() {
        setBackground(Color.white);
        List<String> fileNameList = getAllFileName();
        mnistCnnLabel=new JLabel("Mnist Cnn");
        xorDnnLabel=new JLabel("Xor Dnn");
        mnistCnn = new JButton(new ImageIcon(FileUtil.getResourcePath("icon/mnistCnn.png")));
        xorDnn = new JButton(new ImageIcon(FileUtil.getResourcePath("icon/xorDnn.png")));
        mnistCnn.setBorderPainted(false);
        mnistCnn.setFocusPainted(false);
        mnistCnn.setContentAreaFilled(false);
        xorDnn.setBorderPainted(false);
        xorDnn.setFocusPainted(false);
        xorDnn.setContentAreaFilled(false);

        add(mnistCnn);
        add(xorDnn);
        add(mnistCnnLabel);
        add(xorDnnLabel);
        setVisible(true);
    }

    private String getTemplateDirectory(){
        File file = new File("");
        return (file.getAbsolutePath() + "/bin/" + MyApp.templateFolder + "/");
    }

    private List<String> getAllFileName(){
        List<String> returnFileList = new ArrayList<>();
        File file = new File(getTemplateDirectory());
        File []fileList = file.listFiles();

        //오류나서 일단 주석처리 해놓음
        /*for(File tempFile : fileList) {
            String fileExtension = tempFile.getName().substring(tempFile.getName().lastIndexOf(".")+1,tempFile.getName().length());
            if(tempFile.isFile() && fileExtension.equals(".block")) {
                returnFileList.add(tempFile.getParent());
            }
        }
        */

        return returnFileList;
    }

    //각각의 템플릿 버튼 누르면 액션이벤트 발생하게 해서 해당되는 파일 보내기
    private class MnistCnnButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BlockPlacementController.getInstance().loadBlockBatch("bin/inner/mnistCnn.block");
        }
    }

    private class XorDnnButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BlockPlacementController.getInstance().loadBlockBatch("bin/inner/xorDnn.block");
        }
    }



}
