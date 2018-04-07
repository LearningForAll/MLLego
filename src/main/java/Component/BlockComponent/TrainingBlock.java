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
    private JComboBox<Optimizer> optimizerCombobox;


    public TrainingBlock(){

        batchSizeTextField = new NumberOnlyTextField(1,1,100000);
        //TODO 소수도 지원 가능한걸로 교체
        learningRateTextField = new JTextField();
        epochTextField = new NumberOnlyTextField(1,1,100000);
        optimizerCombobox = new JComboBox<>(Optimizer.values());
        
    }




    @Override
    String getBlockAttrStr() {
        return null;
    }


    @Override
    public boolean isNextBlockConnectable(Block block) {
        return (block instanceof ClassifierBlock);
    }

    @Override
    public boolean isPreviousBlockConnectable(Block block) {

        return (block instanceof ModelBlock);
    }

    @Override
    public boolean isNextBlockConnected() {
        return false;
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return false;
    }


    public ClassifierBlock getClassifierBlock(){
        if (previousBlocks == null || previousBlocks.size()<1){
            return null;
        }
        return (ClassifierBlock)previousBlocks.get(0);
    }
}
