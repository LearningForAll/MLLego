package Component.BlockComponent;

import Component.BlockException.BlockException;
import Component.BlockObserver.BlockObserver;
import Component.BlockObserver.BlockPublisher;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;


// 관찰될 수 있는 객체
public abstract class Block extends JPanel implements MouseListener, MouseMotionListener, BlockPublisher {
    private int offX, offY;
    private boolean isDragged = false;
    private int draggedX, draggedY;

    private BlockObserver blockObserver;
    protected List<Block> previousBlocks;
    protected List<Block> nextBlocks;
    abstract String getBlockAttrStr();

    // 인자로 들어온 블록이 현재 블록의 다음 블록으로 연결 될 수 있는지 확인하는 메소드
    abstract public boolean isNextBlockConnectable(Block block);
    // 인자로 들어온 블록이 현재 블록의 이전 블록으로 연결 될 수 있는지 확인하는 메소드
    abstract public boolean isPreviousBlockConnectable(Block block);
    // 다음 블록과 연결되어 있는지 여부 연결되어있으면 true 리턴
    abstract public boolean isNextBlockConnected();
    // 이전 블록과 연결되어 있는지 여부
    abstract public boolean isPreviousBlockConnected();


    public Block(){
        nextBlocks = new ArrayList<>();
        previousBlocks = new ArrayList<>();
        this.setBorder(null);
    }

    //TODO boolean을 return 하거나 Exception 으로 Handle할수 있게
    // 인자로 넘어온 블록을 다음 블록으로 등록하는 함수
    public void registerNextBlock(Block block) throws BlockException{
        if(isNextBlockConnectable(block)){
            if (block.isPreviousBlockConnectable(this)){
                this.nextBlocks.add(block);
                block.previousBlocks.add(this);
            }else{
                throw new BlockException(block.getClass().getSimpleName() + "is not connectable Previous block for" + this.getClass().getSimpleName());
            }
        }else{
            throw new BlockException(block.getClass().getSimpleName() + "is not connectable Next block for" + this.getClass().getSimpleName());
        }
    }
    // 인자로 넘어온 블록을 이전 블록으로 등록하는 함수
    public void registerPreviousBlock(Block block) throws BlockException {
        if (isPreviousBlockConnectable(block)) {
            if (block.isNextBlockConnectable(this)) {
                this.previousBlocks.add(block);
                block.nextBlocks.add(this);
            } else {
                throw new BlockException(block.getClass().getSimpleName() + "is not connectable Next block for" + this.getClass().getSimpleName());
            }
        } else {
            throw new BlockException(block.getClass().getSimpleName() + "is not connectable Previous block for" + this.getClass().getSimpleName());
        }
    }
    /**
     *  보더를 셋해준다
     */
    public void blinkBottom(){
        this.setBorder(new MatteBorder(0, 0, 5, 0, Color.CYAN));
    }

    /**
     * 보더를 셋해준다.
     */
    public void blinkTop(){
        this.setBorder(new MatteBorder(5, 0, 0, 0, Color.CYAN));
    }


    //TODO 여기도 생성자 기본 테두리가 나오면 null 부분에 기본 테두리가 들어감
    public void revertBlock(){
        this.setBorder(null);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (contains(new Point(e.getX(), e.getY()))) {
            offX = e.getX();
            offY = e.getY();
            isDragged = true;


        }
    }

    // Pressed 에서 Dragged로 블링크 블록을 옮김 실시간 좌표와 액션을 위해
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = 0;
        int y = 0;
        if (isDragged) {
            x = e.getX()+getLocation().x-offX;
            y = e.getY()+getLocation().y-offY;
            blockObserver.blinkBlock(this);
            setLocation(x,y);
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        isDragged = false;
        blockObserver.revertOrConnectBlock(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void setObserver(BlockObserver observer){
        this.blockObserver = observer;
    }


    /**
     *
     * 블록 테두리가 기본 테두리면 return false;
     * 이벤트에 의해 바뀐 테두리면 return true;l
     *
     * */
    public boolean checkBorder(){

        //TODO 블록 테두리 코드 받으면 null 부분대신 default 코드가 들어감
        if (this.getBorder() == null){
            return false;
        }else{
            return true;
        }
    }

    /**
     * TopBorder가 event에 의해 Trigger되었는지 여부
     * equals의 인자로 정의 해놓은 Border가 들어가야함
     * */
    public boolean checkTopBorder(){
        if (this.getBorder().equals(new MatteBorder(5, 0, 0, 0, Color.cyan))){
            return true;
        }else{
            return false;

        }
    }
    public boolean checkBottomBorder(){
        if (this.getBorder().equals(new MatteBorder(0, 0, 5, 0, Color.cyan))){
            return true;
        }else{
            return false;
        }
    }

}
