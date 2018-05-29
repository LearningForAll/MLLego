package Presentation.View;

import Models.ModelTestResultArray;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-05-26.
 */

//모델 테스트에 대한 모델 이름, 그래프, 정확도, cost가 나오는 패널
public class ModelResultPanel extends JPanel {
    ModelTestResultArray[] modelTestResultArray;
    //JLabel header[]=new JLabel[]{"Model Name", "Graph1", "Graph2", "Accuracy", "Cost"};

    JTable jTable;
    int dataNum=3;//데이터 갯수를 받아옴

    ModelResultPanel(){
        setLayout(new GridLayout(dataNum+1, 5));
        //jTable=new JTable();
        //data값이 들어감
        /*
        for(int i=0; i<header.length; i++){
            this.add();
        }
        */
        modelTestResultArray=new ModelTestResultArray[dataNum];

        setBackground(Color.WHITE);
        setVisible(true);
    }

}
