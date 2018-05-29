package Presentation.View;

import Models.ModelTestResultArray;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by LG on 2018-05-26.
 */

//모델 테스트에 대한 모델 이름, 그래프, 정확도, cost가 나오는 패널
public class ModelResultPanel extends JPanel {
    ModelTestResultArray[] modelTestResultArray;
    //TODO:: dataNum 받아오는 것으로 수정 필요
    int dataNum=10;//데이터 갯수를 받아와야함
    JLabel modelLabel;
    JLabel graph_1;
    JLabel graph_2;
    JLabel accuLabel;
    JLabel costLabel;

    ModelResultPanel(){
        setLayout(new GridLayout(dataNum+1, 5));
        modelTestResultArray=new ModelTestResultArray[dataNum];

        modelLabel=new JLabel("Model Name");
        graph_1=new JLabel("Graph 1");
        graph_2=new JLabel("Graph 2");
        accuLabel=new JLabel("Accuracy");
        costLabel=new JLabel("Cost");
        add(modelLabel);
        add(graph_1);
        add(graph_2);
        add(accuLabel);
        add(costLabel);

        setBackground(Color.WHITE);
        setVisible(true);
    }

    //결과값을 패널에 나타내줌
    public void updatePanel(ArrayList<ModelTestResultArray> modelTestResultArray){
        for(int i=0; i<dataNum; i++){
            JLabel name=new JLabel(modelTestResultArray.get(i).getModelName());
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(modelTestResultArray.get(i).getGraph1()));
            JLabel graph1=new JLabel(imageIcon);
            ImageIcon imageIcon2 = new ImageIcon(this.getClass().getResource(modelTestResultArray.get(i).getGraph2()));
            JLabel graph2=new JLabel(imageIcon2);
            JLabel accuracy=new JLabel(String.format("%f",modelTestResultArray.get(i).getAccuracy()));
            JLabel cost=new JLabel(String.format("%f",modelTestResultArray.get(i).getCost()));

            add(name);
            add(graph1);
            add(graph2);
            add(accuracy);
            add(cost);

            revalidate();
            repaint();
        }
    }
}
