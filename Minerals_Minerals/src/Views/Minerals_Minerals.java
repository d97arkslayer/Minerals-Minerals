/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author darkd
 */
public class Minerals_Minerals extends javax.swing.JFrame
{

    int countMines = 1;
    LinkedList<JPanel> panels = new LinkedList<>();
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
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");
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
        Mine mine = new Mine(this.countMines, t.getMetal());
        Builder m = new Builder(this, true, mine);
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
                                this.pruebapanels.getFirst().getMine().getListMiners().add(new Miner(road.getX(), road.getY() + 2, 2,"Luz"));
                                this.pruebapanels.getFirst().getMine().getListMiners().getFirst().setMovement(true);
                                this.minersThreads.add(new Thread(this.pruebapanels.getFirst().getMine().getListMiners().getLast()));
                                this.minersThreads.getLast().start();
                                break;
                            case 2:
                                this.pruebapanels.getFirst().getMine().getListMiners().add(new Miner(road.getX() + 2, road.getY(), 3,"Luz"));
                                this.pruebapanels.getFirst().getMine().getListMiners().getFirst().setMovement(true);
                                this.minersThreads.add(new Thread(this.pruebapanels.getFirst().getMine().getListMiners().getLast()));
                                this.minersThreads.getLast().start();
                                break;
                            case 3:
                                this.pruebapanels.getFirst().getMine().getListMiners().add(new Miner(road.getX(), road.getY() + 2, 4,"Luz"));
                                this.pruebapanels.getFirst().getMine().getListMiners().getFirst().setMovement(true);
                                this.minersThreads.add(new Thread(this.pruebapanels.getFirst().getMine().getListMiners().getLast()));
                                this.minersThreads.getLast().start();
                                break;
                            case 4:
                                this.pruebapanels.getFirst().getMine().getListMiners().add(new Miner(road.getX() + 2, road.getY(), 1,"Luz"));
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
        String[] aux=(JOptionPane.showInputDialog("punto")).split(",");
        Deposit d=(Deposit)this.pruebapanels.getFirst().getMine().getMatrix().get(Integer.parseInt(aux[0])).get(Integer.parseInt(aux[1])).getObject();
        this.pruebapanels.getFirst().getMine().getListMiners().add(new Miner(d.getX(),d.getY() , 1, "luz"));
        this.pruebapanels.getFirst().getMine().getListMiners().get(0).setMovement(true);
        this.pruebapanels.getFirst().getMine().route(JOptionPane.showInputDialog("punto"),0);
        this.minersThreads.add(new Thread(this.pruebapanels.getFirst().getMine().getListMiners().get(0)));
        this.minersThreads.getLast().start();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

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
            public void run()
            {
                new Minerals_Minerals().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
