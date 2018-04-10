package Component.BlockComponent;

import Models.Coords;

import javax.swing.*;
import java.awt.*;

public class ModelBlock extends Block {

    public ModelBlock(){
        JLabel nameLabel=new JLabel("Model Block");
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        //setLayout(layout);
        //setBorder(line);
        add(nameLabel);
        setBackground(new Color(0, 0, 180));
        setVisible(true);
    }

    @Override
    String getBlockAttrStr() {
        return null;
    }


    @Override
    public boolean isNextBlockConnectable(Block block) {
        // 최종 Output 블록
        return false;
    }

    @Override
    public boolean isPreviousBlockConnectable(Block block) {
        return (block instanceof TrainingBlock);
    }

    @Override
    public boolean isNextBlockConnected() {
        return false;
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return false;
    }
}
