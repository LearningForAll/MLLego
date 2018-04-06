package Component.BlockObserver;

import Component.BlockComponent.Block;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by chaebyeonghun on 2018. 4. 6..
 */
public interface BlockObserver {

    void update(Block block);

}
