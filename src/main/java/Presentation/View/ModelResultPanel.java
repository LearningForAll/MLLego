package Presentation.View;

import Component.MyModelComponent.MyModelComponent;
import Models.ModelTestResultArray;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

/**
 * Created by LG on 2018-05-26.
 */

//모델 테스트에 대한 모델 이름, 그래프, 정확도, cost가 나오는 패널
public class ModelResultPanel extends JPanel {
    //TODO:: dataNum 받아오는 것으로 수정 필요
    int dataNum;//데이터 갯수를 받아와야함
    JLabel modelLabel;
    JLabel graph_1;
    JLabel graph_2;
    java.util.List<ModelTestResultArray> modelTestResultArrays;

    ModelResultPanel(){
        dataNum=getModelList().size();
        setLayout(new GridLayout(dataNum+1, 3));
        modelTestResultArrays = new ArrayList<>();
        modelLabel=new JLabel("Model Name");
        graph_1=new JLabel("Graph 1");
        graph_2=new JLabel("Graph 2");
        add(modelLabel);
        add(graph_1);
        add(graph_2);

        for(int i=0; i<getModelList().size(); i++){
            ModelTestResultArray modelTestResultArray=new ModelTestResultArray(getModelList().get(i), getGraph1List().get(i), getGraph2List().get(i));
            modelTestResultArrays.add(modelTestResultArray);
            add(modelTestResultArray);
        }

        setBackground(Color.WHITE);
        setVisible(true);
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


    public java.util.List<String> getGraph1List(){
        List<String> graph1List = new ArrayList<>();
        String currentDir=System.getProperty("user.dir");
        String folderDir=currentDir+"/bin/";
        System.out.println(folderDir);

        File dir = new File(folderDir);
        File[] fileList = dir.listFiles();
        try{
            for(int i = 0 ; i < fileList.length ; i++){
                File file = fileList[i];
                if(file.isDirectory()){
                    System.out.println("디렉토리 이름 = " + file.getName());
                    //graph1List.add(folderDir+file.getName()+"/train.png");
                    graph1List.add("/bin/"+file.getName()+"/train.png");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return graph1List;
    }



    public java.util.List<String> getGraph2List(){
        List<String> graph2List = new ArrayList<>();
        String currentDir=System.getProperty("user.dir");
        String folderDir=currentDir+"/bin/";
        System.out.println(folderDir);

        File dir = new File(folderDir);
        File[] fileList = dir.listFiles();
        try{
            for(int i = 0 ; i < fileList.length ; i++){
                File file = fileList[i];
                if(file.isDirectory()){
                    System.out.println("디렉토리 이름 = " + file.getName());
                    //graph2List.add(folderDir+file.getName()+"/validate.png");
                    graph2List.add("/bin/"+file.getName()+"/validate.png");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return graph2List;
    }

}
