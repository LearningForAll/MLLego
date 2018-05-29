package Models;

import java.awt.*;

/**
 * Created by LG on 2018-05-29.
 */
public class ModelTestResultArray {
    private String modelName;
    private Image graph1;
    private Image graph2;
    private float accuracy;
    private float cost;

    ModelTestResultArray(){}

    ModelTestResultArray(String modelName, Image graph1, Image graph2, float accuracy, float cost){
        this.modelName=modelName;
        this.graph1=graph1;
        this.graph2=graph2;
        this.accuracy=accuracy;
        this.cost=cost;
    }

    public String getModelName(){return modelName;}
    public float getAccuracy(){return accuracy;}
    public float getCost(){return cost;}
    public Image getGraph1(){return graph1;}
    public Image getGraph2(){return graph2;}

    public void setModelName(String modelName){this.modelName=modelName;}
    public void setGraph1(Image graph1){this.graph1=graph1;}
    public void setGraph2(Image graph2){this.graph2=graph2;}
    public void setAccuracy(float accuracy){this.accuracy=accuracy;}
    public void setCost(float cost){this.cost=cost;}
}
