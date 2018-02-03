/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.Deposit;
import Models.Mine;
import Models.Road;
import Models.Wall;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Chinche
 */
public class Panel extends javax.swing.JPanel implements Runnable
{

    private Mine mine = new Mine();

    /**
     * Creates new form Panel
     */
    public Panel()
    {
        initComponents();
    }
    public Panel(int h, int w)
    {
        initComponents();
        this.setPreferredSize(new Dimension(w, h));
    }

    private void minersFollowRoute()
    {
       if(!this.mine.getListMiners().isEmpty())
       {
            for (int i = 0; i < this.mine.getListMiners().size(); i++)
            {
                if (this.mine.getListMiners().get(i).isFollowRoute())
                {
                    String position = this.mine.getListMiners().get(i).getRoute().getFirst();
                    for (int j = 0; j < this.mine.getMatrix().size(); j++)
                    {
                        for (int k = 0; k < this.mine.getMatrix().get(j).size(); k++)
                        {
                            if (this.mine.getMatrix().get(j).get(k).getObject() instanceof Road)
                            {
                                Road road = (Road) this.mine.getMatrix().get(j).get(k).getObject();
                                Rectangle r = new Rectangle((road.getX() + (road.getWidth() / 2)) - 4, (road.getY() + (road.getHeight() / 2)) - 4, 10, 10);
                                if ("".equals(this.mine.getListMiners().get(i).getPreviousCollision()) || !this.mine.getListMiners().get(i).getPreviousCollision().equalsIgnoreCase(j + "," + k))
                                    if (r.intersects(new Rectangle(this.mine.getListMiners().get(i).getX() + (this.mine.getListMiners().get(i).getWidth() / 2), this.mine.getListMiners().get(i).getY() + (this.mine.getListMiners().get(i).getWidth() / 2), 5, 5)) && this.mine.getListMiners().get(i).getDirection() == 2)
                                    {
                                        if (position.equals(j + "," + k))
                                        {
                                            this.mine.getListMiners().get(i).setPreviousCollision(j + "," + k);
                                            int direction = this.mine.newDirection(i);
                                            if (direction != 0)
                                                this.mine.getListMiners().get(i).changeDirection(direction);
                                            else
                                               //this.mine.getListMiners().get(i).setMovement(false);
                                                if(this.mine.getAmountOfDeposits()<=0)
                                                {
                                                    this.mine.getListMiners().get(i).setMovement(false);
                                                }else
                                                {
                                                    this.mine.getListMiners().get(i).setFollowRoute(false);
                                                    this.mine.getListMiners().get(i).resetDirection();
                                                }
                                        }
                                    }
                                    else if (r.intersects(new Rectangle(this.mine.getListMiners().get(i).getX() + (this.mine.getListMiners().get(i).getWidth() / 2), this.mine.getListMiners().get(i).getY() + (this.mine.getListMiners().get(i).getWidth() / 2), 5, 5)) && this.mine.getListMiners().get(i).getDirection() == 3)
                                    {
                                        if (position.equals(j + "," + k))
                                        {
                                            this.mine.getListMiners().get(i).setPreviousCollision(j + "," + k);
                                            int direction = this.mine.newDirection(i);
                                            if (direction != 0)
                                                this.mine.getListMiners().get(i).changeDirection(direction);
                                            else
                                                if(this.mine.getAmountOfDeposits()<=0)
                                                {
                                                    this.mine.getListMiners().get(i).setMovement(false);
                                                }else
                                                {
                                                    this.mine.getListMiners().get(i).resetDirection();
                                                    this.mine.getListMiners().get(i).setFollowRoute(false);
                                                }
                                                //this.mine.getListMiners().get(i).setMovement(false);
                                        }
                                    }
                                    else if (r.intersects(new Rectangle(this.mine.getListMiners().get(i).getX() + (this.mine.getListMiners().get(i).getWidth() / 2), this.mine.getListMiners().get(i).getY() + (this.mine.getListMiners().get(i).getWidth() / 2), 5, 5)) && this.mine.getListMiners().get(i).getDirection() == 4)
                                    {
                                        if (position.equals(j + "," + k))
                                        {
                                            this.mine.getListMiners().get(i).setPreviousCollision(j + "," + k);
                                            int direction = this.mine.newDirection(i);
                                            if (direction != 0)
                                                this.mine.getListMiners().get(i).changeDirection(direction);
                                            else
                                                //this.mine.getListMiners().get(i).setMovement(false);
                                                if(this.mine.getAmountOfDeposits()<=0)
                                                {
                                                    this.mine.getListMiners().get(i).setMovement(false);
                                                }else
                                                {
                                                    this.mine.getListMiners().get(i).resetDirection();
                                                    this.mine.getListMiners().get(i).setFollowRoute(false);
                                                }
                                        }
                                    }
                                    else if (r.intersects(new Rectangle(this.mine.getListMiners().get(i).getX() + (this.mine.getListMiners().get(i).getWidth() / 2), this.mine.getListMiners().get(i).getY() + (this.mine.getListMiners().get(i).getWidth() / 2), 5, 5)) && this.mine.getListMiners().get(i).getDirection() == 1)
                                        if (position.equals(j + "," + k))
                                        {
                                            this.mine.getListMiners().get(i).setPreviousCollision(j + "," + k);
                                            int direction = this.mine.newDirection(i);
                                            if (direction != 0)
                                                this.mine.getListMiners().get(i).changeDirection(direction);
                                            else
                                                //this.mine.getListMiners().get(i).setMovement(false);
                                                if(this.mine.getAmountOfDeposits()<=0)
                                                {
                                                    this.mine.getListMiners().get(i).setMovement(false);
                                                }else
                                                {
                                                    this.mine.getListMiners().get(i).resetDirection();
                                                    this.mine.getListMiners().get(i).setFollowRoute(false);
                                                }
                                        }
                            }
                        }
                    }
                }
            }
       }
    }

