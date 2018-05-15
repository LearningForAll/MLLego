package Component.BlockComponent;

import Component.BlockActionListener.ExtendActionListener;
import Util.FileUtil;

import javax.swing.*;
import java.awt.*;

public abstract class ExtendableBlock extends Block {

    JButton extendButton;

    private int extendSize = 1;
    private int connectedSize = 0;

    public ExtendableBlock(){
        super();
        extendButton = new JButton(new ImageIcon(FileUtil.getResourcePath("icon/plus.png")));
        extendButton.addActionListener(new ExtendActionListener(this));
        extendButton.setPreferredSize(new Dimension(16, 16));
        extendButton.setEnabled(false);

    }
    public boolean isBlockExtended(){
        return extendSize != 1;
    }

    @Override
    public boolean isNextBlockConnected() {
        // 연결되었지만 블록공간이 남을경우 nextBlock이 올수있음
        return (nextBlocks.size() != 0);
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return (extendSize - previousBlocks.size() == 0);
    }
    public boolean isFull(){
        return (extendSize - connectedSize == 0);
    }
    public void addConnectedSize(int size){
        connectedSize = connectedSize + size;
    }
    public void minusConnectedSize(int size){
        connectedSize = connectedSize - size;
    }

    public int getExtendSize(){
        return extendSize;
    }
    public int addExtendSize(){
        extendSize++;
        return extendSize;
    }
    public int getBlockIndex(Block block){
        return this.previousBlocks.indexOf(block);
    }
    public JButton getExtendButton(){
        return this.extendButton;
    }
    public int getConnectedSize(){
        return connectedSize;
    }

}
