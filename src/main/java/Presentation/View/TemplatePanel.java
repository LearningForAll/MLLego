package Presentation.View;

import App.MyApp;
import Presentation.Controller.TemplateController;

import javax.swing.*;
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
    TemplateController templateController;

    public TemplatePanel() {
        List<String> fileNameList = getAllFileName();
        mnistCnn = new JButton("MNist CNN");
        xorDnn = new JButton("Xor-DNN");
        add(mnistCnn);
        add(xorDnn);
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

        /*for(File tempFile : fileList) {
            String fileExtension = tempFile.getName().substring(tempFile.getName().lastIndexOf(".")+1,tempFile.getName().length());
            if(tempFile.isFile() && fileExtension.equals(".block")) {
                returnFileList.add(tempFile.getParent());
            }
        }
        */

        return returnFileList;
    }

    private class MnistCnnButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //templateController.loadBatchModel();
        }
    }

    private class XorDnnButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //templateController.loadBatchModel();
        }
    }



}
