package Presentation.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-05-24.
 */
public class ModelTestResultPanel extends JPanel {
    JTextArea jTextPane;
    JScrollPane scroll;

    ModelTestResultPanel(){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(590,630));
        jTextPane=new JTextArea();
        jTextPane.setEditable(false);
        scroll=new JScrollPane(jTextPane);
        add(scroll);
        setVisible(true);
    }

    public void addLine(String data){
        jTextPane.append(data.trim()+ "\n");  // 로그 내용을 JTextArea 위에 붙여주고
        jTextPane.setCaretPosition(jTextPane.getDocument().getLength());  // 맨아래로 스크롤한다.jTextPane.append();
    }

    public void reset() {
        jTextPane.setText("");
    }
}
