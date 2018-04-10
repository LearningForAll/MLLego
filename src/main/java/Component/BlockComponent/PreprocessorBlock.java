package Component.BlockComponent;

import javax.swing.*;
import java.awt.*;
import Const.FileType;

import javax.swing.*;

/**
 * Created by chaebyeonghun on 2018. 3. 27..
 */
public class PreprocessorBlock extends Block {

    // 몇개 더 추가해야하는데 기억이 안나서 이것만 해놓음.
    //TODO 옵션 추가와 파일 타입에 따른 UI 구성이 필요
    JRadioButton rgbOptionRadioButton;
    FileType fileType;


    public PreprocessorBlock(){
        JLabel nameLabel=new JLabel("Preprocessor Block");
        nameLabel.setForeground(Color.white);
        add(nameLabel);
        setBackground(new Color(243,115,50));
        setVisible(true);
    }
    @Override
    String getBlockAttrStr() {
        return null;
    }


    @Override
    public boolean isNextBlockConnectable(Block block) {
        return (block instanceof LayerBlock);
    }

    @Override
    public boolean isPreviousBlockConnectable(Block block) {
        if(block instanceof InputBlock){
            getFileType((InputBlock) block);
        }
        return (block instanceof InputBlock);
    }

    @Override
    public boolean isNextBlockConnected() {
        return false;
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return false;
    }

    // Preprocessor랑 Input블록이 연결될때 무조건 호출해야하는 함수
    public void getFileType(InputBlock inputBlock){
        //이전 Input 블록에서 파일타입을 가져옴
        fileType = inputBlock.getFileType();
    }
}
