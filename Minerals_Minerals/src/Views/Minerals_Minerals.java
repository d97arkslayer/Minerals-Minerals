/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.*;
import Models.JSON.InformationMineJson;
import Models.JSON.JsonLoadProcess;
import Models.JSON.JsonModel;
import Models.JSON.JsonSaveProcess;
import Models.JSON.MinersJson;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author darkd
 */
public class Minerals_Minerals extends javax.swing.JFrame implements Runnable
{

    int countMines = 1;
    AdministrarNombresMineros nombreMineros = new AdministrarNombresMineros();
    LinkedList<Integer> totalGain = new LinkedList<>();
    LinkedList<JPanel> panels = new LinkedList<>();
    InformationMineJson generalInformation = new InformationMineJson();
    MinersJson minersInfo = new MinersJson();
    LinkedList<Panel> pruebapanels = new LinkedList<>();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    LinkedList<Thread> minersThreads = new LinkedList<>();
    LinkedList<Thread> mineThreads = new LinkedList<>();
    LinkedList<Thread> panelsThreads = new LinkedList<>();
    Thread hilo;

    /**
     * Creates new form Minerals_Minerals
     */
    public Minerals_Minerals()
    {

        initComponents();
        this.setDefaultCloseOperation(Minerals_Minerals.EXIT_ON_CLOSE);
        this.setSize((int) (screenSize.getWidth()) - 40, (int) (screenSize.getHeight()) - 40);
        this.setResizable(false);
        this.setTitle("Minerals & Minerals");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("../Images/miner_logo.png")));
        this.setLocationRelativeTo(null);
        //General b = new General(this, true);
        Start b = new Start(this, true, this);
    }

    public void initialInformation()
    {
        this.totalMineros.setText(this.minersInfo.getTotalMineros() + "");
        this.minerosOro.setText(this.minersInfo.getTotalMinerosOro() + "");
        this.minerosPlata.setText(this.minersInfo.getTotalMinerosPlata() + "");
        this.minerosCobre.setText(this.minersInfo.getTotalMinerosCobre() + "");
        this.minerosComodin.setText(this.minersInfo.getTotalminerosComodin() + "");
        this.capacidadOro.setText(this.generalInformation.getCapacidadCargaOro() + " " + this.generalInformation.getUnidadCapacidadCarga());
        this.capacidadPlata.setText(this.generalInformation.getCapacidadCargaPlata() + " " + this.generalInformation.getUnidadCapacidadCarga());
        this.capacidadCobre.setText(this.generalInformation.getCapacidadCargaCobre() + " " + this.generalInformation.getUnidadCapacidadCarga());
        this.gananciaOro.setText(this.generalInformation.getGananciaOro() + " $");
        this.gananciaPlata.setText(this.generalInformation.getGananciaPlata() + " $");
        this.gananciaCobre.setText(this.generalInformation.getGananciaCobre() + " $");
    }

    public void locateMiners(LinkedList<Mine> listMines, int miners)
    {
        int[] minersByMine = new int[listMines.size()];
        LinkedList<Integer> minesToTheLimit=new LinkedList<>();
        int amountMiners = miners;
        while (amountMiners > 0)
        {
            int[][] matriz = new int[amountMiners][listMines.size()];
            int[][] matriz1 = new int[amountMiners][listMines.size()];
            for (int i = 0; i < matriz.length; i++)
            {
                for (int j = 0; j < matriz[i].length; j++)
                {
                    if(listMines.get(j).getAsignedminers()==0)
                    {
                        if (minersByMine[j] == 0)
                            if (i == 0)
                                if ((i + 1) < listMines.get(j).getAmountOfDeposits())
                                {
                                    matriz[i][j] = (i + 1) * 800;
                                    matriz1[i][j] = 1;
                                }
                                else if ((i + 1) == listMines.get(j).getAmountOfDeposits())
                                {
                                    matriz[i][j] = (i + 1) * 950;
                                    matriz1[i][j] = 1;
                                }
                                else
                                {
                                    int aux;
                                    if ((listMines.get(j).getAmountOfDeposits()
                                            - ((i + 1 + minersByMine[j]))) < 0)
                                        aux = (listMines.get(j).getAmountOfDeposits()
                                                - ((i + 1 + minersByMine[j]))) * (-1);
                                    else
                                        aux = (listMines.get(j).getAmountOfDeposits()
                                                - ((i + 1 + minersByMine[j])));
                                    matriz[i][j] = (listMines.get(j).getAmountOfDeposits())
                                            * 250 - ((aux) * 120);
                                    matriz1[i][j] = 1;
                                }
                            else
                            {
                                int total;
                                if ((i + 1) < listMines.get(j).getAmountOfDeposits())
                                    total = (i + 1) * 800;
                                else if ((i + 1) == listMines.get(j).getAmountOfDeposits())
                                    total = (i + 1) * 950;
                                else
                                {
                                    int aux;
                                    if ((listMines.get(j).getAmountOfDeposits()
                                            - ((i + 1 + minersByMine[j]))) < 0)
                                        aux = (listMines.get(j).getAmountOfDeposits()
                                                - ((i + 1 + minersByMine[j]))) * (-1);
                                    else
                                        aux = (listMines.get(j).getAmountOfDeposits()
                                                - ((i + 1 + minersByMine[j])));
                                    total = (listMines.get(j).getAmountOfDeposits()) * 250 - ((aux) * 120);
                                }
                                if (total > matriz[i - 1][j])
                                {
                                    matriz[i][j] = total;
                                    matriz1[i][j] = 1;
                                }
                                else
                                    matriz[i][j] = matriz[i - 1][j];
                            }
                        else if (i == 0)
                            if (((i + 1 + minersByMine[j])) < listMines.get(j).getAmountOfDeposits())
                            {
                                matriz[i][j] = ((i + 1 + minersByMine[j])) * 800;
                                matriz1[i][j] = 1;
                            }
                            else if (((i + 1 + minersByMine[j])) == listMines.get(j).getAmountOfDeposits())
                            {
                                matriz[i][j] = ((i + 1 + minersByMine[j])) * 950;
                                matriz1[i][j] = 1;
                            }
                            else
                            {
                                int aux;
                                if ((listMines.get(j).getAmountOfDeposits()
                                        - ((i + 1 + minersByMine[j]))) < 0)
                                    aux = (listMines.get(j).getAmountOfDeposits()
                                            - ((i + 1 + minersByMine[j]))) * (-1);
                                else
                                    aux = (listMines.get(j).getAmountOfDeposits()
                                            - ((i + 1 + minersByMine[j])));
                                matriz[i][j] = (listMines.get(j).getAmountOfDeposits())
                                        * 250 - (aux * 120);
                                matriz1[i][j] = 1;
                            }
                        else
                        {
                            int total;
                            if (((i + 1 + minersByMine[j])) < listMines.get(j).getAmountOfDeposits())
                                total = ((i + 1 + minersByMine[j])) * 800;
                            else if (((i + 1 + minersByMine[j])) == listMines.get(j).getAmountOfDeposits())
                                total = ((i + 1 + minersByMine[j])) * 950;
                            else
                            {
                                int aux;
                                if ((listMines.get(j).getAmountOfDeposits()
                                        - ((i + 1 + minersByMine[j]))) < 0)
                                    aux = (listMines.get(j).getAmountOfDeposits()
                                            - ((i + 1 + minersByMine[j]))) * (-1);
                                else
                                    aux = (listMines.get(j).getAmountOfDeposits()
                                            - ((i + 1 + minersByMine[j])));
                                total = (listMines.get(j).getAmountOfDeposits()) * 250 - ((aux) * 120);
                            }
                            if (total > matriz[i - 1][j])
                            {
                                matriz[i][j] = total;
                                matriz1[i][j] = 1;
                            }
                            else
                                matriz[i][j] = matriz[i - 1][j];
                        }
                    }else
                    {
                        if(i==0)
                        {
                                if (((i + 1 + listMines.get(j).getAsignedminers()+minersByMine[j])) < listMines.get(j).getAmountOfDeposits())
                                {
                                    matriz[i][j] = ((i + 1 +minersByMine[j]+ listMines.get(j).getAsignedminers())) * 800;
                                    matriz1[i][j] = 1;
                                }
                                else if (((i + 1 + minersByMine[j]+listMines.get(j).getAsignedminers())) == listMines.get(j).getAmountOfDeposits())
                                {
                                    matriz[i][j] = ((i + 1 + minersByMine[j]+listMines.get(j).getAsignedminers())) * 950;
                                    matriz1[i][j] = 1;
                                }
                                else
                                {
                                    int aux;
                                    if ((listMines.get(j).getAmountOfDeposits()
                                    - ((i + 1 +minersByMine[j]+ listMines.get(j).getAsignedminers()))) < 0)
                                        aux = (listMines.get(j).getAmountOfDeposits()
                                                - ((i + 1+minersByMine[j] + listMines.get(j).getAsignedminers()))) * (-1);
                                    else
                                        aux = (listMines.get(j).getAmountOfDeposits()
                                         - ((i + 1+minersByMine[j] + listMines.get(j).getAsignedminers())));
                                        matriz[i][j] = (listMines.get(j).getAmountOfDeposits())
                                            * 250 - (aux * 120);
                                        matriz1[i][j] = 1;
                                }
                           
                        }
                        else{
                            
                            int total;
                            if (((i + 1+listMines.get(j).getAsignedminers() + minersByMine[j])) < listMines.get(j).getAmountOfDeposits())
                                total = ((i + 1 +listMines.get(j).getAsignedminers()+ minersByMine[j])) * 800;
                            else if (((i + 1+listMines.get(j).getAsignedminers() + minersByMine[j])) == listMines.get(j).getAmountOfDeposits())
                                total = ((i + 1 +listMines.get(j).getAsignedminers()+ minersByMine[j])) * 950;
                            else
                            {
                                int aux;
                                if ((listMines.get(j).getAmountOfDeposits()
                                        - ((i + 1 + listMines.get(j).getAsignedminers()+minersByMine[j]))) < 0)
                                    aux = (listMines.get(j).getAmountOfDeposits()
                                            - ((i + 1 + minersByMine[j]+ listMines.get(j).getAsignedminers()+minersByMine[j]))) * (-1);
                                else
                                    aux = (listMines.get(j).getAmountOfDeposits()
                                            - ((i + 1+listMines.get(j).getAsignedminers() + minersByMine[j])));
                                total = (listMines.get(j).getAmountOfDeposits()) * 250 - ((aux) * 120);
                            }
                            if (total > matriz[i - 1][j])
                            {
                                matriz[i][j] = total;
                                matriz1[i][j] = 1;
                            }
                            else
                                matriz[i][j] = matriz[i - 1][j];
                        }
                    }
                }
            }
            String res = "";
            String res1 = "";
            for (int i = 0; i < matriz.length; i++)
            {
                for (int j = 0; j < matriz[i].length; j++)
                {
                    res = res + "|" + matriz[i][j] + "|";
                    res1 = res1 + "|" + matriz1[i][j] + "|";
                }
                res = res + "\n";
                res1 = res1 + "\n";
            }
            System.out.println(res);
            System.out.println(res1);
            int higher = 0;
            int position = 0;
            //int counthigher=0;
            LinkedList<Integer> positions = new LinkedList<>();
            for (int i = 0; i < matriz[matriz.length - 1].length; i++)
            {
                if (matriz[matriz.length - 1][i] > higher && !minesToTheLimit.contains(i))
                {
                    position = i;
                    higher = matriz[matriz.length - 1][i];
                    //positions.add(i);
                    //counthigher=1;
                }
                else if (matriz[matriz.length - 1][i] == higher)
                    positions.add(i);
            }
           boolean delete=false;
            if (positions.isEmpty())
            {
                for (int i = 0; i < matriz1.length; i++)
                {
                    if(!delete)
                    {
                        if (matriz1[i][position] == 1&& ((minersByMine[position] + 1)
                            +listMines.get(position).getAsignedminers())<=listMines.get(position).getMaxMiners())
                        {
                            amountMiners--;
                            minersByMine[position] = minersByMine[position] + 1;
                        }
                        else
                        {
                            if(((minersByMine[position] + 1)
                            +listMines.get(position).getAsignedminers())>listMines.get(position).getMaxMiners())
                            {
                                delete=true;
                                minesToTheLimit.add(position);
                                //listMines.remove(position);
                            }
                            
                        }
                    }
                }
            }
            else
            {
                position = betterOption(positions);
                for (int i = 0; i < matriz1.length; i++)
                {
                    if (matriz1[i][position] == 1)
                    {
                        amountMiners--;
                        minersByMine[position] = minersByMine[position] + 1;
                    }
                }
            }
        }
        for (int i = 0; i < minersByMine.length; i++)
        {
            if(listMines.get(i).getAsignedminers()==0)
            {
                listMines.get(i).setAsignedminers(minersByMine[i]);
            }
            else
            {
                listMines.get(i).setAsignedminers(listMines.get(i).getAsignedminers()+minersByMine[i]);
            }
            System.out.println(minersByMine[i]);
        }

    }

    public int betterOption(LinkedList<Integer> options)
    {
        int option = 0;
        double value = 0;

        for (int i = 0; i < options.size(); i++)
        {
            if (i == 0)
            {
                value = (this.pruebapanels.get(options.get(i)).getMine().getAmountForDeposit()
                        * this.pruebapanels.get(options.get(i)).getMine().getTimeToExtract())
                        / this.pruebapanels.get(options.get(i)).getMine().getProduceMineral();
                option = i;
            }
            else
            {
                double auxValue = (this.pruebapanels.get(options.get(i)).getMine().getAmountForDeposit()
                        * this.pruebapanels.get(options.get(i)).getMine().getTimeToExtract())
                        / this.pruebapanels.get(options.get(i)).getMine().getProduceMineral();

                if (auxValue < value)
                {
                    value = auxValue;
                    option = i;
                }
            }
        }
        return option;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        startBackground1 = new Views.StartBackground();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        minerosComodin = new javax.swing.JTextField();
        minerosCobre = new javax.swing.JTextField();
        minerosPlata = new javax.swing.JTextField();
        minerosOro = new javax.swing.JTextField();
        totalMineros = new javax.swing.JTextField();
        capacidadOro = new javax.swing.JTextField();
        capacidadPlata = new javax.swing.JTextField();
        capacidadCobre = new javax.swing.JTextField();
        gananciaCobre = new javax.swing.JTextField();
        gananciaPlata = new javax.swing.JTextField();
        gananciaOro = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnJson = new javax.swing.JMenuItem();
        btnLoad = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Total mineros:");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Mineros de oro:");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Mineros de plata:");

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Mineros de cobre:");

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Mineros comodines:");

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Capacidades de carga");

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Oro:");

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Plata:");

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Cobre:");

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Oro:");

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Plata:");

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Cobre:");

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Ganancias");

        minerosComodin.setEditable(false);
        minerosComodin.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        minerosComodin.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        minerosCobre.setEditable(false);
        minerosCobre.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        minerosCobre.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        minerosPlata.setEditable(false);
        minerosPlata.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        minerosPlata.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        minerosOro.setEditable(false);
        minerosOro.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        minerosOro.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        totalMineros.setEditable(false);
        totalMineros.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        totalMineros.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalMineros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalMinerosActionPerformed(evt);
            }
        });

        capacidadOro.setEditable(false);
        capacidadOro.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        capacidadOro.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        capacidadPlata.setEditable(false);
        capacidadPlata.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        capacidadPlata.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        capacidadCobre.setEditable(false);
        capacidadCobre.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        capacidadCobre.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        gananciaCobre.setEditable(false);
        gananciaCobre.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        gananciaCobre.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        gananciaPlata.setEditable(false);
        gananciaPlata.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        gananciaPlata.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gananciaPlata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gananciaPlataActionPerformed(evt);
            }
        });

        gananciaOro.setEditable(false);
        gananciaOro.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        gananciaOro.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout startBackground1Layout = new javax.swing.GroupLayout(startBackground1);
        startBackground1.setLayout(startBackground1Layout);
        startBackground1Layout.setHorizontalGroup(
            startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startBackground1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(startBackground1Layout.createSequentialGroup()
                        .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalMineros, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(minerosOro)
                            .addComponent(minerosPlata)
                            .addComponent(minerosCobre)
                            .addComponent(minerosComodin)))
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(106, 106, 106)
                .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(startBackground1Layout.createSequentialGroup()
                        .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(capacidadOro)
                            .addComponent(capacidadPlata)
                            .addComponent(capacidadCobre, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(84, 84, 84)
                .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(startBackground1Layout.createSequentialGroup()
                        .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gananciaOro, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gananciaPlata, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gananciaCobre, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        startBackground1Layout.setVerticalGroup(
            startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, startBackground1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(gananciaOro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(gananciaPlata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(gananciaCobre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97))
            .addGroup(startBackground1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(totalMineros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(minerosOro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(minerosPlata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(minerosCobre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(minerosComodin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(startBackground1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(capacidadOro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(capacidadPlata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(capacidadCobre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(149, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(startBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(startBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Informacion general", jPanel1);

        jMenu1.setText("File");

        btnJson.setText("Guardar");
        btnJson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJsonActionPerformed(evt);
            }
        });
        jMenu1.add(btnJson);

        btnLoad.setText("Cargar");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });
        jMenu1.add(btnLoad);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Acciones");

        jMenuItem1.setText("Agregar Mina");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Prueba Minero");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Grafo");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Dijkstra");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Prueba camino");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("Ubicar mineros");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Tiempo Dijkstra");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setText("Ganancias");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setText("Incapacitar Minero");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        TypeMetal t = new TypeMetal(this, true);
        Mine mine = new Mine(this.countMines, t.getMetal(), t.getAmount());
        ViewInfoMine v = new ViewInfoMine(this, true);
        Builder m = new Builder(this, true, mine);
        m.dispose();
        JPanel jp = new JPanel();
        jp.setBackground(Color.BLUE);
        Panel pAux = new Panel((int) (this.screenSize.getWidth()), (int) (this.screenSize.height));
        this.panelsThreads.add(new Thread(pAux));
        this.panelsThreads.getLast().start();
        jp.removeAll();
        ((FlowLayout) jp.getLayout()).setAlignment(FlowLayout.LEADING);
        pAux.setMine(mine);
        this.pruebapanels.add(pAux);
        //this.pruebapanels.getLast().getMine().setCollectQuantity(1.0);
        this.mineThreads.add(new Thread(this.pruebapanels.getLast().getMine()));
        this.mineThreads.getLast().start();
        this.pruebapanels.getLast().getMine().createGraph();
        this.pruebapanels.getLast().getMine().dijkstra();
        if (this.pruebapanels.getLast().getMine().getMetal().equalsIgnoreCase("oro"))
        {
            this.pruebapanels.getLast().getMine().setCapacityCharge(this.generalInformation.getCapacidadCargaOro());
            this.pruebapanels.getLast().getMine().setEarnigs(this.generalInformation.getGananciaOro());
        }
        else if (this.pruebapanels.getLast().getMine().getMetal().equalsIgnoreCase("plata"))
        {
            this.pruebapanels.getLast().getMine().setCapacityCharge(this.generalInformation.getCapacidadCargaPlata());
             this.pruebapanels.getLast().getMine().setEarnigs(this.generalInformation.getGananciaPlata());
        }
        else
        {
            this.pruebapanels.getLast().getMine().setCapacityCharge(this.generalInformation.getCapacidadCargaCobre());
            this.pruebapanels.getLast().getMine().setEarnigs(this.generalInformation.getGananciaCobre());
        }
        jp.add(pAux);
        this.panels.add(jp);
        this.jTabbedPane1.add("Mina " + this.countMines, this.panels.getLast());
        this.countMines++;
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        for (int i = 0; i < this.pruebapanels.getFirst().getMine().getMatrix().size(); i++)
        {
            for (int j = 0; j < this.pruebapanels.getFirst().getMine().getMatrix().get(i).size(); j++)
            {
                if (this.pruebapanels.getFirst().getMine().getMatrix().get(i).get(j).getObject() instanceof Road)
                {
                    Road road = (Road) this.pruebapanels.getFirst().getMine().getMatrix().get(i).get(j).getObject();
                    if (road.isEntry())
                        switch (road.getLocationEntry())
                        {
                            case 1:
                                this.pruebapanels.getFirst().getMine().getListMiners().add(new Miner(road.getX(), road.getY() + 2, 2, this.nombreMineros.getNombreMinero()));
                                this.pruebapanels.getFirst().getMine().getListMiners().getLast().setMovement(true);
                                this.minersThreads.add(new Thread(this.pruebapanels.getFirst().getMine().getListMiners().getLast()));
                                this.minersThreads.getLast().start();
                                break;
                            case 2:
                                this.pruebapanels.getFirst().getMine().getListMiners().add(new Miner(road.getX() + 2, road.getY(), 3, this.nombreMineros.getNombreMinero()));
                                this.pruebapanels.getFirst().getMine().getListMiners().getLast().setMovement(true);
                                this.minersThreads.add(new Thread(this.pruebapanels.getFirst().getMine().getListMiners().getLast()));
                                this.minersThreads.getLast().start();
                                break;
                            case 3:
                                this.pruebapanels.getFirst().getMine().getListMiners().add(new Miner(road.getX(), road.getY() + 2, 4, this.nombreMineros.getNombreMinero()));
                                this.pruebapanels.getFirst().getMine().getListMiners().getLast().setMovement(true);
                                this.minersThreads.add(new Thread(this.pruebapanels.getFirst().getMine().getListMiners().getLast()));
                                this.minersThreads.getLast().start();
                                break;
                            case 4:
                                this.pruebapanels.getFirst().getMine().getListMiners().add(new Miner(road.getX() + 2, road.getY(), 1, this.nombreMineros.getNombreMinero()));
                                this.pruebapanels.getFirst().getMine().getListMiners().getLast().setMovement(true);
                                this.minersThreads.add(new Thread(this.pruebapanels.getFirst().getMine().getListMiners().getLast()));
                                this.minersThreads.getLast().start();
                                break;
                        }
                }
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.pruebapanels.getFirst().getMine().createGraph();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        this.pruebapanels.getFirst().getMine().dijkstra();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        String[] aux = (JOptionPane.showInputDialog("punto")).split(",");
        Deposit d = (Deposit) this.pruebapanels.getFirst().getMine().getMatrix().get(Integer.parseInt(aux[0])).get(Integer.parseInt(aux[1])).getObject();
        this.pruebapanels.getFirst().getMine().getListMiners().add(new Miner(d.getX(), d.getY(), 1, "luz"));
        this.pruebapanels.getFirst().getMine().getListMiners().get(0).setMovement(true);
        this.pruebapanels.getFirst().getMine().route(JOptionPane.showInputDialog("punto"), 0);
        this.minersThreads.add(new Thread(this.pruebapanels.getFirst().getMine().getListMiners().get(0)));
        this.minersThreads.getLast().start();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void btnJsonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnJsonActionPerformed
    {//GEN-HEADEREND:event_btnJsonActionPerformed

        LinkedList<Mine> mines = new LinkedList<>();
        this.pruebapanels.forEach((panel) ->
        {
            mines.add(panel.getMine());
        });
        JsonSaveProcess save = new JsonSaveProcess(mines, generalInformation, this.minersInfo);
        FileWriter fw = null;
        try
        {
            FileDialog fd = new FileDialog(this, "Guardar", FileDialog.SAVE);
            fd.setFile("*.json");
            fd.setVisible(true);
            if (fd.getDirectory() != null && fd.getFile() != null)
            {
                String src = fd.getDirectory() + fd.getFile();
                fw = new FileWriter(src, true);
                //System.out.println(this.game.save());
                fw.append(save.save());
                fw.close();
            }
        }
        catch (IOException e)
        {
            Logger.getLogger(Minerals_Minerals.class.getName()).log(Level.SEVERE, null, e);
        }

    }//GEN-LAST:event_btnJsonActionPerformed


    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnLoadActionPerformed
    {//GEN-HEADEREND:event_btnLoadActionPerformed
        this.load();
    }//GEN-LAST:event_btnLoadActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        LinkedList<Mine> listMinesOro = new LinkedList<>();
        LinkedList<Mine> listMinesPlata = new LinkedList<>();
        LinkedList<Mine> listMinesCobre = new LinkedList<>();
        LinkedList<Mine> listMine = new LinkedList<>();
        boolean flag=true;
        for (Panel panel : this.pruebapanels)
        {
            if (panel.getMine().getMetal().equalsIgnoreCase("oro"))
                listMinesOro.add(panel.getMine());
            else if (panel.getMine().getMetal().equalsIgnoreCase("cobre"))
                listMinesCobre.add(panel.getMine());
            else
                listMinesPlata.add(panel.getMine());
            listMine.add(panel.getMine());
        }
        if(flag && this.theAreMiners(listMinesOro, this.minersInfo.getTotalMinerosOro()))
        {
            if(flag && this.theAreMiners(listMinesPlata, this.minersInfo.getTotalMinerosPlata()))
            {
                if(flag && this.theAreMiners(listMinesOro, this.minersInfo.getTotalMinerosCobre())){
                    flag=true;
                }
                else
                {
                    flag=false;
                    JOptionPane.showMessageDialog(null,"Tiene mineros de cobre pero no minas de cobre");
                }
                        
            }else
            {
                JOptionPane.showMessageDialog(null,"Tiene mineros de plata pero no minas plata");
                flag=false;
            }
        }
        else
        {
            flag=false;
             JOptionPane.showMessageDialog(null,"Tiene mineros de oro pero no minas oro");
        }
        if(flag && this.validateQuantityMiners(listMine,  this.minersInfo.getTotalMineros()))
        {
            flag=true;
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Se excede la cantidad maxima de mineros permitidos");
            flag=false;
        }
        if(flag)
        {
            if (!listMinesOro.isEmpty())
                this.locateMiners(listMinesOro, this.minersInfo.getTotalMinerosOro());
            if (!listMinesCobre.isEmpty())
                this.locateMiners(listMinesCobre, this.minersInfo.getTotalMinerosCobre());
            if (!listMinesPlata.isEmpty())
                this.locateMiners(listMinesPlata, this.minersInfo.getTotalMinerosPlata());
            this.locateMiners(listMine, this.minersInfo.getTotalminerosComodin());
            int total = 0;
            for (Panel panel : this.pruebapanels)
            {
                panel.getMine().createMiners(this.mineThreads);
                panel.getMine().calculateGain();
                total = total + panel.getMine().getTotalGain().getLast();
                System.out.println(panel.getMine().getTotalGain());
                System.out.println(panel.getMine().getAsignedminers());
            }
            this.hilo=new Thread(this);
            hilo.start();
            this.totalGain.add(total);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        LinkedList<Mine> listMines = new LinkedList<Mine>();
        for (Panel p : this.pruebapanels)
        {
            listMines.add(p.getMine());
        }
        DijkstraGrapich m = new DijkstraGrapich(this, true, listMines);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed

        LinkedList<Mine> listMines = new LinkedList<Mine>();
        for (Panel p : this.pruebapanels)
        {
            listMines.add(p.getMine());
        }
        GainGraphic m = new GainGraphic(this, true, listMines, this.totalGain);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void totalMinerosActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_totalMinerosActionPerformed
    {//GEN-HEADEREND:event_totalMinerosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalMinerosActionPerformed

    private void gananciaPlataActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_gananciaPlataActionPerformed
    {//GEN-HEADEREND:event_gananciaPlataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gananciaPlataActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
       LinkedList<Mine> listMines=new LinkedList<>();
        for(Panel panel:this.pruebapanels)
       {
           listMines.add(panel.getMine());
       }
        incpacitateMiner option=new incpacitateMiner(this,true , listMines);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private boolean theAreMiners(LinkedList<Mine> listMines, int quantity)
    {
        if(listMines.isEmpty() && quantity>0)
        {
            return false;
        }else
        {
            return  true;
        }
    }
    
    
    private boolean validateQuantityMiners(LinkedList<Mine> listMiners,int totalMiners)
    {
        int totalMinerspermitted=0;
        for(Mine mine:listMiners)
        {
            totalMinerspermitted=totalMinerspermitted+mine.getMaxMiners();
        }
        if(totalMinerspermitted>totalMiners)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public void load()
    {
        String chain = "";
        try
        {
            JsonModel aux;
            FileDialog fd = new FileDialog(this, "Select file", FileDialog.LOAD);
            fd.setVisible(true);
            String src = fd.getDirectory() + fd.getFile();
            BufferedReader br;

            br = new BufferedReader(new FileReader(new File(src)));
            String s;
            try
            {
                while ((s = br.readLine()) != null)
                {
                    chain += s;
                }

            }
            catch (IOException ex)
            {
                Logger.getLogger(Minerals_Minerals.class.getName()).log(Level.SEVERE, null, ex);
            }
            br.close();
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Minerals_Minerals.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Minerals_Minerals.class.getName()).log(Level.SEVERE, null, ex);
        }
        JsonLoadProcess load = new JsonLoadProcess(chain);
        this.generalInformation = load.getInformationGeneralOfMine();
        this.minersInfo = load.getMinersInfo();
        LinkedList<Mine> ms = load.getMines();
        for (Mine m : ms)
        {

            JPanel jp = new JPanel();
            jp.setBackground(Color.BLUE);
            Panel pAux = new Panel((int) (this.screenSize.getWidth()), (int) (this.screenSize.height));
            jp.removeAll();
            ((FlowLayout) jp.getLayout()).setAlignment(FlowLayout.LEADING);
            pAux.setMine(m);
            this.pruebapanels.add(pAux);
            //this.panelsThreads.add(new Thread(pAux));
            //this.panelsThreads.getLast().start();
            this.mineThreads.add(new Thread(this.pruebapanels.getLast().getMine()));
            this.mineThreads.getLast().start();
            this.pruebapanels.getLast().getMine().createGraph();
            this.pruebapanels.getLast().getMine().dijkstra();
            if (this.pruebapanels.getLast().getMine().getMetal().equalsIgnoreCase("oro"))
                this.pruebapanels.getLast().getMine().setCapacityCharge(this.generalInformation.getCapacidadCargaOro());
            else if (this.pruebapanels.getLast().getMine().getMetal().equalsIgnoreCase("plata"))
                this.pruebapanels.getLast().getMine().setCapacityCharge(this.generalInformation.getCapacidadCargaPlata());
            else
                this.pruebapanels.getLast().getMine().setCapacityCharge(this.generalInformation.getCapacidadCargaCobre());
            jp.add(pAux);
            this.panels.add(jp);
            this.jTabbedPane1.add("Mina " + this.countMines, this.panels.getLast());
            this.countMines++;
        }
        this.initialInformation();
    }

    public void newInformation()
    {
        General g = new General(this, true, this.minersInfo, this.generalInformation);
        this.minersInfo = g.getMinersInfo();
        this.generalInformation = g.getGeneralInformation();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Minerals_Minerals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Minerals_Minerals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Minerals_Minerals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Minerals_Minerals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Minerals_Minerals().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnJson;
    private javax.swing.JMenuItem btnLoad;
    private javax.swing.JTextField capacidadCobre;
    private javax.swing.JTextField capacidadOro;
    private javax.swing.JTextField capacidadPlata;
    private javax.swing.JTextField gananciaCobre;
    private javax.swing.JTextField gananciaOro;
    private javax.swing.JTextField gananciaPlata;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField minerosCobre;
    private javax.swing.JTextField minerosComodin;
    private javax.swing.JTextField minerosOro;
    private javax.swing.JTextField minerosPlata;
    private Views.StartBackground startBackground1;
    private javax.swing.JTextField totalMineros;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while(true)
        {
            for (Panel panel : this.pruebapanels) 
            {
                panel.minersFollowRoute();
                panel.minersMovement();
                panel.minersWork();
            }
        }
    }
}
