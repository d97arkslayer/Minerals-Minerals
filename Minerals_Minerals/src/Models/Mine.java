/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 *
 * @author darkd
 */
public class Mine
{

    private int id;
    private String metal;
    private LinkedList<LinkedList<SectionMap>> matrix;
    private LinkedList<Miner> listMiners;
    private Graph graph;

    public Mine()
    {
        this.matrix = new LinkedList<>();
    }

    public Mine(int id, String metal)
    {
        this.id = id;
        this.metal = metal;
        this.matrix = new LinkedList<>();
        this.listMiners=new LinkedList<>();
        this.graph=new Graph();
    }
    
    public void createGraph()
    {
        LinkedList<String> listNodes=getNameGraphNodes();
        for (String name : listNodes) 
        {
            this.graph.addNode(name);
        }
        for (int i = 0; i < this.graph.getListNodes().size(); i++) 
        {
            LinkedHashMap<String,Integer> edges=getAdjacentNodes(this.graph.getListNodes().get(i).getName());
            for (String name : edges.keySet()) 
            {
                this.graph.getListEdges().get(i).add(new GraphEdge(new GraphNode(name), edges.get(name)));
            }
        }
        printGraph();
    }
    
    private LinkedHashMap<String,Integer> getAdjacentNodes(String node)
    {
        LinkedHashMap<String,Integer> result=new LinkedHashMap<>();
        String[] position=node.split(",");
        for (int i = 1; i <= 4; i++) 
        {
            if(i==1)
            {
                String name=getAdjacentNodeName(Integer.parseInt(position[0]), Integer.parseInt(position[1]), i);
                if(!name.equals(""))
                {
                    int value=getEdgeValue(Integer.parseInt(position[0]), Integer.parseInt(position[1]), i, name, 0);
                    result.put(name, value);
                }
            }
            if(i==2)
            {
                String name=getAdjacentNodeName(Integer.parseInt(position[0]), Integer.parseInt(position[1]), i);
                if(!name.equals(""))
                {
                    int value=getEdgeValue(Integer.parseInt(position[0]), Integer.parseInt(position[1]), i, name, 0);
                    result.put(name, value);
                }
            }
            if(i==3)
            {
                String name=getAdjacentNodeName(Integer.parseInt(position[0]), Integer.parseInt(position[1]), i);
                if(!name.equals(""))
                {
                    int value=getEdgeValue(Integer.parseInt(position[0]), Integer.parseInt(position[1]), i, name, 0);
                    result.put(name, value);
                }
            }
            if(i==4)
            {
                String name=getAdjacentNodeName(Integer.parseInt(position[0]), Integer.parseInt(position[1]), i);
                if(!name.equals(""))
                {
                    int value=getEdgeValue(Integer.parseInt(position[0]), Integer.parseInt(position[1]), i, name, 0);
                    result.put(name, value);
                }
            }
        }
        return result;
    }
    
    private int getEdgeValue(int i,int j,int direction,String name,int cont)
    {
        if(direction==1)
        {
            if((j-1)>=0)
            {
                if(this.matrix.get(i).get(j-1).getObject() instanceof Road)
                {
                    if(name.equalsIgnoreCase(i+","+(j-1)))
                    {
                        return cont;
                    }else
                    {
                        cont++;
                        return getEdgeValue(i, j-1, direction, name, cont);
                    }
                }else
                {
                    return 0;
                }
                
            }
        }
        if(direction==2)
        {
            if((i-1)>=0)
            {
                if(this.matrix.get(i-1).get(j).getObject() instanceof Road)
                {
                    if(name.equalsIgnoreCase((i-1)+","+j))
                    {
                        return cont;
                    }else
                    {
                        cont++;
                        return getEdgeValue(i-1, j, direction, name, cont);
                    }
                }else
                {
                    return 0;
                }
                
            }
        }
        if(direction==3)
        {
            if((j+1)<this.matrix.get(i).size())
            {
                if(this.matrix.get(i).get(j+1).getObject() instanceof Road)
                {
                    if(name.equalsIgnoreCase(i+","+(j+1)))
                    {
                        return cont;
                    }else
                    {
                        cont++;
                        return getEdgeValue(i, j+1, direction, name, cont);
                    }
                }else
                {
                    return 0;
                }
                
            }
        }
         if(direction==4)
        {
            if((i+1)<this.matrix.size())
            {
                if(this.matrix.get(i+1).get(j).getObject() instanceof Road)
                {
                    if(name.equalsIgnoreCase((i+1)+","+j))
                    {
                        return cont;
                    }else
                    {
                        cont++;
                        return getEdgeValue(i+1, j, direction, name, cont);
                    }
                }else
                {
                    return 0;
                }
                
            }
        }
        return 0;
    }
    
