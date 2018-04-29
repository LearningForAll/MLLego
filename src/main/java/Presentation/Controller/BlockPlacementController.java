package Presentation.Controller;

import App.MyApp;
import Component.BlockComponent.Block;
import Component.BlockObserver.BlockObserver;
import Presentation.View.BlockPlacementPanel;

import java.io.*;
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

        if (block.isNextBlockConnected() && !block.isPreviousBlockConnected()) {
            //다음 블록이 있을때..
            //LOGIC 가장 최 하단의 블록을 구해서 조건을 걸어야함

            for (Block block1 : tempBlocks) {
                if (checkTopCloseBlock(block, block1) && block1.isNextBlockConnectable(block) && !block1.isNextBlockConnected() && block.isPreviousBlockConnectable(block1)) {
                    //만약 아래에서 위로 갔을 경우 즉 드래그 되는 블록이 탑에서 반짝거릴 경우
                    block.blinkTop();
                    block1.blinkBottom();
                    break;
                } else {
                    // 위에서 아래로 갔을 경우
                    if (checkBottomCloseBlock(block.getLastConnectedBlock(), block1) && block1.isPreviousBlockConnectable(block.getLastConnectedBlock()) && !block1.isPreviousBlockConnected() && block.getLastConnectedBlock().isNextBlockConnectable(block1)) {
                        block.getLastConnectedBlock().blinkBottom();
                        block1.blinkTop();
                        break;
                    }else{
                        block.revertBlock();
                        block.getLastConnectedBlock().revertBlock();
                        block1.revertBlock();
                    }
                }
            }


        } else {
            if (block.isPreviousBlockConnected()) {

                if (!block.checkTopBorder()) {
                    //블록이 끊길때
                    block.disconnectBlock();
                }
            } else {

                for (Block block1 : tempBlocks) {
                    if (checkTopCloseBlock(block, block1) && block1.isNextBlockConnectable(block) && !block1.isNextBlockConnected() && block.isPreviousBlockConnectable(block1)) {
                        block.blinkTop();
                        block1.blinkBottom();
                        break;
                    } else if (checkBottomCloseBlock(block, block1) && block1.isPreviousBlockConnectable(block) && !block1.isPreviousBlockConnected() && block.isNextBlockConnectable(block1)) {

                        block.blinkBottom();
                        block1.blinkTop();
                        break;
                    } else {
                        //TODO 오류 찾음 여기서 아마 배열순서때문에 오류가 발생했을 가능성이 농후
                        block.revertBlock();
                        block1.revertBlock();
                    }
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
        //TODO 만약 최하단의 블록이 조건이라면 그에맞는 분기도 필요
        if (block.checkBorder()) {// 먼저 현재 블록의 Border을 체크
            if (block.checkTopBorder()) {// 그다음 위에가 활성화 되었는지 체크
                for (Block block1 : tempBlocks) {// 모든 블록을 검사하면서 바텀이 빛나는 블록이있는지 체크
                    if (block1.checkBottomBorder()) {
                        block.registerPreviousBlock(block1);
                        block1.registerNextBlock(block);
                        // 등록하면 종료
                        break;
                    }
                }
            } else {// bottom이 빛날때
                for (Block block1 : tempBlocks) {
                    if (block1.checkTopBorder()) {
                        block.registerNextBlock(block1);
                        block1.registerPreviousBlock(block);
                        break;
                    }
                }
            }
            //블록 원상태로 복구
            for (Block block1 : blocks) {
                block1.revertBlock();
            }
        } else {
            //드래그 되고있는 블록에 대해 연결된 블록중 마지막 블록이 빛나는지 체크
            if(block.getLastConnectedBlock().checkBorder()){
                if(!block.checkTopBorder()) {// bottom이 빛날때
                    for (Block block1 : tempBlocks) {
                        if (block1.checkTopBorder()) {
                            block.getLastConnectedBlock().registerNextBlock(block1);
                            block1.registerPreviousBlock(block.getLastConnectedBlock());
                            break;
                        }
                    }
                }
            }
            //블록 원상태로 복구.
            for (Block block1 : blocks) {
                block1.revertBlock();
            }

        }


    }
    // block 을 기준으로 첫번째 인자가 기준블록이면 기준블록이 아래 그 반대의 경우는 인자 위치를 바꿔주면됌

    // TODO 논리 수정 필요 말이안됌
    private boolean checkTopCloseBlock(Block block, Block block1) {
        //내가드래그 하는 블록이 아래쪽에서 위쪽으로 접근할
        return ((block.getX() - block1.getX() < 50)
                && (block.getX() - block1.getX() > -50)
                && (block1.getY() + block1.getHeight() - block.getY() > -30)
                && (block1.getY() + block1.getHeight() - block.getY() < 0));
    }

    private boolean checkBottomCloseBlock(Block block, Block block1) {

        return ((block.getX() - block1.getX() < 50)
                && (block.getX() - block1.getX() > -50)
                && (block.getY() + block.getHeight() - block1.getY() > -30)
                && (block.getY() + block.getHeight() - block1.getY() < 0));
    }


    public void saveBlockBatch(String name) {
        // save 해야할 것
        // 블록 객체 자체를 직렬화 하여 저장..
        try {
            //  ".block으로 저장
            try{
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getStorePath(name)));
                oos.writeObject(blocks);
                oos.close();
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }

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

    public String getStorePath(String name){

        //파일 이름을 받아서 파일 전체 저장 경로를 구하는 함수
        File file = new File("");
        System.out.println(file.getAbsolutePath() + "/bin/" + MyApp.projectTitle+ "/" + name + ".block");
        return file.getAbsolutePath() + "/bin/" + MyApp.projectTitle +"/"+ name + ".block";
    }
}
