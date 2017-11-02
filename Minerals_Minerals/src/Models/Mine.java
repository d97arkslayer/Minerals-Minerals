/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.LinkedList;

/**
 *
 * @author darkd
 */
public class Mine
{

    private int id;
    private String metal;
    private LinkedList<LinkedList<SectionMap>> matrix;

    public Mine()
    {
        this.matrix = new LinkedList<>();
    }

    public Mine(int id, String metal)
    {
        this.id = id;
        this.metal = metal;
        this.matrix = new LinkedList<>();
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the metal
     */
    public String getMetal()
    {
        return metal;
    }

    /**
     * @param metal the metal to set
     */
    public void setMetal(String metal)
    {
        this.metal = metal;
    }

    /**
     * @return the matrix
     */
    public LinkedList<LinkedList<SectionMap>> getMatrix()
    {
        return matrix;
    }

    /**
     * @param matrix the matrix to set
     */
    public void setMatrix(LinkedList<LinkedList<SectionMap>> matrix)
    {
        this.matrix = matrix;
    }

    /**
     * @return the matrix
     */
}
