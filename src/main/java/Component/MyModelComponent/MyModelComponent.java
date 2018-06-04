package Component.MyModelComponent;

import Presentation.Controller.BlockPlacementController;
import Presentation.Controller.BlockPlacementDefaultController;
import Presentation.Controller.ModelTestController;
import Util.FileUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by chaebyeonghun on 2018. 5. 29..
 */
public class MyModelComponent extends JPanel {

    JButton jButton;
    JLabel jLabel;
    String modelName;

    // MyModelComponent를 재사용하기 위한 옵션 0 이면 Mymodel, 1이면 Template
    public MyModelComponent(String modelName, String iconName ,int option){
        this.setVisible(true);
        this.setLayout(new GridLayout(2,1));
        this.modelName = modelName;
        ImageIcon imageIcon = new ImageIcon(FileUtil.getResourcePath("icon/"+iconName+".png"));
        setBackground(Color.white);
        jButton = new JButton(imageIcon);
        jButton.setSize(50, 50);
        jButton.setBorder(null);
        add(jButton);

        if (option == 0){
            jButton.addActionListener(new ButtonActionListener(this.modelName));
        }else {
            jButton.addActionListener(new ModelClickActionListener(this.modelName));
        }
        jLabel = new JLabel(modelName);
        jLabel.setSize(50, 10);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        add(jLabel);
        this.setSize(50, 90);

    }
    private class ButtonActionListener implements ActionListener{

        String modelName;
        ButtonActionListener(String modelName){
            this.modelName = modelName;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            ModelTestController.getInstance().addTestModel(modelName);
            BlockPlacementDefaultController.getInstance().changeModelTestTab();
        }
    }
    private class ModelClickActionListener implements ActionListener{

        String templateName;
        ModelClickActionListener(String templateName){this.templateName = templateName;}
        @Override
        public void actionPerformed(ActionEvent e) {
            BlockPlacementController.getInstance().loadBlockBatch("bin/inner/"+templateName+".block");
        }

    }
}
