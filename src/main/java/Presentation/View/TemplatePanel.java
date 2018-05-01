package Presentation.View;

import App.MyApp;

import javax.swing.*;
import java.io.File;

/**
 * Created by LG on 2018-03-26.
 */
public class TemplatePanel extends JPanel {

    public TemplatePanel(){
    }

    public String getTemplateDirectory(){
        File file = new File("");
        return (file.getAbsolutePath() + "/bin/" + MyApp.templateFolder);
    }
}
