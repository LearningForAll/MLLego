package Component.TestBlockComponent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by chaebyeonghun on 2018. 5. 29..
 */
public class TestModelBlock extends TestBlock {

    JPanel flowSubPanel;
    JTextField modelTextField;

    public TestModelBlock(String modelName){
        super();
        flowSubPanel=new JPanel();
        modelTextField=new JTextField(18);
        nameLabel = new JLabel(getClass().getSimpleName());
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        GridLayout layout=new GridLayout(2, 1);
        setLayout(layout);
        setSize(200,50);
        width = getWidth();
        flowSubPanel.add(modelTextField);
        add(flowPanel);
        add(flowSubPanel);
        flowPanel.add(nameLabel);
        flowPanel.setBackground(new Color(0, 0, 180));
        setVisible(true);

        // 생성자에 모델네임 넘기고 ...
        modelTextField.setText(modelName);

    }
}
