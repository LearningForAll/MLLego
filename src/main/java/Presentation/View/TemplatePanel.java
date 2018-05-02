package Presentation.View;

import App.MyApp;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LG on 2018-03-26.
 */
public class TemplatePanel extends JPanel {

    //파일을 읽어와서 파일리스트를 만듬
    public TemplatePanel(){

        List<String> fileNameList = getAllFileName();
    }

    private String getTemplateDirectory(){
        File file = new File("");
        return (file.getAbsolutePath() + "/bin/" + MyApp.templateFolder + "/");
    }

    private List<String> getAllFileName(){
        List<String> returnFileList = new ArrayList<>();
        File file = new File(getTemplateDirectory());
        File []fileList = file.listFiles();
        for(File tempFile : fileList) {
            String fileExtension = tempFile.getName().substring(tempFile.getName().lastIndexOf(".")+1,tempFile.getName().length());
            if(tempFile.isFile() && fileExtension.equals(".block")) {
                returnFileList.add(tempFile.getParent());
            }
        }
        return returnFileList;
    }


}
