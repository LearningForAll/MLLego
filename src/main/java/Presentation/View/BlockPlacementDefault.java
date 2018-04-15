package Presentation.View;

/**
 * Created by LG on 2018-03-30.
 */
import javax.swing.*;
import java.awt.*;


//블록배치창의 탭패널이고 여기에 BlockPlacementPanel이랑 ModelTestPanel이 패널로 들어감.
public class BlockPlacementDefault extends JTabbedPane{
    BlockPlacementPanel blockPlacementPanel=new BlockPlacementPanel();
    ModelTestPanel modelTestPanel=new ModelTestPanel();
    JScrollPane scroll;

    public BlockPlacementDefault(){

        add("Block Placement", blockPlacementPanel);
        add("Model Test", modelTestPanel);
        setVisible(true);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1200,600);
    }

}