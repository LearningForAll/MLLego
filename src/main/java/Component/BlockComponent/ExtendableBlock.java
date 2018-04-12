package Component.BlockComponent;

import javax.swing.*;
import java.awt.*;

public abstract class ExtendableBlock extends Block {

    JButton extendButton;
    public ExtendableBlock(){
        super();
    }

    public ExtendableBlock(String name){
        super(name);
        extendButton=new JButton(new ImageIcon("images/icon/plus.png"));
        extendButton.setPreferredSize(new Dimension(16, 16));
        flowPanel.add(extendButton);
        setVisible(true);
    }
    @Override
    public boolean isNextBlockConnected() {
        return false;
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return false;
    }
}
