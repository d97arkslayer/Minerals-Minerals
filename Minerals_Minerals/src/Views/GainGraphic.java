/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.Mine;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Chinche
 */
public class GainGrapich extends java.awt.Dialog {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    LinkedList<Mine> listMine;
    LinkedList<Integer> listGain;
    /**
     * Creates new form GainGrapich
     */
    public GainGrapich(java.awt.Frame parent, boolean modal,LinkedList<Mine> listMines,LinkedList<Integer> totalgain) {
        super(parent, modal);
        initComponents();
        this.listMine=listMines;
        this.listGain=totalgain;
        for(Mine mine:listMines)
        {
            this.jComboBox1.addItem("Mina-"+(mine.getId()+1));
        }
        this.setTitle("Grafica");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize((int) (screenSize.getWidth()) - 80, (int) (screenSize.getHeight()) - 80);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Total", "Total por minas" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 90, 30));

        jButton1.setText("Graficar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 93, 80, 40));
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 370, 250));

        add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(this.jComboBox1.getSelectedItem().equals("Total"))
        {
            ChartPanel panel;
            JFreeChart chart=null;

            XYPlot plot;
            XYSplineRenderer renderer=new XYSplineRenderer();
            XYSeriesCollection dataset=new XYSeriesCollection();

            ValueAxis x= new NumberAxis();
            ValueAxis y= new NumberAxis();

            XYSeries serie=new XYSeries("Total ganado");
            
            jPanel2.removeAll();
            
            for (int i = 0; i < this.listGain.size(); i++) 
            {
                serie.add((float)(i+1),this.listGain.get(i));
            }
            dataset.addSeries(serie);

            x.setLabel("Fase en la que se produce");
            y.setLabel("Ganancia");
            
            plot=new  XYPlot(dataset,x,y,renderer);

            chart=new JFreeChart(plot);
            chart.setTitle("Ganancia total");
            
            panel= new ChartPanel(chart);
            panel.setBounds(0, 0, 370, 250);
            jPanel2.add(panel);
            jPanel2.repaint();
        }
        else if(this.jComboBox1.getSelectedItem().equals("Total por minas"))
        {
            ChartPanel panel;
            JFreeChart chart=null;

            XYPlot plot;
            XYSplineRenderer renderer=new XYSplineRenderer();
            XYSeriesCollection dataset=new XYSeriesCollection();
            
            ValueAxis x= new NumberAxis();
            ValueAxis y= new NumberAxis();
            
            LinkedList<XYSeries> series=new LinkedList<>();
            jPanel2.removeAll();
            
            for (int i = 0; i < this.listMine.size(); i++) 
            {
                series.add(new XYSeries("Mina-"+(this.listMine.get(i).getId()+1)));
                for (int j = 0; j < this.listMine.get(i).getTotalGain().size(); j++) 
                {
                    series.get(i).add((float)(j+1), this.listMine.get(i).getTotalGain().get(j));
                }
            }
            for (int i = 0; i < series.size(); i++) {
                dataset.addSeries(series.get(i));
            }
            x.setLabel("Fase en la que se produce");
            y.setLabel("Ganancia");
            
            plot=new  XYPlot(dataset,x,y,renderer);

            chart=new JFreeChart(plot);
            chart.setTitle("Ganancia total");
            panel= new ChartPanel(chart);
            panel.setBounds(0, 0, 370, 250);
            jPanel2.add(panel);
            jPanel2.repaint();
        }
        else
        {
            ChartPanel panel;
            JFreeChart chart=null;

            XYPlot plot;
            XYSplineRenderer renderer=new XYSplineRenderer();
            XYSeriesCollection dataset=new XYSeriesCollection();

            ValueAxis x= new NumberAxis();
            ValueAxis y= new NumberAxis();

            XYSeries serie=new XYSeries("Total ganado en la "+this.jComboBox1.getSelectedItem());

            jPanel2.removeAll();
            int i=0;
            boolean flag=true;
            while(flag && i<this.listMine.size())
            {
                if(this.jComboBox1.getSelectedItem().equals("Mina-"+(this.listMine.get(i).getId()+1)))
                {
                    flag=false;
                }
                i++;
            }
            for (int j = 0; j < this.listMine.get(i-1).getTotalGain().size(); j++) 
            {
                serie.add((float)(j+1), this.listMine.get(i-1).getTotalGain().get(j));
            }
            dataset.addSeries(serie);
            x.setLabel("Fase en la que se produce");
            y.setLabel("Ganancia");
            
            plot=new  XYPlot(dataset,x,y,renderer);

            chart=new JFreeChart(plot);
            chart.setTitle("Ganancia total");
            panel= new ChartPanel(chart);
            panel.setBounds(0, 0, 370, 250);
            jPanel2.add(panel);
            jPanel2.repaint();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}