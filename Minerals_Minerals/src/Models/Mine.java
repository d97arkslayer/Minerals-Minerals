/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darkd
 */
public class Mine implements  Runnable
{

    private int id;
    private String metal;
    private double amount;
    private LinkedList<LinkedList<SectionMap>> matrix;
    private LinkedList<Miner> listMiners;
    private Graph graph;
    private int amountOfDeposits;
    private int maxMiners;
    private double produceMineral;
    private double timeToExtract;
    private String unitTimeToExtract;
    private double speed;
    private String unitSpeed;
    private double amountForDeposit;
    private String unitAmount;
    private LinkedList<Long> dikjstraTime;
    private int asignedminers;
    private LinkedList<Integer> totalGain;
    private double capacityCharge;
    private double  collectQuantity;
    private double earnigs;
    AdministrarNombresMineros Nombre=new AdministrarNombresMineros();
    private int asignedMinersComodin;

    public Mine()
    {
        this.matrix = new LinkedList<>();
    }

    public Mine(int id, String metal, double amount)
    {
        this.id = id;
        this.amount = amount;
        this.capacityCharge=0;
        this.metal = metal;
        this.matrix = new LinkedList<>();
        this.dikjstraTime=new LinkedList<>();
        this.listMiners = new LinkedList<>();
        this.amountOfDeposits = 0;
        this.graph = new Graph();
        this.totalGain=new LinkedList<>();
        this.loadDeposits();
    }
    
    private void loadDeposits()
    {
        for (int i = 0; i < this.matrix.size(); i++) 
        {
            for (int j = 0; j < this.matrix.get(i).size(); j++) 
            {
                if(this.matrix.get(i).get(j).getObject() instanceof Deposit)
                {
                    Deposit deposit=(Deposit)this.matrix.get(i).get(j).getObject();
                    deposit.setAmount(this.amount);
                    this.matrix.get(i).get(j).setObject(deposit);
                }
            }
        }
    }

    public void calculateGain()
    {
        if(this.asignedminers<this.amountOfDeposits)
        {
            this.getTotalGain().add((this.asignedminers*800));
        }
        else if(this.asignedminers==this.amountOfDeposits)
        {
            this.getTotalGain().add((this.asignedminers*950));
        }
        else
        {
            this.getTotalGain().add((this.amountOfDeposits*250)-((this.asignedminers-this.amountOfDeposits)*120));
        }
    }
    public void route(String end, int miner)
    {
        String start = "";
        for (int i = 0; i < this.matrix.size(); i++)
        {
            for (int j = 0; j < this.matrix.get(i).size(); j++)
            {
                if (this.matrix.get(i).get(j).getObject() instanceof Road)
                {
                    Road road = (Road) this.matrix.get(i).get(j).getObject();
                    if (road.isEntry())
                        start = i + "," + j;
                }
            }
        }
        LinkedList<String> result = this.graph.getRoute(start, end);
        this.listMiners.get(miner).setFollowRoute(true);
        this.listMiners.get(miner).setRoute(result);

        System.out.println(result);
        this.listMiners.get(miner).changeDirection(newDirection(miner));
        this.listMiners.get(miner).setState("mover");
    }

    public int newDirection(int miner)
    {
        int i = 0;
        if (this.listMiners.get(miner).getRoute().size() != 1)
        {
            String[] node1 = this.listMiners.get(miner).getRoute().get(i).split(",");

            String[] node2 = this.listMiners.get(miner).getRoute().get(i + 1).split(",");
            if (Integer.parseInt(node1[0]) > Integer.parseInt(node2[0]) && Integer.parseInt(node1[1]) == Integer.parseInt(node2[1]))
            {
                this.listMiners.get(miner).getRoute().removeFirst();
                return 1;
            }
            else if (Integer.parseInt(node1[1]) < Integer.parseInt(node2[1]) && Integer.parseInt(node1[0]) == Integer.parseInt(node2[0]))
            {
                this.listMiners.get(miner).getRoute().removeFirst();
                return 2;
            }
            else if (Integer.parseInt(node1[0]) < Integer.parseInt(node2[0]) && Integer.parseInt(node1[1]) == Integer.parseInt(node2[1]))
            {
                this.listMiners.get(miner).getRoute().removeFirst();
                return 3;
            }
            else if (Integer.parseInt(node1[1]) > Integer.parseInt(node2[1]) && Integer.parseInt(node1[0]) == Integer.parseInt(node2[0]))
            {
                this.listMiners.get(miner).getRoute().removeFirst();
                return 4;
            }
        }
        else
            return 0;
        return 0;
    }

