package Presentation.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-05-26.
 */

//모델 테스트에 대한 모델 이름, 그래프, 정확도, cost가 나오는 패널
public class ModelResultPanel extends JPanel {
    private String modelName;
    private float accuracy;
    private float cost;
    private Image graph;

    ModelResultPanel(){
        setBackground(Color.WHITE);
        setVisible(true);
    }

    public String getModelName(){return modelName;}
    public float getAccuracy(){return accuracy;}
    public float getCost(){return cost;}
    public Image getGraph(){return graph;}
}
