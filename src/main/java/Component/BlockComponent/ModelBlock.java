package Component.BlockComponent;

import Component.BlockBatchModel.BlockTemplateComponent.ModelBlockTemplate;
import Models.Coords;

import javax.swing.*;
import java.awt.*;

public class ModelBlock extends Block {

    public ModelBlock(){
        super();
        blockName="Model Block";
        nameLabel = new JLabel(blockName);
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        GridLayout layout=new GridLayout(1, 1);
        setLayout(layout);
        setSize(200,40);
        add(flowPanel);
        flowPanel.add(nameLabel);
        flowPanel.setBackground(new Color(0, 0, 180));
        setVisible(true);
    }
    public ModelBlock(ModelBlockTemplate template){
        this();
        setLocation(template.getPositionX(), template.getPositionY());
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
        // 모델블록은 항상 마지막이므로 다음 블록이 올수 없다.
        return false;
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return (previousBlocks.size() != 0);
    }
}
