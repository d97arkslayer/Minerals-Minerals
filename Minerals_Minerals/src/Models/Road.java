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
public class Road
{

    private int x;
    private int y;
    private int width;
    private int height;
    private ImageIcon image;
    private boolean entry;
    private int locationEntry;

    public Road()
    {
    }

    public Road(int x, int y,boolean entry,int locationEntry)
    {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
        this.entry=entry;
        this.locationEntry=locationEntry;
        if(this.entry)
        {
            switch (this.locationEntry) 
            {
                case 1:
                    this.image = new ImageIcon(getClass().getResource("../Images/left_entry.png"));
                    break;
                case 2:
                    this.image = new ImageIcon(getClass().getResource("../Images/top_entry.png"));
                    break;
                case 3:
                    this.image = new ImageIcon(getClass().getResource("../Images/right_entry.png"));
                    break;
                case 4:
                    this.image = new ImageIcon(getClass().getResource("../Images/down_entry.png"));
                    break;    
            }
        }else
        {
            this.image = new ImageIcon(getClass().getResource("../Images/road.png"));
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
     * @return the entry
     */
    public boolean isEntry() {
        return entry;
    }

    /**
     * @param entry the entry to set
     */
    public void setEntry(boolean entry) {
        this.entry = entry;
    }

    /**
     * @return the locationEntry
     */
    public int getLocationEntry() {
        return locationEntry;
    }

    /**
     * @param locationEntry the locationEntry to set
     */
    public void setLocationEntry(int locationEntry) {
        this.locationEntry = locationEntry;
    }

}
