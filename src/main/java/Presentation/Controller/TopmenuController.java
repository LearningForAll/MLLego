package Presentation.Controller;

import App.MyApp;
import Presentation.Observer.TopmenuObserver;
import Presentation.View.TopMenuPanel;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
        //Block파일이 저장되어있는 Default Directory 로드
        File file = new File("");
        String defaultPath = file.getAbsolutePath() + "/blocks/" + MyApp.projectTitle;
        JFileChooser jFileChooser = new JFileChooser(defaultPath);
        //폴더와 디렉토리 둘다 가능
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
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
        while(name.contains(".")){
            JOptionPane.showMessageDialog(null, "이름에 .을 포함할 수 없습니다.","경고", JOptionPane.WARNING_MESSAGE);
            name = JOptionPane.showInputDialog(null, "저장할 블록배치 모델의 이름을 입력해주세요");
        }
        File file = new File(BlockPlacementController.getInstance().getStorePath(name));
        if(file.isFile()){
            int ans = JOptionPane.showConfirmDialog(null, "이미 존재하는 파일입니다. 덮어 쓰시겠습니까?", "경고", JOptionPane.YES_NO_CANCEL_OPTION);

            if(ans == 0){
                //Yes 일떄
                BlockPlacementController.getInstance().saveBlockBatch(name);
            }else{
                //No 일때
                name = JOptionPane.showInputDialog(null, "저장할 블록배치 모델의 이름을 입력해주세요");
                while(name.contains(".")){
                    JOptionPane.showMessageDialog(null, "이름에 .을 포함할 수 없습니다.","경고", JOptionPane.WARNING_MESSAGE);
                    name = JOptionPane.showInputDialog(null, "저장할 블록배치 모델의 이름을 입력해주세요");
                }
            }
        }else{
            BlockPlacementController.getInstance().saveBlockBatch(name);
        }
    }

}
