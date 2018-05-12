package Presentation.View;

import Component.BlockComponent.ConvolutionLayerBlock;
import Presentation.Controller.BlockListController;
import Presentation.Controller.BlockPlacementController;
import Presentation.Controller.TopmenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

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
        BlockPlacementController.getInstance().setPanel(blockPlacementDefault.blockPlacementPanel);
        BlockListController.getInstance().setPanel(blockListPanel);
        setSize(1700,1000);
        add(topMenuPanel, BorderLayout.NORTH);
        add(blockPlacementDefault, BorderLayout.CENTER);
        add(blockListPanel, BorderLayout.EAST);
        add(myModelDefault, BorderLayout.SOUTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

}
