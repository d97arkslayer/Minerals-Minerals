/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.awt.Toolkit;

/**
 *
 * @author Esteban Herrera y Jaime Bernal
 * Formulario de inicio
 */
public class Start extends javax.swing.JDialog
{

    Minerals_Minerals father;

    /**
     * Crea nuevo fomulario Start
     * @param parent padre del fromulario
     * @param modal si el padre puede trabajar
     * @param father ventena principal
     */
    public Start(java.awt.Frame parent, boolean modal, Minerals_Minerals father)
    {
        super(parent, modal);
        
        initComponents();
        this.startBackground1.setIndex(1);
        this.father = father;
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("../Images/miner_logo.png")));
        this.setTitle("Bienvenido");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        startBackground1 = new Views.StartBackground();
        jLabel1 = new javax.swing.JLabel();
        newButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Minerals & Minerals");

        newButton.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        newButton.setText("Nueva");
        newButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                newButtonActionPerformed(evt);
            }
        });

        loadButton.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        loadButton.setText("Cargar");
        loadButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                loadButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout startBackground1Layout = new javax.swing.GroupLayout(startBackground1);
        startBackground1.setLayout(startBackground1Layout);
        startBackground1Layout.setHorizontalGroup(
            startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startBackground1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, startBackground1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(235, 235, 235))
        );
        startBackground1Layout.setVerticalGroup(
            startBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startBackground1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(newButton)
                .addGap(18, 18, 18)
                .addComponent(loadButton)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(startBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(startBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_loadButtonActionPerformed
    {//GEN-HEADEREND:event_loadButtonActionPerformed
        this.father.load();
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_loadButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newButtonActionPerformed
    {//GEN-HEADEREND:event_newButtonActionPerformed
        this.father.newInformation();
        this.father.initialInformation();
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_newButtonActionPerformed

    ///**
     //* @param args the command line arguments
     //*/
//    public static void main(String args[])
//    {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try
//        {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
//            {
//                if ("Nimbus".equals(info.getName()))
//                {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        }
//        catch (ClassNotFoundException ex)
//        {
//            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        catch (InstantiationException ex)
//        {
//            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        catch (IllegalAccessException ex)
//        {
//            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        catch (javax.swing.UnsupportedLookAndFeelException ex)
//        {
//            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable()
//        {
//            public void run()
//            {
//                Start dialog = new Start(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter()
//                {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e)
//                    {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton newButton;
    private Views.StartBackground startBackground1;
    // End of variables declaration//GEN-END:variables
}
