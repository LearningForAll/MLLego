package Presentation.Controller;

import App.MyApp;
import Presentation.View.TemplatePanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;


/**
 * Created by chaebyeonghun on 2018. 4. 8..
 */
public class TemplateController {

    private static TemplateController instance = new TemplateController();
    TemplatePanel panel;

    private TemplateController(){

    }

    public TemplateController getInstance(){
        return instance;
    }

    public void setPanel(){
        this.panel = panel;
    }

    //템플릿 창에 있는 MNist CNN, Xor-DNN 버튼을 누르면 버튼에 알맞는 파일을 바로 넘겨줌
    public void LoadTemplateFile(File file) {

    }

}
