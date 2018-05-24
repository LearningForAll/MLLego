package Component.TestBlockComponent;

import Component.BlockComponent.Block;
import Component.BlockComponent.XInputBlock;
import Const.FileType;
import Const.InputOption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TestInputBlock extends TestBlock {
    private static String filePath = "empty";
    private JPanel flowSubPanel;
    // ... 이런식으로 생겨서 누르면 파일 익스플로어를 연다.
    private JButton openFileExploreButton;
    // 불러온 파일패스를 보여주는 텍스트필드 유저가 직접 편집할수 없게 설정
    private JTextField filePathTextField;
    // csv파일이나 텍스트 파일의 경우 맨 끝의 데이터를 제거할 것인지 맨 처음의 데이터를 제거할 것인지의 옵션.
    private JRadioButton endEliminationRadioButtion;
    private JRadioButton startEliminationRadioButton;
    private JComboBox<InputOption> inputOptionCombobox;
    private int inputFileDim = 0;

    public TestInputBlock(){
        super();
        nameLabel = new JLabel("X Input");
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        openFileExploreButton = new JButton("File");
        openFileExploreButton.addActionListener(new FileOpenListener());
        filePathTextField = new JTextField();
        // 파일패스를 직접 설정하지 못하게..
        filePathTextField.setEditable(false);
        endEliminationRadioButtion = new JRadioButton("End");
        startEliminationRadioButton = new JRadioButton("Start");
        inputOptionCombobox = new JComboBox<>(InputOption.values());

        flowSubPanel=new JPanel(new FlowLayout(FlowLayout.LEADING,2,2));
        openFileExploreButton.setPreferredSize(new Dimension(60,20));
        filePathTextField.setPreferredSize(new Dimension(134,20));

        flowSubPanel.add(openFileExploreButton);
        flowSubPanel.add(filePathTextField);

        GridLayout layout=new GridLayout(3,1);
        setLayout(layout);
        setSize(200,75);
        width = getWidth();

        add(flowPanel);
        add(flowSubPanel);
        add(inputOptionCombobox);
        flowPanel.add(nameLabel);
        inputOptionCombobox.setEnabled(false);
        flowPanel.setBackground(new Color(243, 0, 50));

        setVisible(true);
    }

    public FileType getFileType(){
        //파일 타입을 판단하는 알고리즘 필요
        return FileType.TYPE_NUMBER;
    }

    //TODO :: 파일로 들어오는 것과 폴더로 들어올 때 분기해서 활성화처리 해주기
    public InputOption getInputOption(){
        //활성화된 radioButton 리턴
        //if()
        return (InputOption)inputOptionCombobox.getSelectedItem();
    }

    //todo x와 y구분해서 경로 리턴하기. 어떤 object에서도 같은값을 return 하기위해서 / Class로도 값을 얻을수 있게 하기 위해서 static으로 처리
    public static String getXPath(){
        return "Empty";
    }


    //이너 클래스로 재 정의
    private class FileOpenListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jFileChooser = new JFileChooser();
            //폴더와 디렉토리 둘다 가능
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int ret = jFileChooser.showOpenDialog(null);
            if(ret != JFileChooser.APPROVE_OPTION){
                JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.","경고", JOptionPane.WARNING_MESSAGE);
            }

            //TODO 폴경로는 다 확보했으나 폴더를 받았을 때 파일리스트를 넘겨줄 것인가 아니면 알아서 할 것인지 논의 필요
            String filePath = jFileChooser.getSelectedFile().getPath();
            filePathTextField.setText(filePath);
            TestInputBlock.filePath = filePath;

            // dim 설정
            // TODO 텍스트파일 이미지폴더 구분
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
                String firstLine = br.readLine();
                inputFileDim = firstLine.split(",").length;
                switch (getInputOption()){
                    case ONLY_START:
                    case ONLY_END:
                        inputFileDim = 1;
                        break;
                    case REMOVE_THE_END:
                    case REMOVE_THE_START:
                        inputFileDim -=1;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
