package Presentation.Controller;

import Models.ModelTestResultArray;
import Presentation.View.ModelResultPanel;

import javax.swing.*;
import java.util.ArrayList;

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
    private ArrayList<ModelTestResultArray> modelTestResultArrayList = new ArrayList<>();

    ModelResultController(){
        dataNum= modelTestResultArrayList.size();
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

 /*   public void updatePanel(){
        modelResultPanel.updatePanel(modelTestResultArrayList);
    }*/
}
