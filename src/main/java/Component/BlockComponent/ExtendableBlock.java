package Component.BlockComponent;

import Component.BlockActionListener.ExtendActionListener;
import Component.BlockActionListener.RevertExtendActionListener;
import Util.FileUtil;

import javax.swing.*;
import java.awt.*;

public abstract class ExtendableBlock extends Block {

    public ExtendableBlock(){
        super();
        extendButton=new JButton(new ImageIcon(FileUtil.getResourcePath("icon/plus.png")));
        extendButton.addActionListener(new ExtendActionListener(this));
        extendButton.setPreferredSize(new Dimension(16, 16));
        extendButton.setEnabled(false);

        revertExtendButton=new JButton(new ImageIcon(FileUtil.getResourcePath("icon/undo.png")));
        revertExtendButton.addActionListener(new RevertExtendActionListener(this));
        revertExtendButton.setPreferredSize(new Dimension(16, 16));
        revertExtendButton.setEnabled(false);
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
