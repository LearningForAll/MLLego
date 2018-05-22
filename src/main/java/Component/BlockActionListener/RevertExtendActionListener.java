package Component.BlockActionListener;

import Component.BlockComponent.Block;
import Component.BlockComponent.ExtendableBlock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by LG on 2018-05-14.
 */
public class RevertExtendActionListener implements ActionListener{
    Block block;
    int beforeWidth;
    int beforeHeight;

    public RevertExtendActionListener(Block block) {
        this.block = block;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        beforeHeight = block.getHeight();
        beforeWidth=block.getWidth();

        if(block.getWidth()>block.width){
            block.setSize(beforeWidth-block.width, beforeHeight);
            if(block.getWidth()==block.width){
                block.revertExtendButton.setEnabled(false);
            }
        }
        // Extend 사이즈도 없애줘야함
        ((ExtendableBlock)block).subExtendSize();

        block.revalidate();
    }

}
