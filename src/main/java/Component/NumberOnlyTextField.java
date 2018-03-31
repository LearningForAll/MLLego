package Component;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.event.FocusEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class NumberOnlyTextField extends JFormattedTextField {

    public NumberOnlyTextField(double defaultValue, double min, double max) {
        if (defaultValue < min || defaultValue > max) defaultValue = min;
        NumberFormat format = DecimalFormat.getInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.HALF_UP);
        InternationalFormatter formatter = new InternationalFormatter(format);
        formatter.setMinimum(min);
        formatter.setMaximum(max);
        formatter.setAllowsInvalid(false);
        setFormatterFactory(new DefaultFormatterFactory(formatter));
        setValue(defaultValue);
    }

    public NumberOnlyTextField(int defaultValue, int min, int max) {
        if (defaultValue < min || defaultValue > max) defaultValue = min;
        InternationalFormatter formatter = new InternationalFormatter(DecimalFormat.getInstance()) {
            // 숫자 지울때 최솟값 자동입력, 최대 수보다 크게입력했을때 자동보정
            // ex) min = 1, max = 5 일때
            // 4가 입력되있는상황에서 3을입력하면 => 43인데 3으로 자동보정
            // TODO :: 이 방법은 앞에 입력하면 해석불가능한 단점이있지만 그거까지 커버하기엔 오래걸릴듯해 중요하지않아 일단패스
            @Override
            public Object stringToValue(String string) throws ParseException {
                try {
                    int value = Integer.valueOf(string);
                    if (value <= min) {
                        string = "" + min;
                    } else if (value >= max) {
                        if (value % (Math.pow(10, string.length() - 1)) <= max &&
                                value % (Math.pow(10, string.length() - 1)) >= min) {
                            string = "" + (value % (Math.pow(10, string.length() - 1)));
                        } else {
                            string = "" + max;
                        }
                    }
                } catch (NullPointerException | NumberFormatException e) {
                    string = "" + min;
                }
                return super.stringToValue(string);
            }
        };
        formatter.setMinimum(min);
        formatter.setMaximum(max);
        formatter.setAllowsInvalid(false);
        setFormatterFactory(new DefaultFormatterFactory(formatter));
        setValue(defaultValue);
        setFocusLostBehavior(JFormattedTextField.PERSIST);
    }

}