    public void dijkstra()
    {
        long startTime = System.currentTimeMillis();
        String nodeName = "";
        for (int i = 0; i < this.matrix.size(); i++)
        {
            for (int j = 0; j < this.matrix.get(i).size(); j++)
            {
                if (this.matrix.get(i).get(j).getObject() instanceof Road)
                {
                    Road r = (Road) this.matrix.get(i).get(j).getObject();
                    if (r.isEntry())
                        nodeName = i + "," + j;
                }
            }
        }
        this.graph.calculateDijkstra(nodeName);
        this.dikjstraTime.add(System.currentTimeMillis() - startTime); 
        System.out.println(this.dikjstraTime.getLast());
    }
    
    public void createMiners(LinkedList<Thread> listThread)
    {
        boolean flag=false;
        int i=0;
        int x=0;
        int y=0;
        int direction=0;
        while(!flag)
        {
            for (int j = 0; j < this.matrix.get(i).size(); j++) 
            {
                if(this.matrix.get(i).get(j).getObject() instanceof Road)
                {
                    Road road=(Road) this.matrix.get(i).get(j).getObject();
                    if(road.isEntry())
                    {
                        flag=true;
                        switch(road.getLocationEntry())
                        {
                            case 1:
                                x=road.getX();
                                y=road.getY();
                                direction=2;
                                break;
                            case 2:
                                x=road.getX();
                                y=road.getY();
                                direction=3;
                                break;
                            case 3:
                                x=road.getX();
                                y=road.getY();
                                direction=4;
                                break;
                            case 4:
                                x=road.getX();
                                y=road.getY();
                                direction=1;
                                break;
                        }
                    }
                }
            }
            i++;
        }
        for (int j = 1; j <= this.asignedminers-this.asignedMinersComodin; j++)
        {
            this.listMiners.add(new Miner(x, y, direction, Nombre.getNombreMinero()));
            this.listMiners.getLast().setMovement(true);
            listThread.add(new Thread(this.listMiners.getLast()));
            listThread.getLast().start();
        }
        for (int j = 1; j <= this.asignedMinersComodin; j++)
        {
            this.listMiners.add(new Miner(x, y, direction, Nombre.getNombreMinero()));
            this.listMiners.getLast().setComodin(true);
            this.listMiners.getLast().setMovement(true);
            listThread.add(new Thread(this.listMiners.getLast()));
            listThread.getLast().start();
        }
    }
    
    
    public void createGraph()
    {
        this.graph=new Graph();
        LinkedList<String> listNodes = getNameGraphNodes();
        
        listNodes.forEach((name) ->
        {
            this.graph.addNode(name);
        });
        for (int i = 0; i < this.graph.getListNodes().size(); i++)
        {
            String aux[]=this.graph.getListNodes().get(i).getName().split(",");
            if(this.matrix.get(Integer.parseInt(aux[0])).get(Integer.parseInt(aux[1])).getObject() instanceof  Road)
            {
                LinkedHashMap<String, Integer> edges = getAdjacentNodes(this.graph.getListNodes().get(i).getName());
                for (String name : edges.keySet())
                {
                    this.graph.getListEdges().get(i).add(new GraphEdge(new GraphNode(name), edges.get(name)));
                }
            }
        }
        printGraph();
    }

