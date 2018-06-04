package Presentation.Controller;

import Presentation.View.MyModelDefault;

public class MyModelDefaultController {

    private static MyModelDefaultController instance = new MyModelDefaultController();

    MyModelDefault defaultPanel;

    MyModelDefaultController(){

    }
    public static MyModelDefaultController getInstance(){
        return instance;
    }
    public void setDefaultPanel(MyModelDefault defaultPanel){
        this.defaultPanel = defaultPanel;
    }
    public void restartMyModelList(){
        defaultPanel.restartMyModelList();
    }
    public void setResultTab(){
        if (defaultPanel.getSelectedIndex()!=2){
            defaultPanel.setSelectedIndex(2);
        }
    }
}
