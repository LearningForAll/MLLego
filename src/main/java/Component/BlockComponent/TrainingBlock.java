package Component.BlockComponent;

import Component.NumberOnlyTextField;
import Const.Optimizer;
import Models.Coords;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class TrainingBlock extends Block {

    // 트레이닝 블록에서 epoch, batch_size, learning_rate 설정
    private float learningRate;
    //private String optimizer;
    //Combobox에 들어갈 optimizer
    private Optimizer optimizers;

    // epoch, batch, learning rate를 설정할수 있는 필드
    private NumberOnlyTextField batchSizeTextField;
    // TODO 소수도 지원가능한 텍스트필드 설정
    private JTextField learningRateTextField;
    private NumberOnlyTextField epochTextField;
    private JComboBox<Optimizer> optimizerCombobox;


    public TrainingBlock(){

        batchSizeTextField = new NumberOnlyTextField(1,1,100000);
        //TODO 소수도 지원 가능한걸로 교체
        learningRateTextField = new JTextField();
        epochTextField = new NumberOnlyTextField(1,1,100000);
        optimizerCombobox = new JComboBox<>(Optimizer.values());

        JPanel flowPanel=new JPanel(new FlowLayout(FlowLayout.LEADING,7,2));
        JLabel batchSizeLabel=new JLabel("Size");
        JLabel learningRateLabel=new JLabel("Rate");
        JLabel epochLabel=new JLabel("Epoch");
        batchSizeLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        learningRateLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        epochLabel.setFont(new Font("BOLD", Font.BOLD, 11));
        learningRateTextField.setPreferredSize(new Dimension(20,20));
        batchSizeTextField.setPreferredSize(new Dimension(20,20));
        epochTextField.setPreferredSize(new Dimension(20,20));
        flowPanel.add(batchSizeLabel);
        flowPanel.add(batchSizeTextField);
        flowPanel.add(learningRateLabel);
        flowPanel.add(learningRateTextField);
        flowPanel.add(epochLabel);
        flowPanel.add(epochTextField);

        JLabel nameLabel=new JLabel("Training Block");
        TitledBorder line=new TitledBorder(new LineBorder(Color.black));
        GridLayout layout=new GridLayout(3,1);
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        setLayout(layout);
        setBorder(line);
        add(nameLabel);
        add(flowPanel);
        add(optimizerCombobox);
        setBackground(new Color(0,0,180));
        setVisible(true);
    }

    @Override
    String getBlockAttrStr() {
        return null;
    }


    @Override
    boolean isNextBlockConnectable(Block block) {
        if(nextBlocks.size() > 0){
            return false;
        }else{
            return (block instanceof ClassifierBlock);
        }
    }

    @Override
    boolean isPreviousBlockConnectable(Block block) {
        // 이미 연결되어있을 경우
        if(previousBlocks.size() > 0){
            return false;
        }
        return (block instanceof ModelBlock);
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

}
