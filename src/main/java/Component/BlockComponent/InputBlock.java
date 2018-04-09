package Component.BlockComponent;

import Const.ActivationFunc;
import Const.FileType;
import Const.InputOption;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputBlock extends Block{

    // ... 이런식으로 생겨서 누르면 파일 익스플로어를 연다.
    private JButton openFileExploreButton;
    // 불러온 파일패스를 보여주는 텍스트필드 유저가 직접 편집할수 없게 설정
    private JTextField filePathTextField;
    // csv파일이나 텍스트 파일의 경우 맨 끝의 데이터를 제거할 것인지 맨 처음의 데이터를 제거할 것인지의 옵션.
    private JRadioButton endEliminationRadioButtion;
    private JRadioButton startEliminationRadioButton;
    private JComboBox<InputOption> inputOptionCombobox;

    public InputBlock(){
        openFileExploreButton = new JButton("File");
        openFileExploreButton.addActionListener(new FileOpenListener());
        filePathTextField = new JTextField();
        // 파일패스를 직접 설정하지 못하게..
        filePathTextField.setEditable(false);
        endEliminationRadioButtion = new JRadioButton("End");
        startEliminationRadioButton = new JRadioButton("Start");
        inputOptionCombobox = new JComboBox<>(InputOption.values());

        JPanel flowPanel=new JPanel(new FlowLayout(FlowLayout.LEADING,2,2));
        openFileExploreButton.setPreferredSize(new Dimension(60,20));
        filePathTextField.setPreferredSize(new Dimension(134,20));

        flowPanel.add(openFileExploreButton);
        flowPanel.add(filePathTextField);

        JLabel nameLabel=new JLabel("Input Block");
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        TitledBorder line=new TitledBorder(new LineBorder(Color.black));
        GridLayout layout=new GridLayout(3,1);
        setLayout(layout);
        setBorder(line);
        add(nameLabel);
        add(flowPanel);
        add(inputOptionCombobox);
        inputOptionCombobox.setEnabled(false);
        setBackground(new Color(243, 115, 50));
        setVisible(true);
    }

    //이너 클래스로 재 정의
    private class FileOpenListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jFileChooser = new JFileChooser();
            //폴더와 디렉토리 둘다 가능
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            // TODO 확장자 정리
            // 폴더를 선택했을 떄의 옵션도 필요하기때문에 주석처
           // FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG","jpg");
           // jFileChooser.setFileFilter(filter);

            int ret = jFileChooser.showOpenDialog(null);
            if(ret != JFileChooser.APPROVE_OPTION){
                JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.","경고", JOptionPane.WARNING_MESSAGE);
            }

            //TODO 폴경로는 다 확보했으나 폴더를 받았을 때 파일리스트를 넘겨줄 것인가 아니면 알아서 할 것인지 논의 필요
            String filePath = jFileChooser.getSelectedFile().getPath();
            filePathTextField.setText(filePath);

        }
    }
    @Override
    String getBlockAttrStr() {
        return null;
    }

    @Override
    public boolean isNextBlockConnectable(Block block) {
        // 블록이 연결되어 있을 경우
        return (block instanceof PreprocessorBlock || block instanceof ConvolutionLayerBlock);

    }

    //시작 블록
    @Override
    public boolean isPreviousBlockConnectable(Block block) {
        return false;
    }

    @Override
    public boolean isNextBlockConnected() {
        return false;
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return false;
    }

    public FileType getFileType(){
        //파일 타입을 판단하는 알고리즘 필요

        return FileType.TYPE_NUMBER;
    }

    //TODO :: 파일로 들어오는 것과 폴더로 들어올 때 분기해서 활성화처리 해주기

    public InputOption getInputOption(){
        //활성화된 radioButton 리턴
        return (InputOption)inputOptionCombobox.getSelectedItem();
    }

}
