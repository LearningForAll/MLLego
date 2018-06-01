package Models;

import Util.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by LG on 2018-05-29.
 */
public class ModelTestResultArray extends JPanel {
    private String modelName;
    private String graph1;
    private String graph2;
    JLabel name;
    JLabel graph1Label;
    JLabel graph2Label;
    ImageIcon imageIcon;
    ImageIcon imageIcon2;

    ModelTestResultArray() {
    }

    public ModelTestResultArray(String modelName, String graph1, String graph2) {
        this.modelName = modelName;
        this.graph1 = graph1;
        this.graph2 = graph2;

        name=new JLabel(modelName);
        String currentDir = System.getProperty("user.dir");
        String folderDir = currentDir+"/bin/";
        String path = folderDir+modelName;
        imageIcon = new ImageIcon(path+"/train.png");
        graph1Label=new JLabel(imageIcon);
        graph1Label.setSize(100,100);
        imageIcon2 = new ImageIcon(path+"/validate.png");
        graph2Label=new JLabel(imageIcon2);
        graph2Label.setSize(100,100);

        add(name);
        add(graph1Label);
        add(graph2Label);

        setVisible(true);
    }

    public String getModelName() {
        return modelName;
    }

    public String getGraph1() {
        return graph1;
    }

    public String getGraph2() {
        return graph2;
    }

    //해성이가 건드려야 하는 부분
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setGraph1(String graph1) {
        this.graph1 = graph1;
    }

    public void setGraph2(String graph2) {
        this.graph2 = graph2;
    }//String graph는 그래프의 경로를 넘겨줌
}