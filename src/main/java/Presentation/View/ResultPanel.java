package Presentation.View;

import Presentation.Controller.ResultController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LG on 2018-03-26.
 */
public class ResultPanel extends JPanel {
    JTextArea txtLog;
    JScrollPane scrollPane;

    public ResultPanel() {
        ResultController.getInstance().setPanel(this);
        setLayout(new BorderLayout());
        txtLog = new JTextArea();
        txtLog.setEditable(false);

        scrollPane = new JScrollPane(txtLog);
        add(scrollPane);
    }

    public void addResultLine(String result) {
        txtLog.append(result + "\n");  // 로그 내용을 JTextArea 위에 붙여주고
        txtLog.setCaretPosition(txtLog.getDocument().getLength());  // 맨아래로 스크롤한다.
    }

    public void resetResult(){
        txtLog.setText("");
    }

}
