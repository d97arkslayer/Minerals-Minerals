/*
 
 */
package Models;

import java.util.LinkedList;
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
    private String previousCollision;
    private int xBox;
    private int yBox;
    private int widthBox;
    private int heightBox;
    private ImageIcon imageBox;
    private String nombre;
    private boolean followRoute;
    private LinkedList<String> route;
    private String especiality;
    private double currentCapacity;
    private SectionMap workPlace;
    private String workLocate;
    private Double earnings;
    private boolean status;
    private boolean comodin;

    public Miner()
    {
    }

    public Miner(int x, int y, int direction, String nombre)
    {
        this.x = x;
        this.y = y;
        this.width = 36;
        this.height = 34;
        this.direction = direction;
        this.countAnimation = 0;
        this.state = "mover";
        this.move = new String[2];
        this.work = new String[3];
        this.previousCollision = "S";
        this.xBox = this.x + (this.width / 2);
        this.yBox = this.y - 40;
        this.widthBox = 50;
        this.heightBox = 40;
        this.nombre = nombre;
        this.currentCapacity = 0;
        this.imageBox = new ImageIcon(getClass().getResource("../Images/miner_box.png"));
        this.followRoute = false;
        this.route = new LinkedList<>();
        this.workLocate = "";
        this.earnings = 0.0;
        this.status = true;
        this.comodin=false;
        loadSprite();
    }

    /*
        Se inicializa los vectores para la animacion de acuerdo a la direccion
     */
    public void loadSprite()
    {
        switch (this.direction)
        {
            case 1:
                this.move[0] = "../Images/miner_up1.png";
                this.move[1] = "../Images/miner_up2.png";
                this.work[0] = "../Images/miner_up_working1.png";
                this.work[1] = "../Images/miner_up_working2.png";
                this.work[2] = "../Images/miner_up_working3.png";
                this.image = new ImageIcon(getClass().getResource(this.move[0]));
                break;
            case 2:
                this.move[0] = "../Images/miner_right1.png";
                this.move[1] = "../Images/miner_right2.png";
                this.work[0] = "../Images/miner_right_working1.png";
                this.work[1] = "../Images/miner_right_working2.png";
                this.work[2] = "../Images/miner_right_working3.png";
                this.image = new ImageIcon(getClass().getResource(this.move[0]));
                break;
            case 3:
                this.move[0] = "../Images/miner_down1.png";
                this.move[1] = "../Images/miner_down2.png";
                this.work[0] = "../Images/miner_down_working1.png";
                this.work[1] = "../Images/miner_down_working2.png";
                this.work[2] = "../Images/miner_down_working3.png";
                this.image = new ImageIcon(getClass().getResource(this.move[0]));
                break;
            case 4:
                this.move[0] = "../Images/miner_left1.png";
                this.move[1] = "../Images/miner_left2.png";
                this.work[0] = "../Images/miner_left_working1.png";
                this.work[1] = "../Images/miner_left_working2.png";
                this.work[2] = "../Images/miner_left_working3.png";
                this.image = new ImageIcon(getClass().getResource(this.move[0]));
                break;
        }
    }

    public void changeDirection(int newDirection)
    {
        this.direction = newDirection;
        loadSprite();
    }

    public void resetDirection()
    {
        switch (this.direction)
        {
            case 1:
                this.changeDirection(3);
                break;
            case 2:
                this.changeDirection(4);
            case 3:
                this.changeDirection(1);
            case 4:
                this.changeDirection(2);
        }
    }

    /*
        Animacion 
     */
    public void sprite()
    {
        if (this.state.equalsIgnoreCase("mover"))
        {
            this.image = new ImageIcon(getClass().getResource(this.move[this.countAnimation]));
            if (this.countAnimation == 1)
                this.countAnimation = 0;
            else
                this.countAnimation++;
        }
        if (this.state.equalsIgnoreCase("trabajo"))
        {
            this.image = new ImageIcon(getClass().getResource(this.work[this.countAnimation]));
            if (this.countAnimation == 2)
                this.countAnimation = 0;
            else
                this.countAnimation++;
        }
    }

    @Override
    public void run()
    {
        while (this.movement)
        {
            if (this.state.equalsIgnoreCase("mover"))
            {
                switch (this.direction)
                {
                    case 1:
                        this.y = this.y - 2;
                        this.setyBox(this.getyBox() - 2);
                        break;
                    case 2:
                        this.x = this.x + 2;
                        this.setxBox(this.getxBox() + 2);
                        break;
                    case 3:
                        this.y = this.y + 2;
                        this.setyBox(this.getyBox() + 2);
                        break;
                    case 4:
                        this.x = this.x - 2;
                        this.setxBox(this.getxBox() - 2);
                        break;

                }
                sprite();
            }
            if (this.state.equalsIgnoreCase("trabajo"))
                sprite();
            try
            {
                Thread.sleep(150);
            }
            catch (InterruptedException e)
            {
            }
        }
    }

    /**
     * @return the x
     */
    public int getX()
    {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY()
    {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * @return the width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * @return the direction
     */
    public int getDirection()
    {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    /**
     * @return the countAnimation
     */
    public int getCountAnimation()
    {
        return countAnimation;
    }

    /**
     * @param countAnimation the countAnimation to set
     */
    public void setCountAnimation(int countAnimation)
    {
        this.countAnimation = countAnimation;
    }

    /**
     * @return the state
     */
    public String getState()
    {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * @return the move
     */
    public String[] getMove()
    {
        return move;
    }

    /**
     * @param move the move to set
     */
    public void setMove(String[] move)
    {
        this.move = move;
    }

    /**
     * @return the work
     */
    public String[] getWork()
    {
        return work;
    }

    /**
     * @param work the work to set
     */
    public void setWork(String[] work)
    {
        this.work = work;
    }

    /**
     * @return the image
     */
    public ImageIcon getImage()
    {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(ImageIcon image)
    {
        this.image = image;
    }

    /**
     * @return the movement
     */
    public boolean isMovement()
    {
        return movement;
    }

    /**
     * @param movement the movement to set
     */
    public void setMovement(boolean movement)
    {
        this.movement = movement;
    }

    /**
     * @return the previousCollision
     */
    public String getPreviousCollision()
    {
        return previousCollision;
    }

    /**
     * @param previousCollision the previousCollision to set
     */
    public void setPreviousCollision(String previousCollision)
    {
        this.previousCollision = previousCollision;
    }

    /**
     * @return the xBox
     */
    public int getxBox()
    {
        return xBox;
    }

    /**
     * @param xBox the xBox to set
     */
    public void setxBox(int xBox)
    {
        this.xBox = xBox;
    }

    /**
     * @return the yBox
     */
    public int getyBox()
    {
        return yBox;
    }

    /**
     * @param yBox the yBox to set
     */
    public void setyBox(int yBox)
    {
        this.yBox = yBox;
    }

    /**
     * @return the widthBox
     */
    public int getWidthBox()
    {
        return widthBox;
    }

    /**
     * @param widthBox the widthBox to set
     */
    public void setWidthBox(int widthBox)
    {
        this.widthBox = widthBox;
    }

    /**
     * @return the heightBox
     */
    public int getHeightBox()
    {
        return heightBox;
    }

    /**
     * @param heightBox the heightBox to set
     */
    public void setHeightBox(int heightBox)
    {
        this.heightBox = heightBox;
    }

    /**
     * @return the imageBox
     */
    public ImageIcon getImageBox()
    {
        return imageBox;
    }

    /**
     * @param imageBox the imageBox to set
     */
    public void setImageBox(ImageIcon imageBox)
    {
        this.imageBox = imageBox;
    }

    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @return the followRoute
     */
    public boolean isFollowRoute()
    {
        return followRoute;
    }

    /**
     * @param followRoute the followRoute to set
     */
    public void setFollowRoute(boolean followRoute)
    {
        this.followRoute = followRoute;
    }

    /**
     * @return the route
     */
    public LinkedList<String> getRoute()
    {
        return route;
    }

    /**
     * @param route the route to set
     */
    public void setRoute(LinkedList<String> route)
    {
        this.route = route;
    }

    /**
     * @return the especiality
     */
    public String getEspeciality()
    {
        return especiality;
    }

    /**
     * @param especiality the especiality to set
     */
    public void setEspeciality(String especiality)
    {
        this.especiality = especiality;
    }

    /**
     * @return the currentCapacity
     */
    public double getCurrentCapacity()
    {
        return currentCapacity;
    }

    /**
     * @param currentCapacity the currentCapacity to set
     */
    public void setCurrentCapacity(double currentCapacity)
    {
        this.currentCapacity = currentCapacity;
    }

    /**
     * @return the workPlace
     */
    public SectionMap getWorkPlace()
    {
        return workPlace;
    }

    /**
     * @param workPlace the workPlace to set
     */
    public void setWorkPlace(SectionMap workPlace)
    {
        this.workPlace = workPlace;
    }

    /**
     * @return the workLocate
     */
    public String getWorkLocate()
    {
        return workLocate;
    }

    /**
     * @param workLocate the workLocate to set
     */
    public void setWorkLocate(String workLocate)
    {
        this.workLocate = workLocate;
    }

    /**
     * @return the earnings
     */
    public Double getEarnings()
    {
        return earnings;
    }

    /**
     * @param earnings the earnings to set
     */
    public void setEarnings(Double earnings)
    {
        this.earnings = earnings;
    }

    /**
     * @return the status
     */
    public boolean isStatus()
    {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status)
    {
        this.status = status;
    }

    /**
     * @return the comodin
     */
    public boolean isComodin()
    {
        return comodin;
    }

    /**
     * @param comodin the comodin to set
     */
    public void setComodin(boolean comodin)
    {
        this.comodin = comodin;
    }

}