    private LinkedHashMap<String, Integer> getAdjacentNodes(String node)
    {
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        String[] position = node.split(",");
        for (int i = 1; i <= 4; i++)
        {
            if (i == 1)
            {
                String name = getAdjacentNodeName(Integer.parseInt(position[0]), Integer.parseInt(position[1]), i);
                if (!name.equals(""))
                {
                    int value = getEdgeValue(Integer.parseInt(position[0]), Integer.parseInt(position[1]), i, name, 0);
                    result.put(name, value);
                }
            }
            if (i == 2)
            {
                String name = getAdjacentNodeName(Integer.parseInt(position[0]), Integer.parseInt(position[1]), i);
                if (!name.equals(""))
                {
                    int value = getEdgeValue(Integer.parseInt(position[0]), Integer.parseInt(position[1]), i, name, 0);
                    result.put(name, value);
                }
            }
            if (i == 3)
            {
                String name = getAdjacentNodeName(Integer.parseInt(position[0]), Integer.parseInt(position[1]), i);
                if (!name.equals(""))
                {
                    int value = getEdgeValue(Integer.parseInt(position[0]), Integer.parseInt(position[1]), i, name, 0);
                    result.put(name, value);
                }
            }
            if (i == 4)
            {
                String name = getAdjacentNodeName(Integer.parseInt(position[0]), Integer.parseInt(position[1]), i);
                if (!name.equals(""))
                {
                    int value = getEdgeValue(Integer.parseInt(position[0]), Integer.parseInt(position[1]), i, name, 0);
                    result.put(name, value);
                }
            }
        }
        return result;
    }

    private int getEdgeValue(int i, int j, int direction, String name, int cont)
    {
        if (direction == 1)
            if ((j - 1) >= 0)
                if (this.matrix.get(i).get(j - 1).getObject() instanceof Road || this.matrix.get(i).get(j - 1).getObject() instanceof Deposit)
                    if (name.equalsIgnoreCase(i + "," + (j - 1)))
                        return cont;
                    else
                    {
                        cont++;
                        return getEdgeValue(i, j - 1, direction, name, cont);
                    }
                else
                    return 0;
        if (direction == 2)
            if ((i - 1) >= 0)
                if (this.matrix.get(i - 1).get(j).getObject() instanceof Road || this.matrix.get(i - 1).get(j).getObject() instanceof Deposit)
                    if (name.equalsIgnoreCase((i - 1) + "," + j))
                        return cont;
                    else
                    {
                        cont++;
                        return getEdgeValue(i - 1, j, direction, name, cont);
                    }
                else
                    return 0;
        if (direction == 3)
            if ((j + 1) < this.matrix.get(i).size())
                if (this.matrix.get(i).get(j + 1).getObject() instanceof Road || this.matrix.get(i).get(j + 1).getObject() instanceof Deposit)
                    if (name.equalsIgnoreCase(i + "," + (j + 1)))
                        return cont;
                    else
                    {
                        cont++;
                        return getEdgeValue(i, j + 1, direction, name, cont);
                    }
                else
                    return 0;
        if (direction == 4)
            if ((i + 1) < this.matrix.size())
                if (this.matrix.get(i + 1).get(j).getObject() instanceof Road || this.matrix.get(i + 1).get(j).getObject() instanceof Deposit)
                    if (name.equalsIgnoreCase((i + 1) + "," + j))
                        return cont;
                    else
                    {
                        cont++;
                        return getEdgeValue(i + 1, j, direction, name, cont);
                    }
                else
                    return 0;
        return 0;
    }

