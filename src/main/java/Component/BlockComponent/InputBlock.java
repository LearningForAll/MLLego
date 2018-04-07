package Component.BlockComponent;

import Const.FileType;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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


    public InputBlock(){
        openFileExploreButton = new JButton();
        openFileExploreButton.addActionListener(new FileOpenListener());
        filePathTextField = new JTextField();
        // 파일패스를 직접 설정하지 못하게..
        filePathTextField.setEditable(false);
        endEliminationRadioButtion = new JRadioButton("맨 끝 제거");
        startEliminationRadioButton = new JRadioButton("시작 제거");

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




}
