package Component.BlockComponent;

import Component.BlockBatchModel.BlockTemplateComponent.TrainingBlockTemplate;
import Component.NumberOnlyTextField;
import Const.Optimizer;

import javax.swing.*;
import java.awt.*;

public class TrainingBlock extends Block {

    // 트레이닝 블록에서 epoch, batch_size, learning_rate 설정
    //private String optimizer;
    //Combobox에 들어갈 optimizer

    // epoch, batch, learning rate를 설정할수 있는 필드
    private NumberOnlyTextField batchSizeTextField;
    // TODO 소수도 지원가능한 텍스트필드 설정
    private NumberOnlyTextField learningRateTextField;
    private NumberOnlyTextField epochTextField;
    private JComboBox<Optimizer> optimizerCombobox;

    //todo valid ratio 넣기
    private NumberOnlyTextField validRatioTextField;

    public TrainingBlock(){
        super();
        blockName="Training Block";
        nameLabel = new JLabel(blockName);
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        validRatioTextField = new NumberOnlyTextField(0.1,0.1,0.5);
        batchSizeTextField = new NumberOnlyTextField(1,1,100000);
        //TODO 러닝레이트 값 조정해야함
        learningRateTextField = new NumberOnlyTextField(0.1, 0.00001, 1);
        epochTextField = new NumberOnlyTextField(1,1,100000);
        optimizerCombobox = new JComboBox<>(Optimizer.values());

        JPanel flowSubPanel=new JPanel(new FlowLayout(FlowLayout.LEADING,7,2));
        JLabel batchSizeLabel=new JLabel("Size");
        JLabel learningRateLabel=new JLabel("Rate");
        JLabel epochLabel=new JLabel("Epoch");
        JLabel ratioLabel=new JLabel("Ratio");
        batchSizeLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        learningRateLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        epochLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        ratioLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        learningRateTextField.setPreferredSize(new Dimension(20,20));
        batchSizeTextField.setPreferredSize(new Dimension(20,20));
        epochTextField.setPreferredSize(new Dimension(20,20));
        validRatioTextField.setPreferredSize(new Dimension(20,20));
        flowSubPanel.add(batchSizeLabel);
        flowSubPanel.add(batchSizeTextField);
        flowSubPanel.add(learningRateLabel);
        flowSubPanel.add(learningRateTextField);
        flowSubPanel.add(epochLabel);
        flowSubPanel.add(epochTextField);
        flowSubPanel.add(ratioLabel);
        flowSubPanel.add(validRatioTextField);

        GridLayout layout=new GridLayout(3,1);
        setLayout(layout);
        setSize(200,75);
        add(flowPanel);
        add(flowSubPanel);
        add(optimizerCombobox);
        flowPanel.add(nameLabel);
        flowPanel.setBackground(new Color(0,0,180));
        setVisible(true);
    }
    public TrainingBlock(TrainingBlockTemplate template){
        this();
        optimizerCombobox.setSelectedItem(template.getOptimizer());
        learningRateTextField.setText(String.valueOf(template.getLearningRate()));
        batchSizeTextField.setText(String.valueOf(template.getBatchSize()));
        epochTextField.setText(String.valueOf(template.getEpochSize()));
        //TODO 주석제거
        //validRatioTextField.setText(String.valueOf(template.getValidRatio()));
        setLocation(template.getPositionX(), template.getPositionY());
    }

    @Override
    String getBlockAttrStr() {
        return null;
    }


    @Override
    public boolean isNextBlockConnectable(Block block) {
        return (block instanceof ModelBlock);
    }

    @Override
    public boolean isPreviousBlockConnectable(Block block) {

        return (block instanceof ClassifierBlock);
    }

    @Override
    public boolean isNextBlockConnected() {
        return (nextBlocks.size() != 0);
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return (previousBlocks.size() != 0);
    }


    public ClassifierBlock getClassifierBlock(){
        if (previousBlocks == null || previousBlocks.size()<1){
            return null;
        }
        return (ClassifierBlock)previousBlocks.get(0);
    }

    public int getBatchSize(){
        return (int)batchSizeTextField.getValue();
    }

    public int getEpoch(){
        return (int)epochTextField.getValue();
    }

    public Optimizer getOptimizer(){ return (Optimizer) optimizerCombobox.getSelectedItem(); }

    public float getLearningRate() {
        return Float.valueOf(learningRateTextField.getText());
    }

    public float getValidRatio() {
        return Float.valueOf(validRatioTextField.getText());
    }
}
