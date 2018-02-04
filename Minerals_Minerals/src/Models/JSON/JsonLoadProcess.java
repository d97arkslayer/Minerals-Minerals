/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.JSON;

import Models.Deposit;
import Models.Mine;
import Models.Road;
import Models.SectionMap;
import Models.Wall;
import com.google.gson.Gson;
import java.util.LinkedList;

/**
 *
 * @author darkd
 */
public class JsonLoadProcess
{

    private LinkedList<Mine> mines;
    Gson gson;
    JsonModel model;

    public JsonLoadProcess(String text)
    {
        this.mines = new LinkedList<>();
        this.gson = new Gson();
        this.model = this.gson.fromJson(text, JsonModel.class);

    }

    public JsonLoadProcess(LinkedList<Mine> mines)
    {
        this.mines = mines;
    }

    /**
     * @return the mines
     */
    public LinkedList<Mine> getMines()
    {
        this.extractMines();
        return mines;
    }

    /**
     * @param mines the mines to set
     */
    public void setMines(LinkedList<Mine> mines)
    {
        this.mines = mines;
    }

    public InformationMineJson getInformationGeneralOfMine()
    {
        return this.model.getInfoMinas();
    }

    public MinersJson getMinersInfo()
    {
        return this.model.getInfoMineros();
    }

    public void extractMines()
    {
        int count = 0;
        for (MineJSON mine : this.model.getMinas())
        {

            Mine aux = new Mine(count, mine.getTipoMineral(), 0);
            count++;
            int amount = 0;
            aux.setMaxMiners(mine.getCapacidadMineros());
            aux.setAmountForDeposit(mine.getCapacidadDeposito());
            aux.setUnitAmount(mine.getUnidadCapacidad());
            aux.setTimeToExtract(mine.getTiempoExtraccion());
            aux.setUnitTimeToExtract(mine.getUnidadTiempo());
            aux.setSpeed(mine.getVelocidadDesplazamiento());
            aux.setUnitSpeed(mine.getUnidadVelocidad());
            aux.setCollectQuantity(mine.getCantidadRecoleccion());
            LinkedList<SectionMap> L = new LinkedList<>();
            L.add(new SectionMap(new Wall(0, 0)));
            aux.getMatrix().add(L);
            int h = 0;
            while (h < mine.getAncho())
            {
                for (int i = 0; i < aux.getMatrix().size(); i++)
                {
                    int x, width, y;
                    Wall w = (Wall) aux.getMatrix().get(i).getLast().getObject();
                    x = w.getX();
                    width = w.getWidth();
                    y = w.getY();
                    aux.getMatrix().get(i).add(new SectionMap(new Wall(x + width, y)));
                }
                h++;
            }
            for (int i = 0; i < mine.getAlto() - 1; i++)
            {
                LinkedList<SectionMap> l = new LinkedList<>();
                int position = 0, width, height, y;
                Wall w = (Wall) aux.getMatrix().getLast().getFirst().getObject();
                width = w.getWidth();
                height = w.getHeight();
                y = w.getY();
                for (SectionMap first : aux.getMatrix().getFirst())
                {
                    l.add(new SectionMap(new Wall(position, y + height)));
                    position += width;
                }
                aux.getMatrix().add(l);
            }
            for (SectionJson section : mine.getSeccionesMina())
            {
                if (section.getTipo() == 'T')
                {
                    Wall a = (Wall) aux.getMatrix().get(section.getY()).get(section.getX()).getObject();
                    aux.getMatrix().get(section.getY()).get(section.getX()).setObject(new Road(a.getX(), a.getY(), false, 0));
                }
                else if (section.getTipo() == 'D')
                {
                    Wall a = (Wall) aux.getMatrix().get(section.getY()).get(section.getX()).getObject();
                    Deposit b = new Deposit(a.getX(), a.getY(), mine.getTipoMineral());
                    b.setAmount(mine.getCapacidadDeposito());
                    aux.getMatrix().get(section.getY()).get(section.getX()).setObject(b);
                    amount++;
                }
            }

            Road r = (Road) aux.getMatrix().get(mine.getEntradaMina().getY()).get(mine.getEntradaMina().getX()).getObject();
            r.setEntry(true);
            if (mine.getEntradaMina().getX() == 0)
                r.setLocationEntry(1);
            else if (mine.getEntradaMina().getY() == 0)
                r.setLocationEntry(2);
            else if (aux.getMatrix().size() - 1 == mine.getEntradaMina().getY())
                r.setLocationEntry(4);
            else
                r.setLocationEntry(3);

            aux.getMatrix().get(mine.getEntradaMina().getY()).get(mine.getEntradaMina().getX()).setObject(r);
            aux.setAmount(amount * aux.getAmountForDeposit());
            aux.setAmountOfDeposits(amount);
            for (int i = 0; i < aux.getMatrix().size(); i++)
            {
                for (int j = 0; j < aux.getMatrix().get(i).size(); j++)
                {
                    if (aux.getMatrix().get(i).get(j).getObject() instanceof Road)
                        this.giveImage(i, j, aux.getMatrix().size(), aux.getMatrix().get(i).size(), aux);
                }
            }
            this.mines.add(aux);

        }
    }