    private String getAdjacentNodeName(int i, int j, int direction)
    {
        if (direction == 1)
            if ((j - 1) >= 0)
                if (this.matrix.get(i).get(j - 1).getObject() instanceof Road || this.matrix.get(i).get(j - 1).getObject() instanceof Deposit)
                {
                    boolean flag = false;
                    for (int k = 0; k < this.graph.getListNodes().size(); k++)
                    {
                        if (this.graph.getListNodes().get(k).getName().equals(i + "," + (j - 1)))
                            flag = true;
                    }
                    if (flag)
                        return i + "," + (j - 1);
                    else
                        return getAdjacentNodeName(i, j - 1, direction);
                }
                else
                    return "";
        if (direction == 2)
            if ((i - 1) >= 0)
                if (this.matrix.get(i - 1).get(j).getObject() instanceof Road || this.matrix.get(i - 1).get(j).getObject() instanceof Deposit)
                {
                    boolean flag = false;
                    for (int k = 0; k < this.graph.getListNodes().size(); k++)
                    {
                        if (this.graph.getListNodes().get(k).getName().equals((i - 1) + "," + j))
                            flag = true;
                    }
                    if (flag)
                        return (i - 1) + "," + j;
                    else
                        return getAdjacentNodeName(i - 1, j, direction);
                }
                else
                    return "";
        if (direction == 3)
            if ((j + 1) < this.matrix.get(i).size())
                if (this.matrix.get(i).get(j + 1).getObject() instanceof Road || this.matrix.get(i).get(j + 1).getObject() instanceof Deposit)
                {
                    boolean flag = false;
                    for (int k = 0; k < this.graph.getListNodes().size(); k++)
                    {
                        if (this.graph.getListNodes().get(k).getName().equals(i + "," + (j + 1)))
                            flag = true;
                    }
                    if (flag)
                        return i + "," + (j + 1);
                    else
                        return getAdjacentNodeName(i, j + 1, direction);
                }
                else
                    return "";
        if (direction == 4)
            if ((i + 1) < this.matrix.size())
                if (this.matrix.get(i + 1).get(j).getObject() instanceof Road || this.matrix.get(i + 1).get(j).getObject() instanceof Deposit)
                {
                    boolean flag = false;
                    for (int k = 0; k < this.graph.getListNodes().size(); k++)
                    {
                        if (this.graph.getListNodes().get(k).getName().equals((i + 1) + "," + j))
                            flag = true;
                    }
                    if (flag)
                        return (i + 1) + "," + j;
                    else
                        return getAdjacentNodeName(i + 1, j, direction);
                }
                else
                    return "";
        return "";
    }

    private LinkedList<String> getNameGraphNodes()
    {
        LinkedList<String> result = new LinkedList<>();
        for (int i = 0; i < this.matrix.size(); i++)
        {
            for (int j = 0; j < this.matrix.get(i).size(); j++)
            {
                if (this.matrix.get(i).get(j).getObject() instanceof Road)
                {
                    Road road = (Road) this.matrix.get(i).get(j).getObject();
                    if (road.isEntry())
                        result.add(i + "," + j);
                }
                else if (this.matrix.get(i).get(j).getObject() instanceof Deposit)
                    result.add(i + "," + j);
            }
        }
        for (int i = 0; i < result.size(); i++)
        {
            String[] position = result.get(i).split(",");
            if ((Integer.parseInt(position[1]) - 1) >= 0)
                if (this.matrix.get(Integer.parseInt(position[0])).get(Integer.parseInt(position[1]) - 1).getObject() instanceof Road)
                {
                    String aux = getNameNode(Integer.parseInt(position[0]), Integer.parseInt(position[1]) - 1, 1);
                    if (!aux.equals("") && !result.contains(aux))
                        result.add(aux);
                }
            if ((Integer.parseInt(position[0]) - 1) >= 0)
                if (this.matrix.get(Integer.parseInt(position[0]) - 1).get(Integer.parseInt(position[1])).getObject() instanceof Road)
                {
                    String aux = getNameNode(Integer.parseInt(position[0]) - 1, Integer.parseInt(position[1]), 2);
                    if (!aux.equals("") && !result.contains(aux))
                        result.add(aux);
                }
            if ((Integer.parseInt(position[1]) + 1) < this.matrix.get(Integer.parseInt(position[0])).size())
                if (this.matrix.get(Integer.parseInt(position[0])).get(Integer.parseInt(position[1]) + 1).getObject() instanceof Road)
                {
                    String aux = getNameNode(Integer.parseInt(position[0]), Integer.parseInt(position[1]) + 1, 3);
                    if (!aux.equals("") && !result.contains(aux))
                        result.add(aux);
                }
            if ((Integer.parseInt(position[0]) + 1) < this.matrix.size())
                if (this.matrix.get(Integer.parseInt(position[0]) + 1).get(Integer.parseInt(position[1])).getObject() instanceof Road)
                {
                    String aux = getNameNode(Integer.parseInt(position[0]) + 1, Integer.parseInt(position[1]), 4);
                    if (!aux.equals("") && !result.contains(aux))
                        result.add(aux);
                }
        }
        return result;
    }

