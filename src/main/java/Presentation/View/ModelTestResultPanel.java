package Presentation.View;


import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-05-24.
 */
public class ModelTestResultPanel extends JScrollPane {
    JPanel workResultPanel=new JPanel(new FlowLayout());

    ModelTestResultPanel(){
        super(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        setPreferredSize(new Dimension(590,630));

        workResultPanel.setPreferredSize(new Dimension(5000,5000));
        workResultPanel.setBackground(Color.LIGHT_GRAY);
        setViewportView(workResultPanel);

        setVisible(true);
    }

}
