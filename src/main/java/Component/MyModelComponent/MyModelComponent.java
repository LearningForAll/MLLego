package Component.MyModelComponent;

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

    public MyModelComponent(String modelName){
        this.setVisible(true);
        this.setLayout(new GridLayout(2,1));

        this.modelName = modelName;
        ImageIcon imageIcon = new ImageIcon(FileUtil.getResourcePath("icon/model_block_icon.png"));
        setBackground(Color.white);
        jButton = new JButton(imageIcon);
        jButton.setSize(50, 50);
        jButton.setBorder(null);
        add(jButton);
        jButton.addActionListener(new ButtonActionListener(this.modelName));

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
}
