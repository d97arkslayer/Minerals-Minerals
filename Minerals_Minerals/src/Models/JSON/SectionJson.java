/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.JSON;

/**
 *
 * @author darkd
 */
public class SectionJson
{

    private char tipo;
    private int x;
    private int y;

    public SectionJson()
    {
        this.tipo = '?';
        this.x = 0;
        this.y = 0;
    }

    public SectionJson(char tipo, int x, int y)
    {
        this.tipo = tipo;
        this.x = x;
        this.y = y;
    }

    /**
     * @return the tipo
     */
    public char getTipo()
    {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(char tipo)
    {
        this.tipo = tipo;
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

}
