/*
 
 */
package Models;

import javax.swing.ImageIcon;

/**
 *
 * @author darkd
 */
public class Miner implements Runnable
{

    private int x;
    private int y;
    private int width;
    private int height;
    private int direction;
    private int countAnimation;
    private String state;
    private String[] move;
    private String[] work;
    private ImageIcon image;
    private boolean movement;
    
    public Miner() {
    }

    public Miner(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.width = 36;
        this.height = 34;
        this.direction = direction;
        this.countAnimation = 0;
        this.state = "mover";
        this.move = new String[2];
        this.work = new String[3];
        LoadSprite();
    }
    /*
        Se inicializa los vectores para la animacion de acuerdo a la direccion
    */
    public void LoadSprite(){
        switch(this.direction){
            case 1:
                this.move[0]="../imagenes/miner_up1.png";
                this.move[1]="../imagenes/miner_up2.png";
                this.work[0]="../imagenes/miner_up_working1.png";
                this.work[1]="../imagenes/miner_up_working2.png";
                this.work[2]="../imagenes/miner_up_working3.png";
                this.image=new ImageIcon(getClass().getResource(this.move[0]));
                break;
            case 2:
                this.move[0]="../imagenes/miner_right1.png";
                this.move[1]="../imagenes/miner_right2.png";
                this.work[0]="../imagenes/miner_right_working1.png";
                this.work[1]="../imagenes/miner_right_working2.png";
                this.work[2]="../imagenes/miner_right_working3.png";
                this.image=new ImageIcon(getClass().getResource(this.move[0]));
                break;
            case 3:
                this.move[0]="../imagenes/miner_down1.png";
                this.move[1]="../imagenes/miner_down2.png";
                this.work[0]="../imagenes/miner_down_working1.png";
                this.work[1]="../imagenes/miner_down_working2.png";
                this.work[2]="../imagenes/miner_down_working3.png";
                this.image=new ImageIcon(getClass().getResource(this.move[0]));
                break;
            case 4:
                this.move[0]="../imagenes/miner_left1.png";
                this.move[1]="../imagenes/miner_left2.png";
                this.work[0]="../imagenes/miner_left_working1.png";
                this.work[1]="../imagenes/miner_left_working2.png";
                this.work[2]="../imagenes/miner_left_working3.png";
                this.image=new ImageIcon(getClass().getResource(this.move[0]));
                break;
        }
    }
    
    /*
        Animacion 
    */
    public void Sprite(){
        if(this.state.equalsIgnoreCase("mover")){
            this.image=new ImageIcon(getClass().getResource(this.move[this.countAnimation]));
            if(this.countAnimation==1){
                this.countAnimation=0;
            }else{
                this.countAnimation++;
            }
        }
        if(this.state.equalsIgnoreCase("trabajo")){
            this.image=new ImageIcon(getClass().getResource(this.work[this.countAnimation]));
            if(this.countAnimation==2){
                this.countAnimation=0;
            }else{
                this.countAnimation++;
            }
        }
    }

    @Override
    public void run() {
        while(this.movement){
           if(this.state.equalsIgnoreCase("mover")){
                switch (this.direction) { 
                    case 1:
                        this.y=this.y-2;
                        break;
                    case 2:
                        this.x=this.x+2;
                        break;
                    case 3:
                        this.y=this.y+2;
                        break;
                    case 4:
                        this.x=this.x-2;
                        break;
                    
                }
                Sprite();
           }
           if(this.state.equalsIgnoreCase("trabajo")){
                Sprite();
           }
            try {
                Thread.sleep(150);
            } catch (Exception e) {
            }
        }
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * @return the countAnimation
     */
    public int getCountAnimation() {
        return countAnimation;
    }

    /**
     * @param countAnimation the countAnimation to set
     */
    public void setCountAnimation(int countAnimation) {
        this.countAnimation = countAnimation;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the move
     */
    public String[] getMove() {
        return move;
    }

    /**
     * @param move the move to set
     */
    public void setMove(String[] move) {
        this.move = move;
    }

    /**
     * @return the work
     */
    public String[] getWork() {
        return work;
    }

    /**
     * @param work the work to set
     */
    public void setWork(String[] work) {
        this.work = work;
    }

    /**
     * @return the image
     */
    public ImageIcon getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    /**
     * @return the movement
     */
    public boolean isMovement() {
        return movement;
    }

    /**
     * @param movement the movement to set
     */
    public void setMovement(boolean movement) {
        this.movement = movement;
    }
    
}
