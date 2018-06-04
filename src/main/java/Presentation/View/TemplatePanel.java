package Presentation.View;

import App.MyApp;
import Component.MyModelComponent.MyModelComponent;
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

    JButton mnistCnn, xorDnn;
    JLabel mnistLabel, xorLabel;
    JPanel mnistPanel, xorPanel;

    TemplateController templateController;

    public TemplatePanel() {
        setBackground(Color.white);
        List<String> fileNameList = getAllFileName();

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setVgap(25);
        flowLayout.setHgap(40);
        setLayout(flowLayout);

        MyModelComponent mnistCNN = new MyModelComponent("mnistCnn","template_block", 1);
        MyModelComponent xorDNN = new MyModelComponent("xorDnn","template_block", 1);


        add(mnistCNN);
        add(xorDNN);


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
