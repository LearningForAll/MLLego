package Presentation.Controller;

import Component.BlockComponent.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by LG on 2018-04-12.
 */
public class BlockListClickListener implements MouseListener {

    //BlockListPanel에 마우스 클릭 이벤트 들어오면 BlockPlacement 컨트롤러의 addBlock에 해당되는 블록 추가
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof ClassifierBlock){
            BlockPlacementController.getInstance().addBlock(new ClassifierBlock());
        } else if(e.getSource() instanceof ConvolutionLayerBlock){
            BlockPlacementController.getInstance().addBlock(new ConvolutionLayerBlock());
        } else if(e.getSource() instanceof XInputBlock) {
            //TODO Input 제어
            XInputBlock xinput = new XInputBlock();
            xinput.setViewerMode();
            BlockPlacementController.getInstance().addBlock(xinput);
        }else if(e.getSource() instanceof YInputBlock){
            YInputBlock yinput = new YInputBlock();
            yinput.setViewerMode();
            BlockPlacementController.getInstance().addBlock(yinput);
            //yinput
        }else if(e.getSource() instanceof PreprocessorBlock){
            BlockPlacementController.getInstance().addBlock(new PreprocessorBlock());
        }else if(e.getSource() instanceof LstmBlock){
            BlockPlacementController.getInstance().addBlock(new LstmBlock());
        }else if(e.getSource() instanceof PoolingBlock){
            BlockPlacementController.getInstance().addBlock(new PoolingBlock());
        }else if(e.getSource() instanceof DenseBlock){
            BlockPlacementController.getInstance().addBlock(new DenseBlock());
        }else if(e.getSource() instanceof ModelBlock){
            BlockPlacementController.getInstance().addBlock(new ModelBlock());
        }else if(e.getSource() instanceof TrainingBlock){
            BlockPlacementController.getInstance().addBlock(new TrainingBlock());
        }
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
