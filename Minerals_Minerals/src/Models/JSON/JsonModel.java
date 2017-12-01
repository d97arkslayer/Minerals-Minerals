/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.JSON;

import java.util.LinkedList;

/**
 *
 * @author darkd
 */
public class JsonModel
{

    private MinersJson infoMineros;
    private InformationMineJson infoMinas;
    private LinkedList<MineJSON> minas;

    public JsonModel()
    {
        this.infoMineros = new MinersJson();
        this.infoMinas = new InformationMineJson();
        this.minas = new LinkedList<>();
    }

    /**
     * @return the infoMineros
     */
    public MinersJson getInfoMineros()
    {
        return infoMineros;
    }

    /**
     * @param infoMineros the infoMineros to set
     */
    public void setInfoMineros(MinersJson infoMineros)
    {
        this.infoMineros = infoMineros;
    }

    /**
     * @return the infoMinas
     */
    public InformationMineJson getInfoMinas()
    {
        return infoMinas;
    }

    /**
     * @param infoMinas the infoMinas to set
     */
    public void setInfoMinas(InformationMineJson infoMinas)
    {
        this.infoMinas = infoMinas;
    }

    /**
     * @return the minas
     */
    public LinkedList<MineJSON> getMinas()
    {
        return minas;
    }

    /**
     * @param minas the minas to set
     */
    public void setMinas(LinkedList<MineJSON> minas)
    {
        this.minas = minas;
    }
}