    private void minersWork()
    {
        if(!this.mine.getListMiners().isEmpty())
        {
            for (int i = 0; i < this.mine.getListMiners().size(); i++)
            {
                if (!this.mine.getListMiners().get(i).isFollowRoute())
                    for (int j = 0; j < this.mine.getMatrix().size(); j++)
                    {
                        for (int k = 0; k < this.mine.getMatrix().get(j).size(); k++)
                        {
                            if (this.mine.getMatrix().get(j).get(k).getObject() instanceof Deposit && !this.mine.getListMiners().get(i).getState().equalsIgnoreCase("trabajo"))
                            {
                                Deposit deposit = (Deposit) this.mine.getMatrix().get(j).get(k).getObject();
                                Rectangle r = new Rectangle(deposit.getX(), deposit.getY(), deposit.getWidth(), deposit.getHeight());
                                if (r.intersects(new Rectangle(this.mine.getListMiners().get(i).getX(), this.mine.getListMiners().get(i).getY(), this.mine.getListMiners().get(i).getWidth() + 4, this.mine.getListMiners().get(i).getHeight())) && this.mine.getListMiners().get(i).getDirection() == 2)
                                {
                                    this.mine.getListMiners().get(i).setState("trabajo");
                                    this.mine.getListMiners().get(i).setWorkLocate(j+","+k);
                                    this.mine.getListMiners().get(i).setWorkPlace(this.mine.getMatrix().get(j).get(k));
                                }
                                else if (r.intersects(new Rectangle(this.mine.getListMiners().get(i).getX(), this.mine.getListMiners().get(i).getY(), this.mine.getListMiners().get(i).getWidth(), this.mine.getListMiners().get(i).getHeight() + 4)) && this.mine.getListMiners().get(i).getDirection() == 3)
                                {
                                    this.mine.getListMiners().get(i).setState("trabajo");
                                    this.mine.getListMiners().get(i).setWorkLocate(j+","+k);
                                    this.mine.getListMiners().get(i).setWorkPlace(this.mine.getMatrix().get(j).get(k));
                                }
                                else if (r.intersects(new Rectangle(this.mine.getListMiners().get(i).getX() - 6, this.mine.getListMiners().get(i).getY(), this.mine.getListMiners().get(i).getWidth() + 6, this.mine.getListMiners().get(i).getHeight())) && this.mine.getListMiners().get(i).getDirection() == 4)
                                {
                                    this.mine.getListMiners().get(i).setState("trabajo");
                                    this.mine.getListMiners().get(i).setWorkLocate(j+","+k);
                                    this.mine.getListMiners().get(i).setWorkPlace(this.mine.getMatrix().get(j).get(k));
                                }
                                else if (r.intersects(new Rectangle(this.mine.getListMiners().get(i).getX(), this.mine.getListMiners().get(i).getY() - 6, this.mine.getListMiners().get(i).getWidth(), this.mine.getListMiners().get(i).getHeight() + 6)) && this.mine.getListMiners().get(i).getDirection() == 1)
                                {
                                    this.mine.getListMiners().get(i).setState("trabajo");
                                    this.mine.getListMiners().get(i).setWorkLocate(j+","+k);
                                    this.mine.getListMiners().get(i).setWorkPlace(this.mine.getMatrix().get(j).get(k));
                                }
                            }
                        }
                    }
            }
        }
    }

