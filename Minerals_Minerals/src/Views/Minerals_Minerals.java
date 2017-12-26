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
public class Minerals_Minerals extends javax.swing.JFrame
{

    int countMines = 1;
    LinkedList<Integer> totalGain=new LinkedList<>();
    LinkedList<JPanel> panels = new LinkedList<>();
    InformationMineJson generalInformation = new InformationMineJson();
    LinkedList<Panel> pruebapanels = new LinkedList<>();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    LinkedList<Thread> minersThreads = new LinkedList<>();

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
    }
    
    
    public void locateMiners(LinkedList<Mine> listMines,int miners)
    {
        int[] minersByMine=new int[listMines.size()];
        int amountMiners=miners;
        while(amountMiners>0)
        {
            int[][] matriz=new int[amountMiners][listMines.size()];
            int[][] matriz1=new int[amountMiners][listMines.size()];
            for (int i = 0; i < matriz.length; i++) 
            {
              for (int j = 0; j < matriz[i].length; j++) 
              {
                if(minersByMine[j]==0)
                {
                    if(i==0)
                    {
                        if((i+1)<listMines.get(j).getAmountOfDeposits())
                        {
                            matriz[i][j]=(i+1)*800;
                            matriz1[i][j]=1;
                        }
                        else if((i+1)==listMines.get(j).getAmountOfDeposits())
                        {
                            matriz[i][j]=(i+1)*950;
                            matriz1[i][j]=1;
                        }
                        else
                        {
                            int aux;
                            if((listMines.get(j).getAmountOfDeposits()-
                                ((i+1+minersByMine[j])))<0)
                            {
                                aux=(listMines.get(j).getAmountOfDeposits()-
                                    ((i+1+minersByMine[j])))*(-1);
                            }else
                            {
                                aux=(listMines.get(j).getAmountOfDeposits()-
                                    ((i+1+minersByMine[j])));
                            }
                            matriz[i][j]=(listMines.get(j).getAmountOfDeposits())
                                *250-((aux)*120);
                            matriz1[i][j]=1;
                        }
                    }
                    else
                    {
                        int total;
                        if((i+1)<listMines.get(j).getAmountOfDeposits())
                        {
                            total=(i+1)*800;
                        }
                        else if((i+1)==listMines.get(j).getAmountOfDeposits())
                        {
                            total=(i+1)*950;
                        }
                        else
                        {
                            int aux;
                            if((listMines.get(j).getAmountOfDeposits()-
                               ((i+1+minersByMine[j])))<0)
                            {
                                aux=(listMines.get(j).getAmountOfDeposits()-
                                    ((i+1+minersByMine[j])))*(-1);
                            }else
                            {
                                aux=(listMines.get(j).getAmountOfDeposits()-
                                    ((i+1+minersByMine[j])));
                            }
                            total=(listMines.get(j).getAmountOfDeposits())*250-((aux)*120);
                        }
                        if(total>matriz[i-1][j]){
                            matriz[i][j]=total;
                            matriz1[i][j]=1;
                        }else
                        {
                            matriz[i][j]=matriz[i-1][j];
                        }
                    }
                    
                }
                else
                {
                    if(i==0)
                    {
                        if(((i+1+minersByMine[j]))<listMines.get(j).getAmountOfDeposits())
                        {
                            matriz[i][j]=((i+1+minersByMine[j]))*800;
                            matriz1[i][j]=1;
                        }
                        else if(((i+1+minersByMine[j]))==listMines.get(j).getAmountOfDeposits())
                        {
                            matriz[i][j]=((i+1+minersByMine[j]))*950;
                            matriz1[i][j]=1;
                        }
                        else
                        {
                            int aux;
                            if((listMines.get(j).getAmountOfDeposits()-
                                ((i+1+minersByMine[j])))<0)
                            {
                                aux=(listMines.get(j).getAmountOfDeposits()-
                                    ((i+1+minersByMine[j])))*(-1);
                            }else
                            {
                                aux=(listMines.get(j).getAmountOfDeposits()-
                                   ((i+1+minersByMine[j])));
                            }
                            matriz[i][j]=(listMines.get(j).getAmountOfDeposits())
                                *250-(aux*120);
                            matriz1[i][j]=1;
                        }
                    }
                    else
                    {
                        int total;
                        if(((i+1+minersByMine[j]))<listMines.get(j).getAmountOfDeposits())
                        {
                            total=((i+1+minersByMine[j]))*800;
                        }
                        else if(((i+1+minersByMine[j]))==listMines.get(j).getAmountOfDeposits())
                        {
                            total=((i+1+minersByMine[j]))*950;
                        }
                        else
                        {
                            int aux;
                            if((listMines.get(j).getAmountOfDeposits()-
                                ((i+1+minersByMine[j])))<0)
                            {
                                aux=(listMines.get(j).getAmountOfDeposits()-
                                   ((i+1+minersByMine[j])))*(-1);
                            }else
                            {
                                aux=(listMines.get(j).getAmountOfDeposits()-
                                    ((i+1+minersByMine[j])));
                            }
                            total=(listMines.get(j).getAmountOfDeposits())*250-((aux)*120);
                        }
                        if(total>matriz[i-1][j]){
                            matriz[i][j]=total;
                            matriz1[i][j]=1;
                        }else
                        {
                            matriz[i][j]=matriz[i-1][j];
                        }   
                    }
                }
            }
        }
            String res="";
            String res1="";
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    res=res+"|"+matriz[i][j]+"|";
                    res1=res1+"|"+matriz1[i][j]+"|";
                }
                res=res+"\n";
                res1=res1+"\n";
            }
            System.out.println(res);
            System.out.println(res1);
        int higher=0;
        int position=0;
        //int counthigher=0;
        LinkedList<Integer> positions=new LinkedList<>();
        for (int i = 0; i < matriz[matriz.length-1].length; i++) 
        {
           if(matriz[matriz.length-1][i]>higher)
            {
                position=i;
                higher=matriz[matriz.length-1][i];
                //positions.add(i);
                //counthigher=1;
            }
           else if(matriz[matriz.length-1][i]==higher)
           {
               positions.add(i);
           }
        }
        if(positions.isEmpty())
        {
            for (int i = 0; i < matriz1.length; i++) 
            {
               if(matriz1[i][position]==1)
               {
                    amountMiners--;
                    minersByMine[position]=minersByMine[position]+1;
               }
            }
        }else
        {
            position=betterOption(positions);
            for (int i = 0; i < matriz1.length; i++) 
            {
               if(matriz1[i][position]==1)
               {
                    amountMiners--;
                    minersByMine[position]=minersByMine[position]+1;
               }
            }
        }
    }
        for (int i = 0; i < minersByMine.length; i++) 
        {
            listMines.get(i).setAsignedminers(minersByMine[i]);
            System.out.println(minersByMine[i]);
        }
        
}
    
    public int betterOption(LinkedList<Integer> options)
    {
        int option = 0;
        double value=0;
        
        for (int i = 0; i < options.size(); i++) 
        {
            if(i==0)
            {
                value=(this.pruebapanels.get(options.get(i)).getMine().getAmountForDeposit()*
                       this.pruebapanels.get(options.get(i)).getMine().getTimeToExtract())/
                       this.pruebapanels.get(options.get(i)).getMine().getProduceMineral();
                option=i;
            }else
            {
                double auxValue=(this.pruebapanels.get(options.get(i)).getMine().getAmountForDeposit()*
                       this.pruebapanels.get(options.get(i)).getMine().getTimeToExtract())/
                       this.pruebapanels.get(options.get(i)).getMine().getProduceMineral();
                
                if(auxValue<value)
                {
                    value=auxValue;
                    option=i;
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jMenuItem7.setText("jMenuItem7");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setText("jMenuItem8");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        TypeMetal t = new TypeMetal(this, true);
        Mine mine = new Mine(this.countMines, t.getMetal(), t.getAmount());
        Builder m = new Builder(this, true, mine);
        m.dispose();
        JPanel jp = new JPanel();
        jp.setBackground(Color.BLUE);
        Panel pAux = new Panel((int) (this.screenSize.getWidth()), (int) (this.screenSize.height));
        jp.removeAll();
        ((FlowLayout) jp.getLayout()).setAlignment(FlowLayout.LEADING);
        pAux.setMine(mine);
        this.pruebapanels.add(pAux);
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
                                this.pruebapanels.getFirst().getMine().getListMiners().add(new Miner(road.getX(), road.getY() + 2, 2, "Luz"));
                                this.pruebapanels.getFirst().getMine().getListMiners().getFirst().setMovement(true);
                                this.minersThreads.add(new Thread(this.pruebapanels.getFirst().getMine().getListMiners().getLast()));
                                this.minersThreads.getLast().start();
                                break;
                            case 2:
                                this.pruebapanels.getFirst().getMine().getListMiners().add(new Miner(road.getX() + 2, road.getY(), 3, "Luz"));
                                this.pruebapanels.getFirst().getMine().getListMiners().getFirst().setMovement(true);
                                this.minersThreads.add(new Thread(this.pruebapanels.getFirst().getMine().getListMiners().getLast()));
                                this.minersThreads.getLast().start();
                                break;
                            case 3:
                                this.pruebapanels.getFirst().getMine().getListMiners().add(new Miner(road.getX(), road.getY() + 2, 4, "Luz"));
                                this.pruebapanels.getFirst().getMine().getListMiners().getFirst().setMovement(true);
                                this.minersThreads.add(new Thread(this.pruebapanels.getFirst().getMine().getListMiners().getLast()));
                                this.minersThreads.getLast().start();
                                break;
                            case 4:
                                this.pruebapanels.getFirst().getMine().getListMiners().add(new Miner(road.getX() + 2, road.getY(), 1, "Luz"));
                                this.pruebapanels.getFirst().getMine().getListMiners().getFirst().setMovement(true);
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
        JsonSaveProcess save = new JsonSaveProcess(mines, generalInformation);
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
            jp.add(pAux);
            this.panels.add(jp);
            this.jTabbedPane1.add("Mina " + this.countMines, this.panels.getLast());
            this.countMines++;
        }
    }//GEN-LAST:event_btnLoadActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        int count=1;
        while(count<=3)
        {   
            LinkedList<Mine> listMines=new LinkedList<>();
            for(Panel panel:this.pruebapanels)
            {
                if(count==1 && panel.getMine().getMetal().equalsIgnoreCase("oro"))
                {
                    listMines.add(panel.getMine());
                }
            }
            if (!listMines.isEmpty())
            {
                this.locateMiners(listMines, 13);
                count++;
            }
            else
            {
                count++;
            }
        }
        int total=0;
        for(Panel panel:this.pruebapanels)
        {
            panel.getMine().calculateGain();
            total=total+panel.getMine().getTotalGain().getLast();
            System.out.println(panel.getMine().getTotalGain());
        }
        this.totalGain.add(total);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
       LinkedList<Mine> listMines=new LinkedList<Mine>();
       for(Panel p:this.pruebapanels)
       {
           listMines.add(p.getMine());
       }
        DijkstraGrapich m = new DijkstraGrapich(this, true, listMines);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        LinkedList<Mine> listMines=new LinkedList<Mine>();
       for(Panel p:this.pruebapanels)
       {
           listMines.add(p.getMine());
       }
        GainGrapich m = new GainGrapich(this, true, listMines,this.totalGain);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
