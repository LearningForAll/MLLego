package Presentation.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-05-24.
 */
public class ModelTestDefault extends JPanel {
    ModelTestPanel modelTestPanel;
    ModelTestResultPanel modelTestResultPanel;
    ModelTestDefault(){

        setLayout(new GridLayout(1,2));
        modelTestPanel = new ModelTestPanel();
        modelTestResultPanel = new ModelTestResultPanel();
        add(modelTestPanel);
        add(modelTestResultPanel);
        setBackground(Color.white);
        setVisible(true);

    }

}