    private String getNameNode(int i, int j, int direction)
    {
        String result = "";
        if (direction == 1)
        {
            if ((i - 1) >= 0)
                if (this.matrix.get(i - 1).get(j).getObject() instanceof Road)
                    result = i + "," + j;
            if ((i + 1) < this.matrix.size())
                if (this.matrix.get(i + 1).get(j).getObject() instanceof Road)
                    result = i + "," + j;
            if (result.equals(""))
                if ((j - 1) >= 0)
                    if (this.matrix.get(i).get(j - 1).getObject() instanceof Road)
                        return getNameNode(i, j - 1, 1);
                    else
                        return "";
                else
                    return "";
            else
                return result;
        }
        if (direction == 2)
        {
            if ((j - 1) >= 0)
                if (this.matrix.get(i).get(j - 1).getObject() instanceof Road)
                    result = i + "," + j;
            if ((j + 1) < this.matrix.size())
                if (this.matrix.get(i).get(j + 1).getObject() instanceof Road)
                    result = i + "," + j;
            if (result.equals(""))
                if ((i - 1) >= 0)
                    if (this.matrix.get(i - 1).get(j).getObject() instanceof Road)
                        return getNameNode(i - 1, j, 2);
                    else
                        return "";
                else
                    return "";
            else
                return result;
        }
        if (direction == 3)
        {
            if ((i - 1) >= 0)
                if (this.matrix.get(i - 1).get(j).getObject() instanceof Road)
                    result = i + "," + j;
            if ((i + 1) < this.matrix.size())
                if (this.matrix.get(i + 1).get(j).getObject() instanceof Road)
                    result = i + "," + j;
            if (result.equals(""))
                if ((j + 1) < this.matrix.get(i).size())
                    if (this.matrix.get(i).get(j + 1).getObject() instanceof Road)
                        return getNameNode(i, j + 1, 3);
                    else
                        return "";
                else
                    return "";
            else
                return result;
        }
        if (direction == 4)
        {
            if ((j - 1) >= 0)
                if (this.matrix.get(i).get(j - 1).getObject() instanceof Road)
                    result = i + "," + j;
            if ((j + 1) < this.matrix.get(i).size())
                if (this.matrix.get(i).get(j + 1).getObject() instanceof Road)
                    result = i + "," + j;
            if (result.equals(""))
                if ((i + 1) < this.matrix.size())
                    if (this.matrix.get(i + 1).get(j).getObject() instanceof Road)
                        return getNameNode(i + 1, j, 4);
                    else
                        return "";
                else
                    return "";
            else
                return result;
        }
        return "";
    }

    private void printGraph()
    {
        for (int i = 0; i < this.graph.getListNodes().size(); i++)
        {
            System.out.println(this.graph.getListNodes().get(i).getName());
            for (int j = 0; j < this.graph.getListEdges().get(i).size(); j++)
            {
                System.out.println(this.graph.getListEdges().get(i).get(j).getEndNode().getName() + "|" + this.graph.getListEdges().get(i).get(j).getValue());
            }
        }
    }
    
    public void stopWorking(String workLocate)
    {
        for (Miner miner : this.listMiners) 
        {
            if(miner.getWorkLocate().equalsIgnoreCase(workLocate))
            {
                miner.setWorkLocate("");
                miner.setState("mover");
                miner.setWorkPlace(null);
                miner.setCountAnimation(0);
            }
        }
    }
    