    private String getAdjacentNodeName(int i,int j,int direction)
    {
        if(direction==1)
        {
            if((j-1)>=0)
            {
                if(this.matrix.get(i).get(j-1).getObject() instanceof Road)
                {
                    boolean flag=false;
                    for (int k = 0; k < this.graph.getListNodes().size(); k++) 
                    {
                         if(this.graph.getListNodes().get(k).getName().equals(i+","+(j-1)))
                         {
                             flag=true;
                         }
                    }
                    if(flag)
                    {
                        return i+","+(j-1);
                    }else
                    {
                        return getAdjacentNodeName(i, j-1, direction);
                    }
                }else
                {
                    return "";
                }
                
            }
        }
        if(direction==2)
        {
            if((i-1)>=0)
            {
                if(this.matrix.get(i-1).get(j).getObject() instanceof Road)
                {
                    boolean flag=false;
                    for (int k = 0; k < this.graph.getListNodes().size(); k++) 
                    {
                         if(this.graph.getListNodes().get(k).getName().equals((i-1)+","+j))
                         {
                             flag=true;
                         }
                    }
                    if(flag)
                    {
                        return (i-1)+","+j;
                    }else
                    {
                        return getAdjacentNodeName(i-1, j, direction);
                    }
                }else
                {
                    return "";
                }
                
            }
        }
        if(direction==3)
        {
            if((j+1)<this.matrix.get(i).size())
            {
                if(this.matrix.get(i).get(j+1).getObject() instanceof Road)
                {
                    boolean flag=false;
                    for (int k = 0; k < this.graph.getListNodes().size(); k++) 
                    {
                         if(this.graph.getListNodes().get(k).getName().equals(i+","+(j+1)))
                         {
                             flag=true;
                         }
                    }
                    if(flag)
                    {
                        return i+","+(j+1);
                    }else
                    {
                        return getAdjacentNodeName(i, j+1, direction);
                    }
                }else
                {
                    return "";
                }
                
            }
        }
        if(direction==4)
        {
            if((i+1)<this.matrix.size())
            {
                if(this.matrix.get(i+1).get(j).getObject() instanceof Road)
                {
                    boolean flag=false;
                    for (int k = 0; k < this.graph.getListNodes().size(); k++) 
                    {
                         if(this.graph.getListNodes().get(k).getName().equals((i+1)+","+j))
                         {
                             flag=true;
                         }
                    }
                    if(flag)
                    {
                        return (i+1)+","+j;
                    }else
                    {
                        return getAdjacentNodeName(i+1, j, direction);
                    }
                }else
                {
                    return "";
                }
                
            }
        }
        return "";
    }
    
    private LinkedList<String> getNameGraphNodes()
    {
        LinkedList<String> result=new LinkedList<>();
        for (int i = 0; i < this.matrix.size(); i++) 
        {
            for (int j = 0; j < this.matrix.get(i).size(); j++) {
                if(this.matrix.get(i).get(j).getObject() instanceof Road)
                {
                    Road road=(Road)this.matrix.get(i).get(j).getObject();
                    if(road.isEntry())
                    {
                        result.add(i+","+j);
                    }
                }else if(this.matrix.get(i).get(j).getObject() instanceof Deposit)
                {
                    result.add(i+","+j);
                }
            }
        }
        for (int i = 0; i < result.size(); i++) {
            String[] position=result.get(i).split(",");
            if((Integer.parseInt(position[1])-1)>=0)
            {
                if(this.matrix.get(Integer.parseInt(position[0])).get(Integer.parseInt(position[1])-1).getObject() instanceof  Road)
                {
                    String aux=getNameNode(Integer.parseInt(position[0]), Integer.parseInt(position[1])-1, 1);
                    if(!aux.equals("") && !result.contains(aux))
                    {
                        result.add(aux);
                    }
                }
            }
            if((Integer.parseInt(position[0])-1)>=0)
            {
                if(this.matrix.get(Integer.parseInt(position[0])-1).get(Integer.parseInt(position[1])).getObject() instanceof  Road)
                {
                    String aux=getNameNode(Integer.parseInt(position[0])-1, Integer.parseInt(position[1]), 2);
                    if(!aux.equals("")&&!result.contains(aux))
                    {
                        result.add(aux);
                    }
                }
            }
            if((Integer.parseInt(position[1])+1)<this.matrix.get(Integer.parseInt(position[0])).size())
            {
                if(this.matrix.get(Integer.parseInt(position[0])).get(Integer.parseInt(position[1])+1).getObject() instanceof  Road)
                {
                    String aux=getNameNode(Integer.parseInt(position[0]), Integer.parseInt(position[1])+1, 3);
                    if(!aux.equals("")&& !result.contains(aux))
                    {
                        result.add(aux);
                    }
                }
            }
            if((Integer.parseInt(position[0])+1)<this.matrix.size())
            {
                if(this.matrix.get(Integer.parseInt(position[0])+1).get(Integer.parseInt(position[1])).getObject() instanceof  Road)
                {
                    String aux=getNameNode(Integer.parseInt(position[0])+1, Integer.parseInt(position[1]), 4);
                    if(!aux.equals("")&&!result.contains(aux))
                    {
                        result.add(aux);
                    }
                }
            }
        }
        return result;
    }
    