    private void minersMovement()
    {
//        LinkedList<Integer> roads = new LinkedList<>();
//        LinkedList<Integer> deposits = new LinkedList<>();
        if(!this.mine.getListMiners().isEmpty())
        {
            boolean flagCollision = false;
            for (int i = 0; i < this.mine.getListMiners().size(); i++)
            {
                if (!this.mine.getListMiners().get(i).isFollowRoute())
                {
                    LinkedList<Integer> roads = new LinkedList<>();
                    LinkedList<Integer> deposits = new LinkedList<>();
                    flagCollision=false;
                    for (int j = 0; j < this.mine.getMatrix().size(); j++)
                    {
                        for (int k = 0; k < this.mine.getMatrix().get(j).size(); k++)
                        {
                            if (this.mine.getMatrix().get(j).get(k).getObject() instanceof Road)
                            {
                                Road road = (Road) this.mine.getMatrix().get(j).get(k).getObject();
                                Rectangle r = new Rectangle((road.getX() + (road.getWidth() / 2)) - 4, (road.getY() + (road.getHeight() / 2)) - 4, 10, 10);
                                if ("".equals(this.mine.getListMiners().get(i).getPreviousCollision()) || !this.mine.getListMiners().get(i).getPreviousCollision().equalsIgnoreCase(j + "," + k))
                                {
                                    if (r.intersects(new Rectangle(this.mine.getListMiners().get(i).getX() + (this.mine.getListMiners().get(i).getWidth() / 2), this.mine.getListMiners().get(i).getY() + (this.mine.getListMiners().get(i).getWidth() / 2), 5, 5)) && this.mine.getListMiners().get(i).getDirection() == 2)
                                    {
                                        roads = roadOptions(j, k, 1);
                                        deposits = depositOptions(j, k, 1);
                                        this.mine.getListMiners().get(i).setPreviousCollision(j + "," + k);
                                        flagCollision = true;
                                    }
                                    else if (r.intersects(new Rectangle(this.mine.getListMiners().get(i).getX() + (this.mine.getListMiners().get(i).getWidth() / 2), this.mine.getListMiners().get(i).getY() + (this.mine.getListMiners().get(i).getWidth() / 2), 5, 5)) && this.mine.getListMiners().get(i).getDirection() == 3)
                                    {
                                        roads = roadOptions(j, k, 2);
                                        deposits = depositOptions(j, k, 2);
                                        this.mine.getListMiners().get(i).setPreviousCollision(j + "," + k);
                                        flagCollision = true;
                                    }
                                    else if (r.intersects(new Rectangle(this.mine.getListMiners().get(i).getX() + (this.mine.getListMiners().get(i).getWidth() / 2), this.mine.getListMiners().get(i).getY() + (this.mine.getListMiners().get(i).getWidth() / 2), 5, 5)) && this.mine.getListMiners().get(i).getDirection() == 4)
                                    {
                                        roads = roadOptions(j, k, 3);
                                        deposits = depositOptions(j, k, 3);
                                        this.mine.getListMiners().get(i).setPreviousCollision(j + "," + k);
                                        flagCollision = true;
                                    }
                                     else if (r.intersects(new Rectangle(this.mine.getListMiners().get(i).getX() + (this.mine.getListMiners().get(i).getWidth() / 2), this.mine.getListMiners().get(i).getY() + (this.mine.getListMiners().get(i).getWidth() / 2), 5, 5)) && this.mine.getListMiners().get(i).getDirection() == 1)
                                    {
                                        roads = roadOptions(j, k, 4);
                                        deposits = depositOptions(j, k, 4);
                                        this.mine.getListMiners().get(i).setPreviousCollision(j + "," + k);
                                        flagCollision = true;
                                    }
                                }
                            }
                        }
                    }
                    if (flagCollision)
                        if (!roads.isEmpty() && deposits.isEmpty())
                        {
                            int position = (int) (Math.random() * (roads.size())) + 0;
                            this.mine.getListMiners().get(i).changeDirection(roads.get(position));
                        }
                        else if (roads.isEmpty() && !deposits.isEmpty())
                        {
                            int position = (int) (Math.random() * (deposits.size())) + 0;
                            this.mine.getListMiners().get(i).changeDirection(deposits.get(position));
                        }
                        else if (!roads.isEmpty() && !deposits.isEmpty())
                        {
                            int select = (int) (Math.random() * (1)) + 0;
                            if (select == 0)
                            {
                                int position = (int) (Math.random() * (roads.size())) + 0;
                                this.mine.getListMiners().get(i).changeDirection(roads.get(position));
                            }
                            else
                            {
                                int position = (int) (Math.random() * (deposits.size())) + 0;
                                this.mine.getListMiners().get(i).changeDirection(deposits.get(position));
                            }
                        }
                        else if (this.mine.getListMiners().get(i).getDirection() == 2)
                            this.mine.getListMiners().get(i).changeDirection(4);
                        else if (this.mine.getListMiners().get(i).getDirection() == 3)
                            this.mine.getListMiners().get(i).changeDirection(1);
                        else if (this.mine.getListMiners().get(i).getDirection() == 1)
                            this.mine.getListMiners().get(i).changeDirection(3);
                        else if (this.mine.getListMiners().get(i).getDirection() == 4)
                            this.mine.getListMiners().get(i).changeDirection(2);
                }
            }
        }
    }

