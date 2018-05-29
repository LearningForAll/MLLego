package Presentation.Controller;

import Models.ModelTestResultArray;
import Presentation.View.ModelResultPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.io.File;
import java.util.*;

/**
 * Created by LG on 2018-05-29.
 */
public class ModelResultController {
    private ModelResultPanel modelResultPanel;
    private static ModelResultController instance = new ModelResultController();
    JLabel modelLabel;
    JLabel graph1_label;
    JLabel graph2_label;
    private int dataNum=0;
    private ArrayList<ModelTestResultArray> modelTestResultArrayList;

    ModelResultController(){
        modelTestResultArrayList=new ArrayList<ModelTestResultArray>();
    }

    public static ModelResultController getInstance() {
        return instance;
    }

    public void setPanel(ModelResultPanel modelResultPanel){
        this.modelResultPanel=modelResultPanel;
    }

    public void addArray(ModelTestResultArray modelTestResult){
        modelTestResultArrayList.add(modelTestResult);
    }

    public void updatePanel(){
        modelResultPanel.updatePanel(modelTestResultArrayList);
    }

    private java.util.List<String> getModelList(){

        java.util.List<String> modelList = new ArrayList<>();
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
