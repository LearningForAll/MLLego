package Component.BlockComponent;

import Component.BlockActionListener.ExtendActionListener;
import Util.FileUtil;

import javax.swing.*;
import java.awt.*;

public abstract class ExtendableBlock extends Block {

    JButton extendButton;

    private int extendSize = 1;
    public ExtendableBlock(){
        super();
        extendButton=new JButton(new ImageIcon(FileUtil.getResourcePath("icon/plus.png")));
        extendButton.addActionListener(new ExtendActionListener(this));
        extendButton.setPreferredSize(new Dimension(16, 16));
        flowPanel.add(extendButton);
        setVisible(true);
    }

    @Override
    public boolean isNextBlockConnected() {
        // 연결되었지만 블록공간이 남을경우 nextBlock이 올수있음
        return (extendSize - nextBlocks.size() == 0);
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return (extendSize - previousBlocks.size() == 0);
    }

    public int getExtendSize(){
        return extendSize;
    }
    public int addExtendSize(){
        extendSize++;
        return extendSize;
    }
}
