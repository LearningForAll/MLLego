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


    private BlockPlacementController() {
        blocks = new ArrayList<>();
    }

    public static BlockPlacementController getInstance() {
        return instance;
    }

    public void setPanel(BlockPlacementPanel panel) {
        this.panel = panel;
    }

    /**
     * 이 함수로 블록 BlockPlacementController에 블록 추가
     **/
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
        //TODO 연결된 블록도 같이 해야함
        tempBlocks.addAll(blocks);
        tempBlocks.remove(block);


        if(block.isPreviousBlockConnected()){

            System.out.println("연결이 끊겼다!!");
            if(!block.checkTopBorder()){
                block.disconnectBlock();
            }
        }else{

            for (Block block1 : tempBlocks) {
                if (checkTopCloseBlock(block, block1) && block1.isNextBlockConnectable(block) && !block1.isNextBlockConnected() && block.isPreviousBlockConnectable(block1)) {
                    System.out.println("탑이 빛날");
                    block.blinkTop();
                    block1.blinkBottom();
                }else if(checkBottomCloseBlock(block, block1) && block1.isPreviousBlockConnectable(block) && !block1.isPreviousBlockConnected() && block.isNextBlockConnectable(block1)){
                    System.out.println("바텀이 빛날때");
                    block.blinkBottom();
                    block1.blinkTop();
                }
                else{
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
        if (block.checkBorder()) {
            if (block.checkTopBorder()) {
                for (Block block1 : tempBlocks) {
                    if (block1.checkBottomBorder()) {
                        block.registerPreviousBlock(block1);
                        block1.registerNextBlock(block);
                        System.out.println(block.isPreviousBlockConnected());
                        System.out.println(block1.isNextBlockConnected());
                    }
                }
            } else {// bottom이 빛날때
                for (Block block1 : tempBlocks) {
                    if (block1.checkTopBorder()) {
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
    private boolean checkTopCloseBlock(Block block, Block block1){
        //내가드래그 하는 블록이 아래쪽에서 위쪽으로 접근할
        System.out.println("TOP CLOSE CHECK");
        System.out.println(block.getClass().toString() + "/" + block1.getClass().toString());
        System.out.println("/"+block.getY()+"/"+block1.getY()+"/"+block.getHeight());
        System.out.println((((block.getX() - block1.getX() < 100)
                && (block.getX() - block1.getX() > -100)
                && (block.getY() + block.getHeight() - block1.getY() > -150)
                && (block.getY() + block.getHeight() - block1.getY() < 0))));
        return ((block.getX() - block1.getX() < 100)
                && (block.getX() - block1.getX() > -100)
                && (block1.getY() + block1.getHeight() - block.getY() > -50)
                && (block1.getY() + block1.getHeight() - block.getY() < 0));
    }
    private boolean checkBottomCloseBlock(Block block, Block block1){
        System.out.println("Bottom Close Check");
        System.out.println();
        return ((block.getX() - block1.getX() < 100)
                && (block.getX() - block1.getX() > -100)
                && (block1.getY() + block1.getHeight() - block.getY() < 150)
                && (block1.getY() + block1.getHeight() - block.getY() > 0));
    }

    public void saveBlockBatch(String name) {
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

    public void loadBlockBatch(String filePath) {
        try {
            // 직렬화된 객체를 로드해옴.
            List<Block> blockList;
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            blockList = (List<Block>) ois.readObject();
            ois.close();

            // 패널의 모든 블록삭제
            this.blocks.clear();
            panel.deleteAllBlock();

            this.blocks = blockList;

            panel.addBlocks(this.blocks);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO 만약 블록이 PlacementController에 있다면 그 배치를 저장하겠냐고 메시지를 띄운다.
    }


}
