/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.swing.ImageIcon;

/**
 *
 * @author Esteban Herrera y Jaime Bernal
 * Clase la cual es el deposito de mineral en la mina
 */
public class Deposit
{

    private int x;
    private int y;
    private int width;
    private int height;
    private ImageIcon image;
    private double amount;

    public Deposit()
    {
    }

    /**
     * Constructor
     * @param x posicion en el eje x
     * @param y posicion en el eje y
     * @param i tipo de metal de la mina
     */
    public Deposit(int x, int y, String i)
    {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
        this.amount = 0;
        switch(i)
        {
            case "Oro":
                this.image = new ImageIcon(getClass().getResource("../Images/gold.gif"));
                break;
            case "Plata":
                this.image = new ImageIcon(getClass().getResource("../Images/silver.gif"));
                break;
            case "Cobre":
                this.image = new ImageIcon(getClass().getResource("../Images/cooper.jpg"));
                break;
                    
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
     * @return the heigth
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * @return the amount
     */
    public double getAmount()
    {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount)
    {
        this.amount = amount;
    }

}
