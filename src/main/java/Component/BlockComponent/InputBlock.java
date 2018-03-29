package Component.BlockComponent;

import Models.Coords;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputBlock extends Block{

    // ... 이런식으로 생겨서 누르면 파일 익스플로어를 연다.
    private JButton openFileExploreButton;
    // 불러온 파일패스를 보여주는 텍스트필드 유저가 직접 편집할수 없게 설정
    private JTextField filePathTextField;


    public InputBlock(){
        openFileExploreButton = new JButton();
        openFileExploreButton.addActionListener(new FileOpenListener());
        filePathTextField = new JTextField();
        // 파일패스를 직접 설정하지 못하게..
        filePathTextField.setEditable(false);

    }

    //이너 클래스로 재 정의
    private class FileOpenListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jFileChooser = new JFileChooser();
            // TODO 확장자 정리
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG","jpg");
            jFileChooser.setFileFilter(filter);


            int ret = jFileChooser.showOpenDialog(null);
            if(ret != JFileChooser.APPROVE_OPTION){
                JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.","경고", JOptionPane.WARNING_MESSAGE);
            }

            String filePath = jFileChooser.getSelectedFile().getPath();
            filePathTextField.setText(filePath);
        }
    }
    @Override
    String getBlockAttrStr() {
        return null;
    }


}
