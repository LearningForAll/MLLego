package Presentation.View;

import Component.BlockComponent.ConvolutionLayerBlock;
import Presentation.Controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    TopMenuPanel topMenuPanel;
    BlockPlacementDefault blockPlacementDefault;
    BlockListPanel blockListPanel;
    MyModelDefault myModelDefault;
    ModelTestDefault modelTestDefault;
    ModelResultPanel modelResultPanel;
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
        modelTestDefault=new ModelTestDefault();
        BlockPlacementController.getInstance().setPanel(blockPlacementDefault.getBlockPlacementPanel());
        ModelTestController.getInstance().setPanel(blockPlacementDefault.getModelTestPanel());
        BlockListController.getInstance().setPanel(blockListPanel);
        BlockPlacementDefaultController.getInstance().setDefaultPanel(blockPlacementDefault);
        ModelResultController.getInstance().setPanel(modelResultPanel);
        
        setSize(1800,1000);
        add(topMenuPanel, BorderLayout.NORTH);
        add(blockPlacementDefault, BorderLayout.CENTER);
        add(blockListPanel, BorderLayout.EAST);
        add(myModelDefault, BorderLayout.SOUTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

}
