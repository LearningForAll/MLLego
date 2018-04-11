package Presentation.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-03-31.
 */
public class MyModelDefault extends JTabbedPane {
    MyModelPanel myModelPanel=new MyModelPanel();
    TemplatePanel templatePanel=new TemplatePanel();
    ResultPanel resultPanel=new ResultPanel();


    public MyModelDefault(){
        add("My Model", myModelPanel);
        add("Template", templatePanel);
        add("Result", resultPanel);

        setTabPlacement(JTabbedPane.BOTTOM);
        setVisible(true);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1700,200);
    }
}
