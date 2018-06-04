package Presentation.View;

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
public class ModelResultPanel extends JScrollPane {
    JLabel jlabel;
    JLabel graphLabel;
    ImageIcon imageIcon;
    ImageIcon imageIcon2;
    Image image;
    Image image2;
    java.util.List<ModelTestResultArray> modelTestResultArrays;
    JPanel jpanel;
    GridBagLayout gridBagLayout;

    ModelResultPanel(){
        super(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        modelTestResultArrays = new ArrayList<>();
        gridBagLayout=new GridBagLayout();
        jpanel=new JPanel(gridBagLayout);
        GridBagConstraints constraints=new GridBagConstraints();
        constraints.fill=GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;


        for(int i=0; i<getModelList().size(); i++){
            constraints.gridwidth=2;
            constraints.gridheight=2;

            ModelTestResultArray modelTestResultArray=new ModelTestResultArray(getModelList().get(i), getGraph1List().get(i), getGraph2List().get(i));
            modelTestResultArrays.add(modelTestResultArray);
            jlabel=new JLabel(getModelList().get(i));
            gridBagLayout.setConstraints(jlabel,constraints);
            jpanel.add(jlabel);

            String path=getGraph1List().get(i);
            imageIcon=new ImageIcon(path);
            image=imageIcon.getImage();
            image2=image.getScaledInstance(400,300,java.awt.Image.SCALE_SMOOTH);
            imageIcon2=new ImageIcon(image2);
            graphLabel=new JLabel();
            graphLabel.setIcon(imageIcon2);
            gridBagLayout.setConstraints(graphLabel,constraints);
            jpanel.add(graphLabel);

            constraints.gridwidth = GridBagConstraints.REMAINDER;
            String path2=getGraph2List().get(i);
            imageIcon=new ImageIcon(path2);
            image=imageIcon.getImage();
            image2=image.getScaledInstance(400,300,java.awt.Image.SCALE_SMOOTH);
            imageIcon2=new ImageIcon(image2);
            graphLabel=new JLabel(imageIcon);
            gridBagLayout.setConstraints(graphLabel,constraints);
            jpanel.add(graphLabel);
        }

        setViewportView(jpanel);
        revalidate();
        jpanel.setBackground(Color.WHITE);
        setBackground(Color.WHITE);
        setVisible(true);
    }


    private List<String> getModelList(){

        List<String> modelList = new ArrayList<>();
        String currentDir = System.getProperty("user.dir");
        String folderDir = currentDir+"/bin/";

        File dir = new File(folderDir);
        File[] fileList = dir.listFiles();
        try{
            for(int i = 0 ; i < fileList.length ; i++){
                File file = fileList[i];
                if(file.isDirectory()){
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