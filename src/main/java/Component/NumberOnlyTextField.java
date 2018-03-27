package Component;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class NumberOnlyTextField extends JFormattedTextField {
    public NumberOnlyTextField(int defaultValue, int min, int max){
        if (defaultValue<min || defaultValue>max) defaultValue = min;
        NumberFormatter formatter = new NumberFormatter();
        formatter.setMinimum(min);
        formatter.setMaximum(max);
        formatter.setAllowsInvalid(false);
        setFormatterFactory( new DefaultFormatterFactory(formatter));

        setValue(defaultValue);
    }
}