    private String getNameNode(int i,int j,int direction)
    {
        String result="";
        if(direction==1)
        {
            if((i-1)>=0)
            {
                if(this.matrix.get(i-1).get(j).getObject() instanceof Road)
                {
                    result=i+","+j;
                }
            }
            if((i+1)<this.matrix.size()){
                if(this.matrix.get(i+1).get(j).getObject() instanceof Road)
                {
                    result=i+","+j;
                }
            }
            if(result.equals(""))
            {
                if((j-1)>=0)
                {
                    if(this.matrix.get(i).get(j-1).getObject() instanceof Road)
                    {
                        return getNameNode(i, j-1, 1);
                    }else
                    {
                        return "";
                    }
                }else
                {
                    return "";
                }
            }else
            {
                return result;
            }
        }
        if(direction==2)
        {
            if((j-1)>=0)
            {
                if(this.matrix.get(i).get(j-1).getObject() instanceof Road)
                {
                    result=i+","+j;
                }
            }
            if((j+1)<this.matrix.size()){
                if(this.matrix.get(i).get(j+1).getObject() instanceof Road)
                {
                    result=i+","+j;
                }
            }
            if(result.equals(""))
            {
                if((i-1)>=0)
                {
                    if(this.matrix.get(i-1).get(j).getObject() instanceof Road)
                    {
                        return getNameNode(i-1, j, 2);
                    }else
                    {
                        return "";
                    }
                }else
                {
                    return "";
                }
            }else
            {
                return result;
            }
        }
        if(direction==3)
        {
            if((i-1)>=0)
            {
                if(this.matrix.get(i-1).get(j).getObject() instanceof Road)
                {
                    result=i+","+j;
                }
            }
            if((i+1)<this.matrix.size()){
                if(this.matrix.get(i+1).get(j).getObject() instanceof Road)
                {
                    result=i+","+j;
                }
            }
            if(result.equals(""))
            {
                if((j+1)<this.matrix.get(i).size())
                {
                    if(this.matrix.get(i).get(j+1).getObject() instanceof Road)
                    {
                        return getNameNode(i, j+1, 3);
                    }else
                    {
                        return "";
                    }
                }else
                {
                    return "";
                }
            }else
            {
                return result;
            }
        }
        if(direction==4)
        {
            if((j-1)>=0)
            {
                if(this.matrix.get(i).get(j-1).getObject() instanceof Road)
                {
                    result=i+","+j;
                }
            }
            if((j+1)<this.matrix.get(i).size()){
                if(this.matrix.get(i).get(j+1).getObject() instanceof Road)
                {
                    result=i+","+j;
                }
            }
            if(result.equals(""))
            {
                if((i+1)<this.matrix.size())
                {
                    if(this.matrix.get(i+1).get(j).getObject() instanceof Road)
                    {
                        return getNameNode(i+1, j, 4);
                    }else
                    {
                        return "";
                    }
                }else
                {
                    return "";
                }
            }else
            {
                return result;
            }
        }
        return "";
    }
    
    private void printGraph()
    {
        for (int i = 0; i < this.graph.getListNodes().size(); i++) 
        {
            System.out.println(this.graph.getListNodes().get(i).getName());
            for (int j = 0; j < this.graph.getListEdges().get(i).size(); j++) {
                System.out.println(this.graph.getListEdges().get(i).get(j).getEndNode().getName()+"|"+this.graph.getListEdges().get(i).get(j).getValue());
            }
        }
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
    public LinkedList<Miner> getListMiners() {
        return listMiners;
    }

    /**
     * @param listMiners the listMiners to set
     */
    public void setListMiners(LinkedList<Miner> listMiners) {
        this.listMiners = listMiners;
    }

    /**
     * @return the graph
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * @param graph the graph to set
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    /**
     * @return the matrix
     */
    
}
