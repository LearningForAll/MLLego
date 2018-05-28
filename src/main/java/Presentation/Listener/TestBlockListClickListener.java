package Presentation.Listener;

import Component.BlockComponent.PreprocessorBlock;
import Component.BlockComponent.XInputBlock;
import Component.TestBlockComponent.TestInputBlock;
import Component.TestBlockComponent.TestPreProcessorBlock;
import Presentation.Controller.ModelTestController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TestBlockListClickListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof TestInputBlock){
            ModelTestController.getInstance().addTestBlock(new TestInputBlock());
        } else if(e.getSource() instanceof TestPreProcessorBlock){
            ModelTestController.getInstance().addTestBlock(new TestPreProcessorBlock());
        }

        //TODO 해성이 여기다가 추가하면댐
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
