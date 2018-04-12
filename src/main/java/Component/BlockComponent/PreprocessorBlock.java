package Component.BlockComponent;

import javax.swing.*;
import java.awt.*;
import Const.FileType;
import Const.PreprocessorType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Created by chaebyeonghun on 2018. 3. 27..
 */
public class PreprocessorBlock extends Block {

    //TODO 옵션 추가와 파일 타입에 따른 UI 구성이 필요

    JComboBox<PreprocessorType> preprocessorTypeCombobox;
    FileType fileType;

    public PreprocessorBlock(String blockName){
        super(blockName);
        preprocessorTypeCombobox = new JComboBox<>(PreprocessorType.values());
        GridLayout layout=new GridLayout(2,1);
        setLayout(layout);
        add(flowPanel);
        add(preprocessorTypeCombobox);
        preprocessorTypeCombobox.setEnabled(false);

        flowPanel.setBackground(new Color(243,115,50));
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

    //TODO : preprocessor 분기에 따라 활성화 시켜줄 요소들 보여주기
    public void activateElement(InputBlock inputBlock){
        if(fileType==FileType.TYPE_TEXT){
            //preprocessorTypeCombobox.getSelectedIndex(1).setEnabled(true);
        }
        else if(fileType==FileType.TYPE_IMAGE){

        }
        else if(fileType==FileType.TYPE_NUMBER){

        }
    }

}
