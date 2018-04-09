package Component.BlockEventThread;

import Component.BlockComponent.Block;

import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 8..
 */
public class BlockDragEventThread extends Thread {

    private static boolean isEventTrigger = false;

    public void run(List<Block> blocks, Block block){

        while(true){

            for (Block block1 : blocks){
                if(block.getX() - block1.getX() > 40 && block.getX() - block1.getX() > -40 && block1.getY() - block.getY() < 100 && block1.getY() - block.getY() > 10){
                    block1.blinkTop();
                }else{
                    block1.revertBlock();
                }
                if(block.getX() - block1.getX() > 40 && block.getX() - block1.getX() > -40 && block.getY() - block1.getY() < 100 && block.getY() - block1.getY() > 10){
                    block1.blinkTop();
                }else{
                    block1.revertBlock();
                }
            }

            try{
                Thread.sleep(10);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            if(isEventTrigger){
                break;
            }
        }
    }
    public void triggerEvent(){
        isEventTrigger = true;
    }
}
