package Presentation.View;

/**
 * Created by LG on 2018-03-30.
 */
import javax.swing.*;
import java.awt.*;


//블록배치창의 탭패널이고 여기에 BlockPlacementPanel이랑 ModelTestPanel이 패널로 들어감.
public class BlockPlacementDefault extends JTabbedPane{

    BlockPlacementPanel blockPlacementPanel;
    ModelTestPanel modelTestPanel;

    public BlockPlacementDefault(){
        setBackground(Color.white);
        blockPlacementPanel=new BlockPlacementPanel();
        modelTestPanel=new ModelTestPanel();
        add("Block Placement", blockPlacementPanel);
        add("Model Test", modelTestPanel);
        //add(blockPlacementPanel.scroll);
        setVisible(true);
    }

}