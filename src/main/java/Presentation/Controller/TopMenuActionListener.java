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
                        tfBuilder.training();
                        break;
                    case 1: // model test
                        TFBuilder.runModelTestBlock();
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
