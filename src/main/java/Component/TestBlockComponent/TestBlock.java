package Component.TestBlockComponent;

import Component.BlockObserver.TestBlockObserver;
import Component.BlockObserver.TestBlockPublisher;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public abstract class TestBlock extends JPanel implements TestBlockPublisher {
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


}
