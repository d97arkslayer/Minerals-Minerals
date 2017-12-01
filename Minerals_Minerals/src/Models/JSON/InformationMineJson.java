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
public class InformationMineJson
{

    private double capacidadCargaOro;
    private double capacidadCargaPlata;
    private double capacidadCargaCobre;
    private String unidadCapacidadCarga;
    private double gananciaOro;
    private double gananciaPlata;
    private double gananciaCobre;

    public InformationMineJson(double capacidadCargaOro, double capacidadCargaPlata, double capacidadCargaCobre, String unidadCapacidadCarga, double gananciaOro, double gananciaPlata, double gananciaCobre)
    {
        this.capacidadCargaOro = capacidadCargaOro;
        this.capacidadCargaPlata = capacidadCargaPlata;
        this.capacidadCargaCobre = capacidadCargaCobre;
        this.unidadCapacidadCarga = unidadCapacidadCarga;
        this.gananciaOro = gananciaOro;
        this.gananciaPlata = gananciaPlata;
        this.gananciaCobre = gananciaCobre;
    }

    public InformationMineJson()
    {
        this.capacidadCargaOro = 0;
        this.capacidadCargaPlata = 0;
        this.capacidadCargaCobre = 0;
        this.unidadCapacidadCarga = "";
        this.gananciaOro = 0;
        this.gananciaPlata = 0;
        this.gananciaCobre = 0;
    }

    /**
     * @return the capacidadCargaOro
     */
    public double getCapacidadCargaOro()
    {
        return capacidadCargaOro;
    }

    /**
     * @param capacidadCargaOro the capacidadCargaOro to set
     */
    public void setCapacidadCargaOro(double capacidadCargaOro)
    {
        this.capacidadCargaOro = capacidadCargaOro;
    }

    /**
     * @return the capacidadCargaPlata
     */
    public double getCapacidadCargaPlata()
    {
        return capacidadCargaPlata;
    }

    /**
     * @param capacidadCargaPlata the capacidadCargaPlata to set
     */
    public void setCapacidadCargaPlata(double capacidadCargaPlata)
    {
        this.capacidadCargaPlata = capacidadCargaPlata;
    }

    /**
     * @return the capacidadCargaCobre
     */
    public double getCapacidadCargaCobre()
    {
        return capacidadCargaCobre;
    }

    /**
     * @param capacidadCargaCobre the capacidadCargaCobre to set
     */
    public void setCapacidadCargaCobre(double capacidadCargaCobre)
    {
        this.capacidadCargaCobre = capacidadCargaCobre;
    }

    /**
     * @return the unidadCapacidadCarga
     */
    public String getUnidadCapacidadCarga()
    {
        return unidadCapacidadCarga;
    }

    /**
     * @param unidadCapacidadCarga the unidadCapacidadCarga to set
     */
    public void setUnidadCapacidadCarga(String unidadCapacidadCarga)
    {
        this.unidadCapacidadCarga = unidadCapacidadCarga;
    }

    /**
     * @return the gananciaOro
     */
    public double getGananciaOro()
    {
        return gananciaOro;
    }

    /**
     * @param gananciaOro the gananciaOro to set
     */
    public void setGananciaOro(double gananciaOro)
    {
        this.gananciaOro = gananciaOro;
    }

    /**
     * @return the gananciaPlata
     */
    public double getGananciaPlata()
    {
        return gananciaPlata;
    }

    /**
     * @param gananciaPlata the gananciaPlata to set
     */
    public void setGananciaPlata(double gananciaPlata)
    {
        this.gananciaPlata = gananciaPlata;
    }

    /**
     * @return the gananciaCobre
     */
    public double getGananciaCobre()
    {
        return gananciaCobre;
    }

    /**
     * @param gananciaCobre the gananciaCobre to set
     */
    public void setGananciaCobre(double gananciaCobre)
    {
        this.gananciaCobre = gananciaCobre;
    }

}
