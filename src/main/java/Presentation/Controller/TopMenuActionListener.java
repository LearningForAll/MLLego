package Presentation.Controller;

import App.MyApp;
import Component.BlockComponent.ModelBlock;
import Component.BlockComponent.TrainingBlock;
import ML.Core.Python.TensorFlow.TFBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopMenuActionListener implements ActionListener {
    String id;

    public TopMenuActionListener(String id) {
        this.id = id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (id) {
            case "run":
                BlockPlacementDefaultController controller = BlockPlacementDefaultController.getInstance();
                switch (controller.getCurrentTabIndex()){
                    case 0: // block placement
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
                        MyModelDefaultController.getInstance().setResultTab();
                        ResultController.getInstance().removeAll();
                        ResultController.getInstance().addResultLine("학습을 준비중입니다... 잠시만 기다려주세요...");
                        tfBuilder.training();
                        break;
                    case 1: // model test
                        ModelTestController modelTestController = ModelTestController.getInstance();
                        modelTestController.removeAll();
                        modelTestController.addResultLine("테스트를 진행합니다... 잠시만 기다려주세요...");
                        TFBuilder.runModelPredictTestBlock(modelTestController.getSelectedModelName(),modelTestController.getSelectedDataPath());
                }
                break;

            case "stop":
                MyApp.stop();
                break;
            default:
                System.out.println("unknown Id : " + id);
        }

    }
}
