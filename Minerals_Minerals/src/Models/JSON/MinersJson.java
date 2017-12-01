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
public class MinersJson
{

    private int totalMineros;
    private int totalMinerosOro;
    private int totalMinerosPlata;
    private int totalMinerosCobre;
    private int totalminerosComodin;

    public MinersJson()
    {
        this.totalMineros = 0;
        this.totalMinerosOro = 0;
        this.totalMinerosPlata = 0;
        this.totalMinerosCobre = 0;
        this.totalminerosComodin = 0;
    }

    public MinersJson(int totalMineros, int totalMinerosOro, int totalMinerosPlata, int totalMinerosCobre, int totalminerosComodin)
    {
        this.totalMineros = totalMineros;
        this.totalMinerosOro = totalMinerosOro;
        this.totalMinerosPlata = totalMinerosPlata;
        this.totalMinerosCobre = totalMinerosCobre;
        this.totalminerosComodin = totalminerosComodin;
    }

    /**
     * @return the totalMineros
     */
    public int getTotalMineros()
    {
        return totalMineros;
    }

    /**
     * @param totalMineros the totalMineros to set
     */
    public void setTotalMineros(int totalMineros)
    {
        this.totalMineros = totalMineros;
    }

    /**
     * @return the totalMinerosOro
     */
    public int getTotalMinerosOro()
    {
        return totalMinerosOro;
    }

    /**
     * @param totalMinerosOro the totalMinerosOro to set
     */
    public void setTotalMinerosOro(int totalMinerosOro)
    {
        this.totalMinerosOro = totalMinerosOro;
    }

    /**
     * @return the totalMinerosPlata
     */
    public int getTotalMinerosPlata()
    {
        return totalMinerosPlata;
    }

    /**
     * @param totalMinerosPlata the totalMinerosPlata to set
     */
    public void setTotalMinerosPlata(int totalMinerosPlata)
    {
        this.totalMinerosPlata = totalMinerosPlata;
    }

    /**
     * @return the totalMinerosCobre
     */
    public int getTotalMinerosCobre()
    {
        return totalMinerosCobre;
    }

    /**
     * @param totalMinerosCobre the totalMinerosCobre to set
     */
    public void setTotalMinerosCobre(int totalMinerosCobre)
    {
        this.totalMinerosCobre = totalMinerosCobre;
    }

    /**
     * @return the totalminerosComodin
     */
    public int getTotalminerosComodin()
    {
        return totalminerosComodin;
    }

    /**
     * @param totalminerosComodin the totalminerosComodin to set
     */
    public void setTotalminerosComodin(int totalminerosComodin)
    {
        this.totalminerosComodin = totalminerosComodin;
    }

}
