package Presentation.Controller;

import Presentation.View.ResultPanel;

public class ResultController {
    private static ResultController instance = new ResultController();
    private ResultPanel resultPanel;

    public void setPanel(ResultPanel panel){
        this.resultPanel = panel;
    }

    private ResultController(){

    }

    public static ResultController getInstance() {
        return instance;
    }

    public void addResultLine(String string){
        resultPanel.addResultLine(string);
    }
}
