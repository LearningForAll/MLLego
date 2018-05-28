package Component.TestBlockComponent;

import Component.BlockActionListener.DeleteActionListener;
import Component.BlockComponent.Block;
import Component.BlockObserver.TestBlockObserver;
import Component.BlockObserver.TestBlockPublisher;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public abstract class TestBlock extends JPanel implements TestBlockPublisher, MouseListener {
    private int offX, offY;
    private boolean isDragged = false;
    private LineBorder basicBorder = new LineBorder(Color.black);
    public int width;
    public JLabel nameLabel;
    //public String blockName;
    public JPanel flowPanel;
    public TestBlockObserver observer;

    public TestBlock(){
        this.setBorder(basicBorder);
        flowPanel = new JPanel(new FlowLayout());
    }

      @Override
    public void setObserver(TestBlockObserver observer) {
        this.observer = observer;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    //마우스 오른쪽 버튼을 누르면 delete가 팝업창으로 뜨면서 deleteActionListener 이벤트 발생
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }



}
