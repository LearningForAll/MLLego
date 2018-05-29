package Models;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-05-29.
 */
public class ModelTestResultArray {
    private String modelName;
    private String graph1;
    private String graph2;
    private float accuracy;
    private float cost;

    ModelTestResultArray(){}

    ModelTestResultArray(String modelName, String graph1, String graph2, float accuracy, float cost){
        this.modelName=modelName;
        this.graph1=graph1;
        this.graph2=graph2;
        this.accuracy=accuracy;
        this.cost=cost;
    }

    public String getModelName(){return modelName;}
    public float getAccuracy(){return accuracy;}
    public float getCost(){return cost;}
    public String getGraph1(){return graph1;}
    public String getGraph2(){return graph2;}

    //모델 부분과 소통하는 부분
    public void setModelName(String modelName){this.modelName=modelName;}
    public void setGraph1(String graph1){this.graph1=graph1;}
    public void setGraph2(String graph2){this.graph2=graph2;}//String graph는 그래프의 경로를 넘겨줌
    public void setAccuracy(float accuracy){this.accuracy=accuracy;}
    public void setCostPanel(float cost){this.cost=cost;}


}
