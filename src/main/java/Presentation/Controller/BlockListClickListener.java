package Presentation.Controller;

import Component.BlockComponent.ClassifierBlock;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by LG on 2018-04-12.
 */
public class BlockListClickListener implements MouseListener {


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof ClassifierBlock){
            BlockPlacementController.getInstance().addBlock(new ClassifierBlock());
            System.out.print("됫지롱!");
        }else{
            System.out.print("안됫어여 ㅠㅠㅠ");
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
