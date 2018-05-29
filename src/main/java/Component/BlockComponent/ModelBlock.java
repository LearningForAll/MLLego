package Component.BlockComponent;

import Component.BlockBatchModel.BlockTemplateComponent.ModelBlockTemplate;

import javax.swing.*;
import java.awt.*;

public class ModelBlock extends Block {
    JPanel flowSubPanel;
    JTextField modelTextField;

    public ModelBlock(){
        super();
        flowSubPanel=new JPanel();
        modelTextField=new JTextField(18);
        nameLabel = new JLabel(getClass().getSimpleName());
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        GridLayout layout=new GridLayout(2, 1);
        setLayout(layout);
        setSize(200,50);
        width=getWidth();
        flowSubPanel.add(modelTextField);
        add(flowPanel);
        add(flowSubPanel);
        flowPanel.add(nameLabel);
        flowPanel.setBackground(new Color(0, 0, 180));
        setVisible(true);
    }
    public ModelBlock(ModelBlockTemplate template){
        this();
        // 크기만 늘어났는지 체크
        this.setExtended(template.isExtended());
        setLocation(template.getPositionX(), template.getPositionY());
    }

    @Override
    protected String getBlockAttrStr() {
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

    public String getModelTextField(){
        return modelTextField.getText();
    }

    public String getName(){
        return modelTextField.getText();
    }
}
