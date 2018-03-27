import Component.*;
import Component.BlockComponent.ModelBlock;
import ML.Core.ProcessListener;
import ML.Core.Python.TensorFlow.TFBuilder;
import org.junit.Test;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class TestBlock {
    @Test
    public void testa(){



    }

    @Test
    public void testTextField(){

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Number Format Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        NumberOnlyTextField numberOnlyTextField = new NumberOnlyTextField(0,10,200);
        frame.add(numberOnlyTextField);
        frame.setVisible(true);
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
