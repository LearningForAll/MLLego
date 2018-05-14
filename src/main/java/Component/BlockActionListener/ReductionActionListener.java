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
    int beforeFlowPanelHeight;
    LayoutManager beforeLayout;
    public ReductionActionListener(Block block) {
        this.block = block;
        this.blockName=block.blockName;
    }


    //TODO :: 블록들이 연결되어 있을 때 Reduct를 수행하면 일어나는 버그들 잡기(연결부분이랑 같이 보기)
    @Override
    public void actionPerformed(ActionEvent e) {

        if (isReducted) {
            System.out.println("isReducted(true) : block.flowPanel.getHeight()"+block.flowPanel.getHeight());
            block.diff=block.flowPanel.getHeight()-beforeFlowPanelHeight;
            block.setLayout(beforeLayout);
            block.setSize(block.getWidth(), beforeHeight);
            block.setFollowBlockPosition(isReducted);
            block.setLocation(block.getX(), block.getY()-beforeHeight+beforeFlowPanelHeight);
            block.revalidate();
            block.repaint();
            isReducted = false;

        } else {
            System.out.println("isReducted(false):" + block.flowPanel.getHeight());
            block.setLocation(block.getX(), block.getY()+block.getHeight()-block.flowPanel.getHeight()); //얜 맞음
            block.setFollowBlockPosition(isReducted);
            beforeHeight = block.getHeight();
            beforeFlowPanelHeight=block.flowPanel.getHeight();
            beforeLayout = block.getLayout();
            block.setLayout(new BoxLayout(block, BoxLayout.Y_AXIS));
            block.setSize(block.getWidth(), block.flowPanel.getHeight());
            block.revalidate();
            block.repaint();
            isReducted = true;
        }

    }
}
