package Presentation.Controller;

import Presentation.Observer.TopmenuObserver;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by chaebyeonghun on 2018. 4. 13..
 */
public class TopmenuController implements TopmenuObserver{



    @Override
    public void loadBatchModel() {
        JFileChooser jFileChooser = new JFileChooser();
        //폴더와 디렉토리 둘다 가능
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // TODO 확장자 정리
        // 폴더를 선택했을 떄의 옵션도 필요하기때문에 주석처
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG","jpg");
        jFileChooser.setFileFilter(filter);
        int ret = jFileChooser.showOpenDialog(null);
        if(ret != JFileChooser.APPROVE_OPTION){
            JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.","경고", JOptionPane.WARNING_MESSAGE);
        }

        //TODO 폴경로는 다 확보했으나 폴더를 받았을 때 파일리스트를 넘겨줄 것인가 아니면 알아서 할 것인지 논의 필요
        String filePath = jFileChooser.getSelectedFile().getPath();
        BlockPlacementController.getInstance().loadBlockBatch(filePath);
    }

    @Override
    public void saveBatchModel() {
        String name = JOptionPane.showInputDialog(null, "저장할 블록배치 모델의 이름을 입력해주세요");
        BlockPlacementController.getInstance().saveBlockBatch(name);
    }
}
