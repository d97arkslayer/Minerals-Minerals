/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Esteban Herrera y Jaime Bernal
 * Administrador de leer el archivo de donde se obtienen los nombre de los mineros
 */
public class AdministrarNombresMineros {
    
    FileReader fill;
    BufferedReader B;
    String linea;
    LinkedList<String> listWords;

    /**
     * Apertura dle archivo
     */
    
    public AdministrarNombresMineros() {
        try {
            this.listWords=new LinkedList<>();
            try {
                this.fill=new FileReader(new File("E:\\Backup\\Datos\\Universidad de Caldas\\Ingenieria en sistemas y computacion\\Octavo semestre\\Analisis y diseño de algoritmos\\Proyecto\\Codigo\\Minerals-Minerals\\Minerals-Minerals\\Minerals_Minerals\\src\\ArchivoNombres\\Nombres.txt"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AdministrarNombresMineros.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.B=new BufferedReader(this.fill);
            this.linea="";
            while((this.linea=B.readLine())!=null)
            {
                this.listWords.add(this.linea);
            }
        } catch (IOException ex) {
            Logger.getLogger(AdministrarNombresMineros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Obtener nombre del minero del archivo
     * @return El nombre del minero que lo solicita
     */
    public String getNombreMinero()
    {
        int position = (int) (Math.random() * (this.listWords.size())) + 0;
        return this.listWords.get(position);
    }
    
}
