import Component.BlockComponent.InputBlock;
import Component.BlockComponent.ModelBlock;
import Component.NumberOnlyTextField;
import Const.Classifier;
import ML.Core.ProcessListener;
import ML.Core.Python.TensorFlow.TFBuilder;
import org.junit.Test;

import javax.swing.*;

public class TestBlock {
    @Test
    public void testCodeGeneration(){
        InputBlock xInputBlock = new InputBlock();
        InputBlock yInputBlock = new InputBlock();
        TFBuilder tfBuilder = new TFBuilder();

    }

    @Test
    public void testTextField(){

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Number Format Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        NumberOnlyTextField numberOnlyTextField = new NumberOnlyTextField(0.0,0,1);
        frame.add(numberOnlyTextField);
        frame.setVisible(true);
        System.out.println(Classifier.LOGISTIC_CLASSIFIER.name());
        TFBuilder tfBuilder = new TFBuilder();
        tfBuilder.setProcessListener(new ProcessListener() {
            @Override
            public void onResponseMessage(String message, Code code) {

            }

            @Override
            public void onFinish(ModelBlock modelBlock) {

            }
        });
    }
}
