package Component.TestBlockComponent;

import Component.BlockComponent.Block;
import Const.FileType;
import Const.PreprocessorType;

import javax.swing.*;
import java.awt.*;

public class TestPreProcessorBlock extends TestBlock  {

    private JComboBox<PreprocessorType> preprocessorTypeCombobox;
    FileType fileType;
    boolean IsXData;

    public TestPreProcessorBlock(){
        super();
        nameLabel = new JLabel(getClass().getSimpleName());
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        preprocessorTypeCombobox = new JComboBox<>(PreprocessorType.values());
        GridLayout layout=new GridLayout(2,1);
        setLayout(layout);
        setSize(200,50);
        width=getWidth();
        add(flowPanel);
        add(preprocessorTypeCombobox);
        flowPanel.add(nameLabel);
        preprocessorTypeCombobox.setEnabled(false);

        flowPanel.setBackground(new Color(243,115,50));
        setVisible(true);
    }
}
