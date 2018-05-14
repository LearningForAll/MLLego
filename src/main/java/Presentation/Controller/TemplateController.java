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

    public TemplateController(){

    }

    public TemplateController getInstance(){
        return instance;
    }

    public void setPanel(){
        this.panel = panel;
    }

}
