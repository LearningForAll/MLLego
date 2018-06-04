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
    boolean isReducted = false;
    int beforeHeight;
    int beforeFlowPanelHeight;
    LayoutManager beforeLayout;

    public ReductionActionListener(Block block) {
        this.block = block;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (isReducted) {
            block.diff=block.flowPanel.getHeight()-beforeFlowPanelHeight;
            block.setLayout(beforeLayout);
            block.setSize(block.getWidth(), beforeHeight);
            block.setFollowBlockPosition(isReducted);
            block.setLocation(block.getX(), block.getY()-beforeHeight+beforeFlowPanelHeight);
            block.revalidate();
            isReducted = false;

        } else {
            block.setLocation(block.getX(), block.getY()+block.getHeight()-block.flowPanel.getHeight());
            block.setFollowBlockPosition(isReducted);
            beforeHeight = block.getHeight();
            beforeFlowPanelHeight=block.flowPanel.getHeight();
            beforeLayout = block.getLayout();
            block.setLayout(new BoxLayout(block, BoxLayout.Y_AXIS));
            block.setSize(block.getWidth(), block.flowPanel.getHeight());
            block.revalidate();
            isReducted = true;
        }
    }
}