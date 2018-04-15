package Presentation.Controller;

import Component.BlockComponent.Block;
import Component.BlockObserver.BlockObserver;
import Presentation.View.BlockPlacementPanel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaebyeonghun on 2018. 4. 1..
 */
public class BlockPlacementController implements BlockObserver {

    // singleton pattern
    private static BlockPlacementController instance = new BlockPlacementController();
    static boolean isBlockEventEvoked = false;
    // 패널을 가지고 있음 컨트롤러니까
    BlockPlacementPanel panel;
    List<Block> blocks;


    private BlockPlacementController(){
        blocks = new ArrayList<>();
    }
    public static BlockPlacementController getInstance(){
        return instance;
    }

    public void setPanel(BlockPlacementPanel panel){
        this.panel = panel;
    }
    /**
     * 이 함수로 블록 BlockPlacementController에 블록 추가
    * **/
    public void addBlock(Block block) {

        blocks.add(block);
        //항상 스택처럼 쌓일테니까 마지막블록에 등록해줌
        block.setObserver(this);
        panel.addNewBlock(block);
        // 패널에도 블록 추가
    }

    public void removeBlock(Block block) {
        blocks.remove(block);
        // 패널에도 블록 삭제
    }


    @Override
    public void blinkBlock(Block block) {
        // 현재 드래그 되고있는 블록은 리스트에서 제거하기 위해..
        List<Block> tempBlocks = new ArrayList<>();
        // Complete 복사를 위해서
        tempBlocks.addAll(blocks);
        tempBlocks.remove(block);

        if(block.isPreviousBlockConnected()){
            // 연결이 끊긴 상태
            System.out.println("연결이 끊겼다!!");
            if(!block.checkTopBorder()){

                //블록 삭
                block.disconnectBlock();
            }
        }else{
            for (Block block1 : tempBlocks) {
                //TODO isNextBlockConnectable 함수의 로직이 완성되면 뒤에  && !block1.isNextBlockConnected() 붙임
                if (checkCloseBlock(block, block1) && block1.isNextBlockConnectable(block) && block1.isNextBlockConnected()) {
                    block1.blinkBottom();
                    block.blinkTop();
                }else{
                    // 거리에서 멀어진 블록들을 revert 시킨다.
                    block.revertBlock();
                    block1.revertBlock();
                }
                if (checkCloseBlock(block1, block) && block1.isPreviousBlockConnectable(block)){
                    block1.blinkTop();
                    block.blinkBottom();
                }else{
                    block.revertBlock();
                    block1.revertBlock();
                }
            }
        }


    }
    // 드래그가 풀리면 블록을 원래대로 되돌리고
    @Override
    public void revertOrConnectBlock(Block block) {

        List<Block> tempBlocks = new ArrayList<>();
        tempBlocks.addAll(blocks);
        tempBlocks.remove(block);
        if(block.checkBorder()){
            if(block.checkTopBorder()){
                for(Block block1 : tempBlocks){
                    if(block1.checkBottomBorder()){
                        block.registerPreviousBlock(block1);
                        block1.registerNextBlock(block);
                    }
                }
            }else{// bottom이 빛날때
                for(Block block1 : tempBlocks){
                    if(block1.checkTopBorder()){
                        block.registerNextBlock(block1);
                        block1.registerPreviousBlock(block);
                    }
                }
            }

        }
        for (Block block1 : tempBlocks) {
            block1.revertBlock();
        }
        block.revertBlock();

    }
    // block 을 기준으로 첫번째 인자가 기준블록이면 기준블록이 아래 그 반대의 경우는 인자 위치를 바꿔주면됌

    // TODO 논리 수정 필요 말이안됌
   private boolean checkCloseBlock(Block block, Block block1){
        return ((block.getX() - block1.getX() < 100)
                && (block.getX() - block1.getX() > -100)
                && (block1.getY() + block1.getY() - block.getY() < 100)
                && (block1.getY() + block1.getY() - block.getY() > - 100));
   }

   public void saveBlockBatch(String name){
        // save 해야할 것
       // 블록 객체 자체를 직렬화 하여 저장..
       try {
           //  ".block으로 저장
           ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sample.block"));
           oos.writeObject(blocks);
           oos.close();

       } catch (Exception e) {
           // TODO: handle exception
           e.printStackTrace();
       }

   }
   public void loadBlockBatch(String filePath){
       try{
           // 직렬화된 객체를 로드해옴.
           List<Block> blockList;
           ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
           blockList = (List<Block>)ois.readObject();
           ois.close();

           // 패널의 모든 블록삭제
           this.blocks.clear();
           panel.deleteAllBlock();

           this.blocks = blockList;

           panel.addBlocks(this.blocks);

       }catch (Exception e){
           e.printStackTrace();
       }

       //TODO 만약 블록이 PlacementController에 있다면 그 배치를 저장하겠냐고 메시지를 띄운다.
   }



}
