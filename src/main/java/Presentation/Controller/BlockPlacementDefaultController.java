package Presentation.Controller;

import Presentation.View.BlockPlacementDefault;

/**
 * Created by chaebyeonghun on 2018. 5. 30..
 */
public class BlockPlacementDefaultController {

    private static BlockPlacementDefaultController instance = new BlockPlacementDefaultController();

    BlockPlacementDefault defaultPanel;

    BlockPlacementDefaultController(){

    }
    public static BlockPlacementDefaultController getInstance(){
        return instance;
    }
    public void setDefaultPanel(BlockPlacementDefault defaultPanel){
        this.defaultPanel = defaultPanel;
    }
    public void changeModelTestTab(){
        if(this.defaultPanel.getSelectedIndex() != 1){
            this.defaultPanel.setSelectedIndex(1);
        }
    }
    public void changeBlockPlacementTab(){
        if(this.defaultPanel.getSelectedIndex() != 0){
            this.defaultPanel.setSelectedIndex(0);
        }
    }
    public void changeModelResult(){
        if(this.defaultPanel.getSelectedIndex() != 2){
            this.defaultPanel.setSelectedIndex(2);
        }
    }

    public int getCurrentTabIndex(){
        return this.defaultPanel.getSelectedIndex();
    }

    public void resetResultPanel(){
        defaultPanel.resetResultPanel();
    }
}
