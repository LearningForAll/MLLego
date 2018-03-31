package Component.BlockComponent;

import Component.NumberOnlyTextField;
import Const.Optimizer;
import Models.Coords;
import javax.swing.*;
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
    private JComboBox optimizerCombobox;
    @Override
    String getBlockAttrStr() {
        return null;
    }

    public ClassifierBlock getClassifierBlock(){
        if (beforeBlocks == null || beforeBlocks.length<1){
            return null;
        }
        return (ClassifierBlock)beforeBlocks[0];
    }
}
