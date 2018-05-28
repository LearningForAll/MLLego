package Presentation.View;

import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * Created by LG on 2018-05-24.
 */
public class ModelTestResultPanel extends JPanel {
    JTextPane jTextPane;
    Font font;
    JScrollPane scroll;

    ModelTestResultPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(590,630));
        jTextPane=new JTextPane();
        jTextPane.setBackground(Color.LIGHT_GRAY);
        jTextPane.setContentType("text/html");
        jTextPane.setEnabled(false); //User가 JTextPanel에 입력 못하게 막아둠
        font=new Font("돋움",Font.PLAIN,20);

        scroll=new JScrollPane(jTextPane){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(600,650);
            }
        };
        scroll.setViewportView(jTextPane);
        setPreferredSize(new Dimension(5000,5000));
        setJTextPaneFont(jTextPane, font);
        scroll.setViewportView(jTextPane);

        add(scroll);
        setVisible(true);
    }

    //JTextPane 부분의 font 크기를 지정해주는 함수
    public static void setJTextPaneFont(JTextPane jtp, Font font) {
        MutableAttributeSet attrs = jtp.getInputAttributes();
        StyleConstants.setFontFamily(attrs, font.getFamily());
        StyleConstants.setFontSize(attrs, font.getSize() * 1);
        StyledDocument doc = jtp.getStyledDocument();

        doc.setCharacterAttributes(0, doc.getLength() + 1, attrs, false);
    }

}
