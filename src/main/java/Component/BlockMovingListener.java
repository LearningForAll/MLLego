package Component;

import Models.Coords;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by chaebyeonghun on 2018. 3. 29..
 */
// 리스너 대신 필요한 것만 쓸 수 있는 어뎁터 사용
public class BlockMovingListener extends MouseAdapter {


    JPanel panel;
    boolean isDragged;
    public BlockMovingListener(JPanel panel) {
        this.panel = panel;
        isDragged = false;

        this.panel.addMouseListener(this);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        //TODO Point 클래스와 Coords를 잘 구별해야할듯?
        /*if(panel.contains(new Coords(panel.getX(),panel.getY()))){
            //#1 마우스 버튼 누름
            //사각형내 마우스 클릭 상대 좌표를 구함
            //현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
            offX = me.getX() - box.x;
            offY = me.getY() - box.y;

            //드래그 시작을 표시
            isDragged = true;

        }*/

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        isDragged = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        /*
        if(isDragged){
            box.x = me.getX() - offX;
            box.y = me.getY() - offY;
        }*/

    }
}