    private LinkedList<Integer> depositOptions(int i, int j, int search)
    {
        LinkedList<Integer> result = new LinkedList<>();
        switch (search)
        {
            case 1:
                if ((i - 1) >= 0)
                    if (this.mine.getMatrix().get(i - 1).get(j).getObject() instanceof Deposit)
                        result.add(1);
                if ((i + 1) < this.mine.getMatrix().size())
                    if (this.mine.getMatrix().get(i + 1).get(j).getObject() instanceof Deposit)
                        result.add(3);
                if ((j + 1) < this.mine.getMatrix().get(i).size())
                    if (this.mine.getMatrix().get(i).get(j + 1).getObject() instanceof Deposit)
                        result.add(2);
                break;
            case 2:
                if ((j - 1) >= 0)
                    if (this.mine.getMatrix().get(i).get(j - 1).getObject() instanceof Deposit)
                        result.add(4);
                if ((j + 1) < this.mine.getMatrix().get(i).size())
                    if (this.mine.getMatrix().get(i).get(j + 1).getObject() instanceof Deposit)
                        result.add(2);
                if ((i + 1) < this.mine.getMatrix().size())
                    if (this.mine.getMatrix().get(i + 1).get(j).getObject() instanceof Deposit)
                        result.add(3);
                break;
            case 3:
                if ((i - 1) >= 0)
                    if (this.mine.getMatrix().get(i - 1).get(j).getObject() instanceof Deposit)
                        result.add(1);
                if ((i + 1) < this.mine.getMatrix().size())
                    if (this.mine.getMatrix().get(i + 1).get(j).getObject() instanceof Deposit)
                        result.add(3);
                if ((j - 1) >= 0)
                    if (this.mine.getMatrix().get(i).get(j - 1).getObject() instanceof Deposit)
                        result.add(4);
                break;
            case 4:
                if ((j - 1) >= 0)
                    if (this.mine.getMatrix().get(i).get(j - 1).getObject() instanceof Deposit)
                        result.add(4);
                if ((j + 1) < this.mine.getMatrix().get(i).size())
                    if (this.mine.getMatrix().get(i).get(j + 1).getObject() instanceof Deposit)
                        result.add(2);
                if ((i - 1) >= 0)
                    if (this.mine.getMatrix().get(i - 1).get(j).getObject() instanceof Deposit)
                        result.add(1);
                break;
        }
        return result;
    }
    
