package Presentation.View;

/**
 * Created by LG on 2018-03-30.
 */
import javax.swing.*;
import java.awt.*;


//블록배치창의 탭패널이고 여기에 BlockPlacementPanel이랑 ModelTestPanel이 패널로 들어감.
public class BlockPlacementDefault extends JTabbedPane{

    private ModelTestDefault modelTestDefault;
    private ModelResultPanel modelResultPanel;
    private BlockPlacementPanel blockPlacementPanel;

    public BlockPlacementDefault(){
        setBackground(Color.white);
        blockPlacementPanel=new BlockPlacementPanel();
        modelTestDefault=new ModelTestDefault();
        modelResultPanel=new ModelResultPanel();

        add("Block Placement", blockPlacementPanel);
        add("Model Test", modelTestDefault);
        add("Model Result", modelResultPanel);

        setVisible(true);
    }

    public BlockPlacementPanel getBlockPlacementPanel() {return blockPlacementPanel;}
    public ModelTestPanel getModelTestPanel(){return modelTestDefault.modelTestPanel;}
    public ModelResultPanel getModelResultPanel(){return modelResultPanel;}
}