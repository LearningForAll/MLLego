package Presentation.Controller;

import Presentation.View.TemplatePanel;

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
}