    private void clean()
    {
        for (int i = 0; i < this.mine.getListMiners().size(); i++) 
        {
            if(!this.mine.getListMiners().get(i).isMovement())
            {
                this.mine.getListMiners().remove(i);
            }
        }
    }
    
    private LinkedList<Integer> roadOptions(int i, int j, int search)
    {
        LinkedList<Integer> result = new LinkedList<>();
        switch (search)
        {
            case 1:
                if ((i - 1) >= 0)
                    if (this.mine.getMatrix().get(i - 1).get(j).getObject() instanceof Road)
                        result.add(1);
                if ((i + 1) < this.mine.getMatrix().size())
                    if (this.mine.getMatrix().get(i + 1).get(j).getObject() instanceof Road)
                        result.add(3);
                if ((j + 1) < this.mine.getMatrix().get(i).size())
                    if (this.mine.getMatrix().get(i).get(j + 1).getObject() instanceof Road)
                        result.add(2);
                break;
            case 2:
                if ((j - 1) >= 0)
                    if (this.mine.getMatrix().get(i).get(j - 1).getObject() instanceof Road)
                        result.add(4);
                if ((j + 1) < this.mine.getMatrix().get(i).size())
                    if (this.mine.getMatrix().get(i).get(j + 1).getObject() instanceof Road)
                        result.add(2);
                if ((i + 1) < this.mine.getMatrix().size())
                    if (this.mine.getMatrix().get(i + 1).get(j).getObject() instanceof Road)
                        result.add(3);
                break;
            case 3:
                if ((i - 1) >= 0)
                    if (this.mine.getMatrix().get(i - 1).get(j).getObject() instanceof Road)
                        result.add(1);
                if ((i + 1) < this.mine.getMatrix().size())
                    if (this.mine.getMatrix().get(i + 1).get(j).getObject() instanceof Road)
                        result.add(3);
                if ((j - 1) >= 0)
                    if (this.mine.getMatrix().get(i).get(j - 1).getObject() instanceof Road)
                        result.add(4);
                break;
            case 4:
                if ((j - 1) >= 0)
                    if (this.mine.getMatrix().get(i).get(j - 1).getObject() instanceof Road)
                        result.add(4);
                if ((j + 1) < this.mine.getMatrix().get(i).size())
                    if (this.mine.getMatrix().get(i).get(j + 1).getObject() instanceof Road)
                        result.add(2);
                if ((i - 1) >= 0)
                    if (this.mine.getMatrix().get(i - 1).get(j).getObject() instanceof Road)
                        result.add(1);
                break;
        }
        return result;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
      
        for (int i = 0; i < this.mine.getMatrix().size(); i++)
        {
            for (int j = 0; j < this.mine.getMatrix().get(i).size(); j++)
            {
                if (this.mine.getMatrix().get(i).get(j).getObject() instanceof Wall)
                {
                    Wall p = (Wall) this.mine.getMatrix().get(i).get(j).getObject();
                    g.setColor(Color.black);
                    g.fillRect(p.getX(), p.getY(), p.getWidth(), p.getHeight());
                    //g.drawImage(p.getImage().getImage(), p.getX(), p.getY(), p.getWidth(), p.getHeight(), this);
                }
                else if (this.mine.getMatrix().get(i).get(j).getObject() instanceof Road)
                {
                    Road road = (Road) this.mine.getMatrix().get(i).get(j).getObject();
                    g.drawImage(road.getImage().getImage(), road.getX(), road.getY(), road.getWidth(), road.getHeight(), this);
                    //g.drawRect((road.getX()+(road.getWidth()/2))-4, (road.getY()+(road.getHeight()/2))-4, 10, 10);
                }
                else if (this.mine.getMatrix().get(i).get(j).getObject() instanceof Deposit)
                {
                    Deposit deposit = (Deposit) this.mine.getMatrix().get(i).get(j).getObject();
                    g.drawImage(deposit.getImage().getImage(), deposit.getX(), deposit.getY(), deposit.getWidth(), deposit.getHeight(), this);
                }
            }
        }
        if (this.mine.getListMiners() != null)
            for (int i = 0; i < this.mine.getListMiners().size(); i++)
            {
                g.drawImage(this.mine.getListMiners().get(i).getImageBox().getImage(), this.mine.getListMiners().get(i).getxBox(), this.mine.getListMiners().get(i).getyBox(), this.mine.getListMiners().get(i).getWidthBox(), this.mine.getListMiners().get(i).getHeightBox(), this);
                g.drawString(this.mine.getListMiners().get(i).getNombre(), this.mine.getListMiners().get(i).getxBox() + this.mine.getListMiners().get(i).getWidth() / 4, this.mine.getListMiners().get(i).getyBox() + 20);
                g.drawImage(this.mine.getListMiners().get(i).getImage().getImage(), this.mine.getListMiners().get(i).getX(), this.mine.getListMiners().get(i).getY(), this.mine.getListMiners().get(i).getWidth(), this.mine.getListMiners().get(i).getHeight(), this);
                //g.drawRect(this.mine.getListMiners().get(i).getX()+(this.mine.getListMiners().get(i).getWidth()/2), this.mine.getListMiners().get(i).getY()+(this.mine.getListMiners().get(i).getWidth()/2), 5, 5);
            }
        repaint();
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

        setBackground(new java.awt.Color(0, 0, 0));
        addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_formMouseClicked
    {//GEN-HEADEREND:event_formMouseClicked
        for (int i = 0; i < this.mine.getMatrix().size(); i++)
        {
            for (int j = 0; j < this.mine.getMatrix().get(i).size(); j++)
            {
                if (this.mine.getMatrix().get(i).get(j).getObject() instanceof Deposit)
                {
                    Deposit d = (Deposit) this.mine.getMatrix().get(i).get(j).getObject();
                    Rectangle r = new Rectangle(d.getX(), d.getY(), d.getWidth(), d.getHeight());
                    if (r.intersects(new Rectangle(evt.getX(), evt.getY(), 1, 1)))
                        JOptionPane.showMessageDialog(null, d.getAmount() + "\n");
                }
            }
        }
    }//GEN-LAST:event_formMouseClicked

    /**
     * @return the mine
     */
    public Mine getMine()
    {
        return mine;
    }

    /**
     * @param mine the mine to set
     */
    public void setMine(Mine mine)
    {
        this.mine = mine;
    }

    @Override
    public void run() {
        while(true)
        {
            clean();
            minersFollowRoute();
            minersMovement();
            minersWork();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
