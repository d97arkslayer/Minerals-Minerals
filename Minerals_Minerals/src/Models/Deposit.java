/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.swing.ImageIcon;

/**
 *
 * @author Chinche
 */
public class Deposit {
    private int x;
    private int y;
    private int width;
    private int heigth;
    private ImageIcon image;

    public Deposit() {
    }

    public Deposit(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.heigth = 50;
        this.image = new ImageIcon(getClass().getResource("../Images/wall.png"));
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
     * @return the heigth
     */
    public int getHeigth() {
        return heigth;
    }

    /**
     * @param heigth the heigth to set
     */
    public void setHeigth(int heigth) {
        this.heigth = heigth;
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
    
    
}
