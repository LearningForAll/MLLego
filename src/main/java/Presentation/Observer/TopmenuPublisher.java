package Presentation.Observer;

import Presentation.Controller.TopmenuController;
import Presentation.View.TopMenuPanel;

/**
 * Created by chaebyeonghun on 2018. 4. 13..
 */
public interface TopmenuPublisher {

    public void setObserver(TopmenuController controller);
}
