import Component.BlockComponent.ConvolutionLayerBlock;
import Component.NumberOnlyTextField;
import Const.Classifier;
import org.junit.Test;

import javax.swing.*;

/**
 * Created by LG on 2018-04-10.
 */
public class sbtest {

    @Test
    public void testComboBox(){
        JComboBox optimizerComboBox = new JComboBox<>(Classifier.values());
        optimizerComboBox.setSelectedIndex(0);
        Object obj = optimizerComboBox.getSelectedItem();
        Classifier classifier = (Classifier)obj;
        System.out.println(classifier);
    }

    @Test
    public void testJSlider(){
    }

    @Test
    public void testNumField(){
        NumberOnlyTextField numTest=new NumberOnlyTextField(1, 1, 1000);
        Object obj=numTest.getValue();
        int i=(int ) obj;
        System.out.println(i);
    }
}
