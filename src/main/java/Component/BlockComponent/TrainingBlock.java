package Component.BlockComponent;

import Models.Coords;
import javax.swing.*;
public class TrainingBlock extends Block {

    // 트레이닝 블록에서 epoch, batch_size, learning_rate 설정
    private int epoch;
    private int batchSize;
    private float learningRate;
    private String optimizer;

    // epoch, batch, learning rate를 설정할수 있는 필드
    private JTextField batchSizeTextField;
    private JTextField learningRateTextField;
    private JTextField epochTextField;
    private JComboBox optimizerCombobox;
    @Override
    String getBlockAttrStr() {
        return null;
    }


}
