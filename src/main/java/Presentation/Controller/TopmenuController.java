package Presentation.Controller;

import App.MyApp;
import Component.BlockComponent.ModelBlock;
import Component.BlockComponent.TrainingBlock;
import ML.Core.Python.TensorFlow.TFBuilder;
import Presentation.Observer.TopmenuObserver;
import Presentation.View.TopMenuPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


/**
 * Created by chaebyeonghun on 2018. 4. 13..
 */
public class TopmenuController implements TopmenuObserver {

    private static TopmenuController instance = new TopmenuController();
    private TopMenuPanel panel;

    private TopmenuController() {

    }

    public void setPanel(TopMenuPanel panel) {
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
        String defaultPath = file.getAbsolutePath() + "/bin/" + MyApp.projectTitle;
        System.out.println(defaultPath);
        JFileChooser jFileChooser = new JFileChooser(defaultPath);
        //폴더와 디렉토리 둘다 가능
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // 블록이라는 확장자로 저장 ...
        FileNameExtensionFilter filter = new FileNameExtensionFilter("BLOCK", "block");
        jFileChooser.setFileFilter(filter);

        int ret = jFileChooser.showOpenDialog(null);
        if (ret != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
        } else {
            String filePath = jFileChooser.getSelectedFile().getPath();
            BlockPlacementController.getInstance().loadBlockBatch(filePath);

        }

    }

    @Override
    public void saveBatchModel() {
        String name = JOptionPane.showInputDialog(null, "저장할 블록배치 모델의 이름을 입력해주세요");
        while (name.contains(".")) {
            JOptionPane.showMessageDialog(null, "이름에 .을 포함할 수 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
            name = JOptionPane.showInputDialog(null, "저장할 블록배치 모델의 이름을 입력해주세요");
        }
        File file = new File(BlockPlacementController.getInstance().getStorePath(name));
        if (file.isFile()) {
            int ans = JOptionPane.showConfirmDialog(null, "이미 존재하는 파일입니다. 덮어 쓰시겠습니까?", "경고", JOptionPane.YES_NO_CANCEL_OPTION);

            if (ans == 0) {
                //Yes 일떄
                BlockPlacementController.getInstance().saveBlockBatch(name);
            } else {
                //No 일때
                name = JOptionPane.showInputDialog(null, "저장할 블록배치 모델의 이름을 입력해주세요");
                while (name.contains(".")) {
                    JOptionPane.showMessageDialog(null, "이름에 .을 포함할 수 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
                    name = JOptionPane.showInputDialog(null, "저장할 블록배치 모델의 이름을 입력해주세요");
                }
            }
        } else {
            BlockPlacementController.getInstance().saveBlockBatch(name);
        }
    }

    class TopMenuActionListener implements ActionListener {
        String id;

        public TopMenuActionListener(String id) {
            this.id = id;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (id) {
                case "run":
                    TrainingBlock trainingBlock = BlockPlacementController.getInstance().getTrainingBlock();
                    if (trainingBlock == null) {
                        JOptionPane.showMessageDialog(null, "Training 블록이 없습니다", "ERROR", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    ModelBlock modelBlock = BlockPlacementController.getInstance().getModelBlock();
                    if (modelBlock== null) {
                        JOptionPane.showMessageDialog(null, "Model 블록이 없습니다", "ERROR", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    TFBuilder tfBuilder = new TFBuilder();
                    boolean result = tfBuilder.generateCodeFile(trainingBlock,modelBlock.getName());
                    if (!result) return;
                    tfBuilder.training();
                    break;
                case "stop":
                    MyApp.stop();
                    break;
                default:
                    System.out.println("unknown Id : " + id);
            }

        }
    }
}

