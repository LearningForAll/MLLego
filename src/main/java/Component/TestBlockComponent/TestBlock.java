package Component.TestBlockComponent;

import Component.BlockActionListener.DeleteActionListener;
import Component.BlockActionListener.DeleteModelTestActionListener;
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

public abstract class TestBlock extends JPanel implements TestBlockPublisher {

    private LineBorder basicBorder = new LineBorder(Color.black);
    private JPopupMenu popupMenu;
    private JMenuItem delete;
    public int width;
    public JLabel nameLabel;
    //public String blockName;
    public JPanel flowPanel;
    public TestBlockObserver observer;

    public TestBlock(){
        this.setBorder(basicBorder);
        popupMenu = new JPopupMenu();
        delete = new JMenuItem("Delete");
        popupMenu.add(delete);
        flowPanel = new JPanel(new FlowLayout());
    }

      @Override
    public void setObserver(TestBlockObserver observer) {
        this.observer = observer;
    }

}
