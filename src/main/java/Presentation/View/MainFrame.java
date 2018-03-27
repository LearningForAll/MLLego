package Presentation.View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    MainFrame(){

        super("ML Lego");
        setLayout(new BorderLayout());

        TopMenuPanel topMenuPanel=new TopMenuPanel();
        BlockBatchPanel blockBatchPanel=new BlockBatchPanel();
        ModelTestPanel modelTestPanel=new ModelTestPanel();
        BlockListPanel blockListPanel=new BlockListPanel();
        MyModelPanel myModelPanel=new MyModelPanel();
        TemplatePanel templatePanel=new TemplatePanel();
        ResultPanel resultPanel=new ResultPanel();
        ProceedPanel proceedPanel=new ProceedPanel();


        setSize(1700,1000);
        add(topMenuPanel, BorderLayout.NORTH);
        add(blockBatchPanel, BorderLayout.CENTER);
        add(blockListPanel, BorderLayout.EAST);
        add(myModelPanel, BorderLayout.SOUTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args){
        new MainFrame();
    }
}
