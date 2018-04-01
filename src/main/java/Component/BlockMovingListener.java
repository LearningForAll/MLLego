package Component;

import Component.BlockComponent.Block;
import Models.Coords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by chaebyeonghun on 2018. 3. 29..
 */
// 리스너 대신 필요한 것만 쓸 수 있는 어뎁터 사용
public class BlockMovingListener extends MouseAdapter {

    Block block;
    int offX, offY;
    boolean isDragged;
    public BlockMovingListener(Block block) {
        this.block = block;
        isDragged = false;

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(block.contains(new Point(e.getX(), e.getY()))){


            isDragged = true;
            offX = e.getX() - block.getX();

            offY = e.getY() - block.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isDragged = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(isDragged){
            block.setLocation(e.getX() - offX,e.getY() - offY);

        }
        block.repaint();

    }
}
