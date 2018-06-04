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
    JScrollPane scrollPane=new JScrollPane(this);
    JLabel modelLabel;
    JLabel graph_1;
    JLabel graph_2;
    JLabel jlabel;
    JLabel graphLabel;
    ImageIcon imageIcon;
    LayoutManager manager;
    java.util.List<ModelTestResultArray> modelTestResultArrays;

    ModelResultPanel(){
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        modelTestResultArrays = new ArrayList<>();
        modelLabel=new JLabel("Model Name");
        graph_1=new JLabel("Graph 1");
        graph_2=new JLabel("Graph 2");
        setLayout(manager);
        setLayout(new GridLayout(0, 3));

        add(modelLabel);
        add(graph_1);
        add(graph_2);
        for(int i=0; i<getModelList().size(); i++){
            ModelTestResultArray modelTestResultArray=new ModelTestResultArray(getModelList().get(i), getGraph1List().get(i), getGraph2List().get(i));
            modelTestResultArrays.add(modelTestResultArray);
            jlabel=new JLabel(getModelList().get(i));
            jlabel.setSize(new Dimension(100,100));
            add(jlabel);

            String path=getGraph1List().get(i);
            System.out.println("model result path:"+path);
            imageIcon=new ImageIcon(path);
            graphLabel=new JLabel();
            graphLabel.setIcon(imageIcon);
            graphLabel.setSize(new Dimension(100,100));


            add(graphLabel);

            String path2=getGraph2List().get(i);
            imageIcon=new ImageIcon(path2);
            graphLabel=new JLabel(imageIcon);
            graphLabel.setSize(new Dimension(100,100));
            add(graphLabel);
        }
        //scrollPane.add(this);
        scrollPane.setViewportView(this);
        //TODO: 이미지 크기 정하기, 스크롤 달기

        revalidate();
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
                    graph1List.add(folderDir+file.getName()+"/train.png");
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
                    graph2List.add(folderDir+file.getName()+"/validate.png");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return graph2List;
    }

}