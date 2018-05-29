package Component.BlockActionListener;

import Component.TestBlockComponent.TestBlock;
import Presentation.Controller.ModelTestController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Created by LG on 2018-05-28.
 */
public class DeleteModelTestActionListener implements ActionListener{
    TestBlock testBlock;

    public DeleteModelTestActionListener(){

    }
    public DeleteModelTestActionListener(TestBlock testBlock){this.testBlock=testBlock;}
    @Override
    public void actionPerformed(ActionEvent e) {
        ModelTestController.getInstance().deleteTestBlock(testBlock);
    }
}
