package Component.BlockComponent;

import Component.BlockBatchModel.BlockTemplateComponent.PreprocessorBlockTemplate;
import Const.FileType;
import Const.PreprocessorType;
import javax.swing.*;
import java.awt.*;

/**
 * Created by chaebyeonghun on 2018. 3. 27..
 */
public class PreprocessorBlock extends Block {

    //TODO 옵션 추가와 파일 타입에 따른 UI 구성이 필요

    public JComboBox<PreprocessorType> preprocessorTypeCombobox;
    FileType fileType;
    boolean IsXData;

    public PreprocessorBlock(){
        super();
        nameLabel = new JLabel(getClass().getSimpleName());
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        preprocessorTypeCombobox = new JComboBox<>(PreprocessorType.values());
        GridLayout layout=new GridLayout(2,1);
        setLayout(layout);
        setSize(200,50);
        width=getWidth();
        add(flowPanel);
        add(preprocessorTypeCombobox);
        flowPanel.add(nameLabel);
        preprocessorTypeCombobox.setEnabled(false);

        flowPanel.setBackground(new Color(243,115,50));
        setVisible(true);
    }
    public PreprocessorBlock(PreprocessorBlockTemplate blockTemplate){
        this();
        preprocessorTypeCombobox.setSelectedItem(blockTemplate.getPreprocessorType());
        setLocation(blockTemplate.getPositionX(), blockTemplate.getPositionY());
    }
    @Override
    protected String getBlockAttrStr() {
        return null;
    }

    @Override
    public boolean isNextBlockConnectable(Block block) {
        return (block instanceof LayerBlock || block instanceof ClassifierBlock);
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
        return (nextBlocks.size() != 0);
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return (previousBlocks.size() != 0);
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

    public PreprocessorType getPreprocessorType(){
        return (PreprocessorType)preprocessorTypeCombobox.getSelectedItem();
    }

    public boolean isXData() {
        return IsXData;
    }

    public void setXData(boolean XData) {
        IsXData = XData;
    }
}
