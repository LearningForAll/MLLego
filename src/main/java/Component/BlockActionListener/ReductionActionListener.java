package Component.BlockActionListener;

import Component.BlockComponent.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by LG on 2018-04-11.
 */

// '- 버튼'을 눌렀을 때 블록의 이름만 보이게끔 설정
public class ReductionActionListener implements ActionListener {
    Block block;
    String blockName;
    boolean isReducted = false;
    int beforeHeight;
    LayoutManager beforeLayout;
    public ReductionActionListener(Block block) {
        this.block = block;
        this.blockName=block.blockName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (isReducted){
            block.setFollowBlockPosition(isReducted);
            block.setLayout(beforeLayout);
            block.setSize(block.getWidth(),beforeHeight);
            isReducted = false;
        }else {
            block.setFollowBlockPosition(isReducted);
            beforeHeight = block.getHeight();
            beforeLayout = block.getLayout();
            block.setLayout(new BoxLayout(block,BoxLayout.Y_AXIS));
            block.setSize(block.getWidth(),block.flowPanel.getHeight());
            isReducted = true;
        }

    }
}
