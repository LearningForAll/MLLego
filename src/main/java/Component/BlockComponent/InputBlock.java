package Component.BlockComponent;

import Models.Coords;

import javax.swing.*;

public class InputBlock extends Block {

    // 파일 Path를 가지고 있게..
    private String filePath;

    // ... 이런식으로 생겨서 누르면 파일 익스플로어를 연다.
    private JButton openFileExploreButton;
    // 불러온 파일패스를 보여주는 텍스트필드 유저가 직접 편집할수 없게 설정
    private JTextField filePathTextField;


    @Override
    String getBlockAttrStr() {
        return null;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
