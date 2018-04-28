package Presentation.Controller;

import Presentation.Observer.TopmenuObserver;
import Presentation.View.TopMenuPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by chaebyeonghun on 2018. 4. 13..
 */
public class TopmenuController implements TopmenuObserver{

    private static TopmenuController instance = new TopmenuController();
    private TopMenuPanel panel;
    private TopmenuController(){

    }
    public void setPanel(TopMenuPanel panel){
        this.panel = panel;
        panel.setObserver(this);
    }

    public static TopmenuController getInstance() {
        return instance;
    }

    @Override
    public void loadBatchModel() {
        JFileChooser jFileChooser = new JFileChooser();
        //폴더와 디렉토리 둘다 가능
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // TODO 확장자 정리
        // 블록이라는 확장자로 저장 ...
        FileNameExtensionFilter filter = new FileNameExtensionFilter("BLOCK","block");
        jFileChooser.setFileFilter(filter);
        int ret = jFileChooser.showOpenDialog(null);
        if(ret != JFileChooser.APPROVE_OPTION){
            JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.","경고", JOptionPane.WARNING_MESSAGE);
        }else{
            String filePath = jFileChooser.getSelectedFile().getPath();
            BlockPlacementController.getInstance().loadBlockBatch(filePath);
        }

    }

    @Override
    public void saveBatchModel() {
        String name = JOptionPane.showInputDialog(null, "저장할 블록배치 모델의 이름을 입력해주세요");
        BlockPlacementController.getInstance().saveBlockBatch(name);
    }
}
