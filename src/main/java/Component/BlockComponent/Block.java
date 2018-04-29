package Component.BlockComponent;

import Component.BlockException.BlockException;
import Component.BlockObserver.BlockObserver;
import Component.BlockObserver.BlockPublisher;
import Util.FileUtil;
import Util.UidGenerator;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
    private String uid;

    private BlockObserver blockObserver;
    protected List<Block> previousBlocks;
    protected List<Block> nextBlocks;
    private LineBorder basicBorder = new LineBorder(Color.black);
    private MatteBorder topBorder = new MatteBorder(5, 0, 0, 0, Color.cyan);
    private MatteBorder bottomBorder = new MatteBorder(0, 0, 5, 0, Color.cyan);
    abstract String getBlockAttrStr();

    // 인자로 들어온 블록이 현재 블록의 다음 블록으로 연결 될 수 있는지 확인하는 메소드
    abstract public boolean isNextBlockConnectable(Block block);
    // 인자로 들어온 블록이 현재 블록의 이전 블록으로 연결 될 수 있는지 확인하는 메소드
    abstract public boolean isPreviousBlockConnectable(Block block);
    // 다음 블록과 연결되어 있는지 여부 연결되어있으면 true 리턴
    abstract public boolean isNextBlockConnected();
    // 이전 블록과 연결되어 있는지 여부
    abstract public boolean isPreviousBlockConnected();
    JLabel nameLabel;
    String blockName;
    JPanel flowPanel;
    JButton reductButton;

    public Block(){
        nextBlocks = new ArrayList<>();
        previousBlocks = new ArrayList<>();
        addMouseListener(this);
        addMouseMotionListener(this);
        uid = UidGenerator.generateUid();
    }

    public Block(String blockName){
        this();
        this.blockName=blockName;
        nameLabel=new JLabel(blockName);
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        reductButton=new JButton(new ImageIcon(FileUtil.getResourcePath("icon/minus.png")));
        reductButton.addActionListener(new ReductionActionListener(this));
        flowPanel=new JPanel(new FlowLayout());
        reductButton.setPreferredSize(new Dimension(16,16));
        flowPanel.add(reductButton);
        flowPanel.add(nameLabel);
        flowPanel.setBackground(new Color(150, 0, 205));
        this.setBorder(basicBorder);
        setVisible(true);
    }

    //TODO boolean을 return 하거나 Exception 으로 Handle할수 있게
    // 인자로 넘어온 블록을 다음 블록으로 등록하는 함수
    public void registerNextBlock(Block block) throws BlockException{
        if(isNextBlockConnectable(block)){
            if (block.isPreviousBlockConnectable(this)){
                this.nextBlocks.add(block);
                // 다음 블록으로 들어오는 블록의 location을 설정.
                block.setLocation(this.getX(), this.getY() + this.getHeight());
            }else{
                throw new BlockException(block.getClass().getSimpleName() + "is not connectable Previous block for" + this.getClass().getSimpleName());
            }
        }else{
            throw new BlockException(block.getClass().getSimpleName() + " is not connectable Next block for" + this.getClass().getSimpleName());
        }
    }
    // 인자로 넘어온 블록을 이전 블록으로 등록하는 함수
    public void registerPreviousBlock(Block block) throws BlockException {
        if (isPreviousBlockConnectable(block)) {
            if (block.isNextBlockConnectable(this)) {
                this.previousBlocks.add(block);
            } else {
                throw new BlockException(block.getClass().getSimpleName() + "is not connectable Next block for" + this.getClass().getSimpleName());
            }
        } else {
            throw new BlockException(block.getClass().getSimpleName() + "is not connectable Previous block for" + this.getClass().getSimpleName());
        }
    }

    /**
     *  아래쪽 보더를 셋해준다
     */
    public void blinkBottom(){
        System.out.println("바텀이 반짝!");
        this.setBorder(bottomBorder);
    }

    /**
     * 위쪽 보더를 셋해준다.
     */
    public void blinkTop(){
        System.out.println("탑이 반짝!");
        this.setBorder(topBorder);
    }


    //TODO 여기도 생성자 기본 테두리가 나오면 null 부분에 기본 테두리가 들어감
    public void revertBlock(){
        this.setBorder(basicBorder);
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

            // 해당하는 블록이 첫번째 블록일때 뒤에 블록은 다 나를 따라와야한다.
            if(isFirstBlock()){
                int i = 1;
                Block block = this;
                while(block.isNextBlockConnected()){
                    for(int k = 0; k < block.nextBlocks.size(); k++){
                        block.nextBlocks.get(k).setLocation(x, y + i * this.getHeight());
                        System.out.println("로케이션 되는중..");
                    }
                    //연결되어있으면
                    if(block.isNextBlockConnected()){
                        block = block.nextBlocks.get(0);
                    }else{
                        break;
                    }
                    i++;

                }

            }
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
        if (this.getBorder() == basicBorder){
            return false;
        }else{
            return true;
        }
    }

    /**
     * TopBorder가 event에 의해 Trigger되었는지 여부
     * */
    public boolean checkTopBorder(){
        // 자기 자신의 border가 topborder임을 체크
        if (this.getBorder() == topBorder){
            return true;
        }else{
            return false;

        }
    }
    public boolean checkBottomBorder(){
        // 자기 자신의 border가 bottomBorder임을 체크
        if (this.getBorder() == bottomBorder){
            return true;
        }else{
            return false;
        }
    }
    public boolean isFirstBlock(){
        return (this.previousBlocks.size() == 0);
    }


    public void disconnectBlock(){
        this.previousBlocks.get(0).disconnectNextBlock();
        this.disconnectPreviousBlock();

    }
    private void disconnectNextBlock(){
        this.nextBlocks.clear();
    }
    private void disconnectPreviousBlock(){
        this.previousBlocks.clear();
    }
    public String getUid(){
        return uid;
    }
    public void setUid(String uid){
        this.uid = uid;
    }

    public List<Block> getPreviousBlocks(){
        return previousBlocks;
    }

    public List<Block> getNextBlocks(){
        return nextBlocks;
    }
}