    public void giveImage(int f, int c, int limitF, int limitC, Mine mine)
    {
        boolean up = false;
        boolean down = false;
        boolean right = false;
        boolean left = false;
        if (c > 0)
            if ((mine.getMatrix().get(f).get(c - 1).getObject() instanceof Road) || (mine.getMatrix().get(f).get(c - 1).getObject() instanceof Deposit))
                left = true;
        if (c < limitC - 1)
            if ((mine.getMatrix().get(f).get(c + 1).getObject() instanceof Road) || (mine.getMatrix().get(f).get(c + 1).getObject() instanceof Deposit))
                right = true;
        if (f < limitF - 1)
            if ((mine.getMatrix().get(f + 1).get(c).getObject() instanceof Road) || (mine.getMatrix().get(f + 1).get(c).getObject() instanceof Deposit))
                down = true;
        if (f > 0)
            if ((mine.getMatrix().get(f - 1).get(c).getObject() instanceof Road) || (mine.getMatrix().get(f - 1).get(c).getObject() instanceof Deposit))
                up = true;

        if (left == true && right == true && down == false && up == false)
        {
            Road r = (Road) (mine.getMatrix().get(f).get(c).getObject());
            r.setImage(r.getImages()[1]);
        }
        else if (left == false && right == false && down == true && up == true)
        {
            Road r = (Road) (mine.getMatrix().get(f).get(c).getObject());
            r.setImage(r.getImages()[0]);
        }
        else if (left == true && right == false && down == false && up == true)
        {
            Road r = (Road) (mine.getMatrix().get(f).get(c).getObject());
            r.setImage(r.getImages()[2]);
        }
        else if (left == false && right == true && down == false && up == true)
        {
            Road r = (Road) (mine.getMatrix().get(f).get(c).getObject());
            r.setImage(r.getImages()[3]);
        }
        else if (left == false && right == true && down == true && up == false)
        {
            Road r = (Road) (mine.getMatrix().get(f).get(c).getObject());
            r.setImage(r.getImages()[4]);
        }
        else if (left == true && right == false && down == true && up == false)
        {
            Road r = (Road) (mine.getMatrix().get(f).get(c).getObject());
            r.setImage(r.getImages()[5]);
        }
        else if (left == true && right == true && down == true && up == true)
        {
            Road r = (Road) (mine.getMatrix().get(f).get(c).getObject());
            r.setImage(r.getImages()[6]);
        }
        else if (left == true && right == true && down == false && up == true)
        {
            Road r = (Road) (mine.getMatrix().get(f).get(c).getObject());
            r.setImage(r.getImages()[7]);
        }
        else if (left == false && right == true && down == true && up == true)
        {
            Road r = (Road) (mine.getMatrix().get(f).get(c).getObject());
            r.setImage(r.getImages()[8]);
        }
        else if (left == true && right == false && down == true && up == true)
        {
            Road r = (Road) (mine.getMatrix().get(f).get(c).getObject());
            r.setImage(r.getImages()[9]);
        }
        else if (left == true && right == true && down == true && up == false)
        {
            Road r = (Road) (mine.getMatrix().get(f).get(c).getObject());
            r.setImage(r.getImages()[10]);
        }

    }

}
