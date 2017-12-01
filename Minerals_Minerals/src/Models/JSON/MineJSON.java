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
public class MineJSON
{

    private String tipoMineral;
    private int capacidadMineros;
    private double capacidadDeposito;
    private String unidadCapacidad;
    private double tiempoExtraccion;
    private String unidadTiempo;
    private double velocidadDesplazamiento;
    private String unidadVelocidad;
    private int ancho;
    private int alto;
    private MineEntrance entradaMina;
    private LinkedList<SectionJson> seccionesMina;

    public MineJSON(String tipoMineral, int capacidadMineros, double capacidadDeposito, String unidadCapacidad, double tiempoExtraccion, String unidadTiempo, double velocidadDesplazamiento, String unidadVelocidad)
    {
        this.tipoMineral = tipoMineral;
        this.capacidadMineros = capacidadMineros;
        this.capacidadDeposito = capacidadDeposito;
        this.unidadCapacidad = unidadCapacidad;
        this.tiempoExtraccion = tiempoExtraccion;
        this.unidadTiempo = unidadTiempo;
        this.velocidadDesplazamiento = velocidadDesplazamiento;
        this.unidadVelocidad = unidadVelocidad;
        this.seccionesMina = new LinkedList<>();

    }

    public MineJSON()
    {
        this.tipoMineral = "";
        this.capacidadMineros = 0;
        this.capacidadDeposito = 0;
        this.unidadCapacidad = "";
        this.tiempoExtraccion = 0;
        this.unidadTiempo = "";
        this.velocidadDesplazamiento = 0;
        this.unidadVelocidad = "";
        this.ancho = 0;
        this.alto = 0;
        this.entradaMina = new MineEntrance();
        this.seccionesMina = new LinkedList<>();
    }

    /**
     * @return the tipoMineral
     */
    public String getTipoMineral()
    {
        return tipoMineral;
    }

    /**
     * @param tipoMineral the tipoMineral to set
     */
    public void setTipoMineral(String tipoMineral)
    {
        this.tipoMineral = tipoMineral;
    }

    /**
     * @return the capacidadMineros
     */
    public int getCapacidadMineros()
    {
        return capacidadMineros;
    }

    /**
     * @param capacidadMineros the capacidadMineros to set
     */
    public void setCapacidadMineros(int capacidadMineros)
    {
        this.capacidadMineros = capacidadMineros;
    }

    /**
     * @return the capacidadDeposito
     */
    public double getCapacidadDeposito()
    {
        return capacidadDeposito;
    }

    /**
     * @param capacidadDeposito the capacidadDeposito to set
     */
    public void setCapacidadDeposito(double capacidadDeposito)
    {
        this.capacidadDeposito = capacidadDeposito;
    }

    /**
     * @return the unidadCapacidad
     */
    public String getUnidadCapacidad()
    {
        return unidadCapacidad;
    }

    /**
     * @param unidadCapacidad the unidadCapacidad to set
     */
    public void setUnidadCapacidad(String unidadCapacidad)
    {
        this.unidadCapacidad = unidadCapacidad;
    }

    /**
     * @return the tiempoExtraccion
     */
    public double getTiempoExtraccion()
    {
        return tiempoExtraccion;
    }

    /**
     * @param tiempoExtraccion the tiempoExtraccion to set
     */
    public void setTiempoExtraccion(double tiempoExtraccion)
    {
        this.tiempoExtraccion = tiempoExtraccion;
    }

    /**
     * @return the unidadTiempo
     */
    public String getUnidadTiempo()
    {
        return unidadTiempo;
    }

    /**
     * @param unidadTiempo the unidadTiempo to set
     */
    public void setUnidadTiempo(String unidadTiempo)
    {
        this.unidadTiempo = unidadTiempo;
    }

    /**
     * @return the velocidadDesplazamiento
     */
    public double getVelocidadDesplazamiento()
    {
        return velocidadDesplazamiento;
    }

    /**
     * @param velocidadDesplazamiento the velocidadDesplazamiento to set
     */
    public void setVelocidadDesplazamiento(double velocidadDesplazamiento)
    {
        this.velocidadDesplazamiento = velocidadDesplazamiento;
    }

    /**
     * @return the unidadVelocidad
     */
    public String getUnidadVelocidad()
    {
        return unidadVelocidad;
    }

    /**
     * @param unidadVelocidad the unidadVelocidad to set
     */
    public void setUnidadVelocidad(String unidadVelocidad)
    {
        this.unidadVelocidad = unidadVelocidad;
    }

    /**
     * @return the ancho
     */
    public int getAncho()
    {
        return ancho;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(int ancho)
    {
        this.ancho = ancho;
    }

    /**
     * @return the alto
     */
    public int getAlto()
    {
        return alto;
    }

    /**
     * @param alto the alto to set
     */
    public void setAlto(int alto)
    {
        this.alto = alto;
    }

    /**
     * @return the entradaMina
     */
    public MineEntrance getEntradaMina()
    {
        return entradaMina;
    }

    /**
     * @param entradaMina the entradaMina to set
     */
    public void setEntradaMina(MineEntrance entradaMina)
    {
        this.entradaMina = entradaMina;
    }

    /**
     * @return the seccionesMina
     */
    public LinkedList<SectionJson> getSeccionesMina()
    {
        return seccionesMina;
    }

    /**
     * @param seccionesMina the seccionesMina to set
     */
    public void setSeccionesMina(LinkedList<SectionJson> seccionesMina)
    {
        this.seccionesMina = seccionesMina;
    }
}