    private void cleanMiners()
    {
        for(Miner miners:this.listMiners)
        {
            miners.setEarnings(this.earnigs*miners.getCurrentCapacity());
        }
        this.listMiners.clear();
    }
    
    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the metal
     */
    public String getMetal()
    {
        return metal;
    }

    /**
     * @param metal the metal to set
     */
    public void setMetal(String metal)
    {
        this.metal = metal;
    }

    /**
     * @return the matrix
     */
    public LinkedList<LinkedList<SectionMap>> getMatrix()
    {
        return matrix;
    }

    /**
     * @param matrix the matrix to set
     */
    public void setMatrix(LinkedList<LinkedList<SectionMap>> matrix)
    {
        this.matrix = matrix;
    }

    /**
     * @return the listMiners
     */
    public LinkedList<Miner> getListMiners()
    {
        return listMiners;
    }

    /**
     * @param listMiners the listMiners to set
     */
    public void setListMiners(LinkedList<Miner> listMiners)
    {
        this.listMiners = listMiners;
    }

    /**
     * @return the graph
     */
    public Graph getGraph()
    {
        return graph;
    }

    /**
     * @param graph the graph to set
     */
    public void setGraph(Graph graph)
    {
        this.graph = graph;
    }

    /**
     * @return the amount
     */
    public double getAmount()
    {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    /**
     * @return the amountOfDeposits
     */
    public int getAmountOfDeposits()
    {
        return amountOfDeposits;
    }

    /**
     * @param amountOfDeposits the amountOfDeposits to set
     */
    public void setAmountOfDeposits(int amountOfDeposits)
    {
        this.amountOfDeposits = amountOfDeposits;
    }

    /**
     * @return the maxMiners
     */
    public int getMaxMiners()
    {
        return maxMiners;
    }

    /**
     * @param maxMiners the maxMiners to set
     */
    public void setMaxMiners(int maxMiners)
    {
        this.maxMiners = maxMiners;
    }

    /**
     * @return the produceMineral
     */
    public double getProduceMineral()
    {
        return produceMineral;
    }

    /**
     * @param produceMineral the produceMineral to set
     */
    public void setProduceMineral(double produceMineral)
    {
        this.produceMineral = produceMineral;
    }

    /**
     * @return the timeToExtract
     */
    public double getTimeToExtract()
    {
        return timeToExtract;
    }

    /**
     * @param timeToExtract the timeToExtract to set
     */
    public void setTimeToExtract(double timeToExtract)
    {
        this.timeToExtract = timeToExtract;
    }

    /**
     * @return the unitTimeToExtract
     */
    public String getUnitTimeToExtract()
    {
        return unitTimeToExtract;
    }

    /**
     * @param unitTimeToExtract the unitTimeToExtract to set
     */
    public void setUnitTimeToExtract(String unitTimeToExtract)
    {
        this.unitTimeToExtract = unitTimeToExtract;
    }

    /**
     * @return the speed
     */
    public double getSpeed()
    {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    /**
     * @return the unitSpeed
     */
    public String getUnitSpeed()
    {
        return unitSpeed;
    }

    /**
     * @param unitSpeed the unitSpeed to set
     */
    public void setUnitSpeed(String unitSpeed)
    {
        this.unitSpeed = unitSpeed;
    }

    /**
     * @return the amountForDeposit
     */
    public double getAmountForDeposit()
    {
        return amountForDeposit;
    }

    /**
     * @param amountForDeposit the amountForDeposit to set
     */
    public void setAmountForDeposit(double amountForDeposit)
    {
        this.amountForDeposit = amountForDeposit;
    }

    /**
     * @return the unitAmount
     */
    public String getUnitAmount()
    {
        return unitAmount;
    }

    /**
     * @param unitAmount the unitAmount to set
     */
    public void setUnitAmount(String unitAmount)
    {
        this.unitAmount = unitAmount;
    }

    /**
     * @return the dikjstraTime
     */
    public LinkedList<Long> getDikjstraTime() {
        return dikjstraTime;
    }

    /**
     * @param dikjstraTime the dikjstraTime to set
     */
    public void setDikjstraTime(LinkedList<Long> dikjstraTime) {
        this.dikjstraTime = dikjstraTime;
    }

    /**
     * @return the asignedminers
     */
    public int getAsignedminers() {
        return asignedminers;
    }

    /**
     * @param asignedminers the asignedminers to set
     */
    public void setAsignedminers(int asignedminers) {
        this.asignedminers = asignedminers;
    }

    /**
     * @return the totalGain
     */
    public LinkedList<Integer> getTotalGain() {
        return totalGain;
    }

    /**
     * @param totalGain the totalGain to set
     */
    public void setTotalGain(LinkedList<Integer> totalGain) {
        this.totalGain = totalGain;
    }

    /**
     * @return the capacityCharge
     */
    public double getCapacityCharge() {
        return capacityCharge;
    }

    /**
     * @param capacityCharge the capacityCharge to set
     */
    public void setCapacityCharge(double capacityCharge) {
        this.capacityCharge = capacityCharge;
    }

    /**
     * @return the collectQuantity
     */
    public double getCollectQuantity() {
        return collectQuantity;
    }

    /**
     * @param collectQuantity the collectQuantity to set
     */
    public void setCollectQuantity(double collectQuantity) {
        this.collectQuantity = collectQuantity;
    }
   
    @Override
    public void run() {
        while(true)
        {
            for (int i = 0; i < this.listMiners.size(); i++) {
                if(this.listMiners.get(i).getState().equalsIgnoreCase("trabajo"))
                {
                    Deposit deposit=(Deposit) this.listMiners.get(i).getWorkPlace().getObject();
                    deposit.setAmount(deposit.getAmount()-this.collectQuantity);
                    this.listMiners.get(i).setCurrentCapacity(this.listMiners.get(i).getCurrentCapacity()+this.collectQuantity);
                    if(this.listMiners.get(i).getCurrentCapacity()>=this.capacityCharge)
                    {
                        this.listMiners.get(i).setCountAnimation(0);
                        this.listMiners.get(i).setCurrentCapacity(0);
                        this.route(this.listMiners.get(i).getWorkLocate(), i);
                        this.listMiners.get(i).setEarnings(this.listMiners.get(i).getEarnings()*this.listMiners.get(i).getCurrentCapacity());
                    }
                    if(deposit.getAmount()<=0)
                    {
                        Road road=new Road(deposit.getX(), deposit.getY(), false, 0);
                        this.listMiners.get(i).getWorkPlace().setObject(road);
                        this.stopWorking(this.listMiners.get(i).getWorkLocate());
                        this.listMiners.get(i).setWorkPlace(null);
                        this.listMiners.get(i).setWorkLocate("");
                        this.listMiners.get(i).setCountAnimation(0);
                        this.listMiners.get(i).setState("mover");
                        this.createGraph();
                        this.dijkstra();
                        this.amountOfDeposits--;
                    }
                    if(this.amountOfDeposits==0)
                    {
                        cleanMiners();
                    }
                }
            }
            try {
                long time=0;
                if(this.unitTimeToExtract.equalsIgnoreCase("segundos"))
                {
                    time=(long)this.timeToExtract*1000;
                }
                Thread.sleep(time);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return the earnigs
     */
    public double getEarnigs() {
        return earnigs;
    }

    /**
     * @param earnigs the earnigs to set
     */
    public void setEarnigs(double earnigs) {
        this.earnigs = earnigs;
    }

    /**
     * @return the asignedMinersComodin
     */
    public int getAsignedMinersComodin()
    {
        return asignedMinersComodin;
    }

    /**
     * @param asignedMinersComodin the asignedMinersComodin to set
     */
    public void setAsignedMinersComodin(int asignedMinersComodin)
    {
        this.asignedMinersComodin = asignedMinersComodin;
    }

    

    /**
     * @return the matrix
     */
}


