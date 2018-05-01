package Component.BlockComponent;

import Component.BlockActionListener.ExtendActionListener;
import Util.FileUtil;

import javax.swing.*;
import java.awt.*;

public abstract class ExtendableBlock extends Block {

    JButton extendButton;
    public ExtendableBlock(){
        super();
        //nameLabel = new JLabel(blockName);
        //nameLabel.setForeground(Color.white);
        //nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        extendButton=new JButton(new ImageIcon(FileUtil.getResourcePath("icon/plus.png")));
        extendButton.addActionListener(new ExtendActionListener(this));
        extendButton.setPreferredSize(new Dimension(16, 16));
        //flowPanel.add(nameLabel);
        flowPanel.add(extendButton);
        setVisible(true);
    }

    /*
    public ExtendableBlock(String name){
        super(name);
        extendButton=new JButton(new ImageIcon(FileUtil.getResourcePath("icon/plus.png")));
        extendButton.addActionListener(new ExtendActionListener(this));
        extendButton.setPreferredSize(new Dimension(16, 16));
        flowPanel.add(extendButton);
        setVisible(true);
    }
    */
    @Override
    public boolean isNextBlockConnected() {
        return false;
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return false;
    }
}
