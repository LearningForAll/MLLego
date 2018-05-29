package Presentation.View;

import Component.MyModelComponent.MyModelComponent;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LG on 2018-03-26.
 */
public class MyModelPanel extends JPanel {
    JScrollPane scrollPane;
    List<MyModelComponent> modelComponents;


    public MyModelPanel(){

        scrollPane = new JScrollPane();
        setBackground(Color.white);
        modelComponents = new ArrayList<>();
        setVisible(true);
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setVgap(10);
        flowLayout.setHgap(40);
        setLayout(flowLayout);

        for(String modelName : getModelList()){
            MyModelComponent modelComponent = new MyModelComponent(modelName);
            modelComponents.add(modelComponent);
            add(modelComponent);
        }


    }

    private List<String> getModelList(){

        List<String> modelList = new ArrayList<>();
        String currentDir = System.getProperty("user.dir");
        String folderDir = currentDir+"/bin/";

        System.out.println(folderDir);

        File dir = new File(folderDir);
        File[] fileList = dir.listFiles();
        try{
            for(int i = 0 ; i < fileList.length ; i++){
                File file = fileList[i];
                if(file.isDirectory()){
                    System.out.println("디렉토리 이름 = " + file.getName());
                    modelList.add(file.getName());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return modelList;
    }

}
