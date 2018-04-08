package Presentation.View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    MainFrame(){

        super("ML Lego");
        setLayout(new BorderLayout());

        TopMenuPanel topMenuPanel=new TopMenuPanel();
        BlockPlacementDefault blockPlacementDefault =new BlockPlacementDefault();
        BlockListPanel blockListPanel=new BlockListPanel();
        MyModelDefault myModelDefault=new MyModelDefault();

        setSize(1700,1000);
        add(topMenuPanel, BorderLayout.NORTH);
        add(blockPlacementDefault, BorderLayout.CENTER);
        add(blockListPanel, BorderLayout.EAST);
        add(myModelDefault, BorderLayout.SOUTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args){
        new MainFrame();
    }
}
