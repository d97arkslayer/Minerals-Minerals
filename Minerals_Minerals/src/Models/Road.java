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
    private ImageIcon[] images;

    public Road()
    {
    }

    public Road(int x, int y, boolean entry, int locationEntry)
    {

        this.myRoadImages();
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
        this.entry = entry;
        this.locationEntry = locationEntry;
        this.image = new ImageIcon(getClass().getResource("../Images/road.png"));
        if (this.entry){
            locateEntry();
        }
            
//        else
//            this.image = new ImageIcon(getClass().getResource("../Images/road.png"));
    }
    
    public void locateEntry()
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
    }
    
    public void myRoadImages()
    {
        this.setImages(new ImageIcon[11]);
        this.getImages()[0] = new ImageIcon(getClass().getResource("../Images/Roads/verticalRail.png"));
        this.getImages()[1] = new ImageIcon(getClass().getResource("../Images/Roads/horizontalRail.png"));
        this.getImages()[2] = new ImageIcon(getClass().getResource("../Images/Roads/giro1Rail.png"));
        this.getImages()[3] = new ImageIcon(getClass().getResource("../Images/Roads/giro2Rail.png"));
        this.getImages()[4] = new ImageIcon(getClass().getResource("../Images/Roads/giro3Rail.png"));
        this.getImages()[5] = new ImageIcon(getClass().getResource("../Images/Roads/giro4Rail.png"));
        this.getImages()[6] = new ImageIcon(getClass().getResource("../Images/Roads/intersectionCrossRail.png"));
        this.getImages()[7] = new ImageIcon(getClass().getResource("../Images/Roads/intersectionDownRail.png"));
        this.getImages()[8] = new ImageIcon(getClass().getResource("../Images/Roads/intersectionLeftRail.png"));
        this.getImages()[9] = new ImageIcon(getClass().getResource("../Images/Roads/intersectionRightRail.png"));
        this.getImages()[10] = new ImageIcon(getClass().getResource("../Images/Roads/intersectionTopRail.png"));
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
    public boolean isEntry()
    {
        return entry;
    }

    /**
     * @param entry the entry to set
     */
    public void setEntry(boolean entry)
    {
        this.entry = entry;
    }

    /**
     * @return the locationEntry
     */
    public int getLocationEntry()
    {
        return locationEntry;
    }

    /**
     * @param locationEntry the locationEntry to set
     */
    public void setLocationEntry(int locationEntry)
    {
        this.locationEntry = locationEntry;
        locateEntry();
    }

    /**
     * @return the images
     */
    public ImageIcon[] getImages()
    {
        return images;
    }

    /**
     * @param images the images to set
     */
    public void setImages(ImageIcon[] images)
    {
        this.images = images;
    }

}
