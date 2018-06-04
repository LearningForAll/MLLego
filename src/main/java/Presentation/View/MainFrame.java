package Presentation.View;

import Presentation.Controller.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    TopMenuPanel topMenuPanel;
    BlockPlacementDefault blockPlacementDefault;
    BlockListPanel blockListPanel;
    MyModelDefault myModelDefault;
    public MainFrame(){

        super("ML Lego");
        setLayout(new BorderLayout());
        setDefaultLookAndFeelDecorated(true);
        setBackground(new Color(0, 71, 113));

        topMenuPanel=new TopMenuPanel();
        TopmenuController.getInstance().setPanel(topMenuPanel);

        blockPlacementDefault =new BlockPlacementDefault();
        blockListPanel=new BlockListPanel();
        myModelDefault=new MyModelDefault();
        MyModelDefaultController.getInstance().setDefaultPanel(myModelDefault);
        BlockPlacementController.getInstance().setPanel(blockPlacementDefault.getBlockPlacementPanel());
        ModelTestController.getInstance().setPanel(blockPlacementDefault.getModelTestPanel());
        BlockListController.getInstance().setPanel(blockListPanel);
        BlockPlacementDefaultController.getInstance().setDefaultPanel(blockPlacementDefault);
        //ModelResultController.getInstance().setPanel(modelResultPanel);
        setSize(1800,1000);
        add(topMenuPanel, BorderLayout.NORTH);
        add(blockPlacementDefault, BorderLayout.CENTER);
        add(blockListPanel, BorderLayout.EAST);
        add(myModelDefault, BorderLayout.SOUTH);
        //add(blockPlacementDefault.getModelResultPanel(), blockPlacementDefault.getModelResultPanel().scrollPane);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

}
