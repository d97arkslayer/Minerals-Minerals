/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.LinkedList;

/**
 *
 * @author Chinche
 */
public class Graph
{

    private LinkedList<GraphNode> listNodes;
    private LinkedList<LinkedList<GraphEdge>> listEdges;

    public Graph()
    {
        this.listNodes = new LinkedList<>();
        this.listEdges = new LinkedList<>();
    }

    public Graph(LinkedList<GraphNode> listNodes, LinkedList<LinkedList<GraphEdge>> listEdges)
    {
        this.listNodes = listNodes;
        this.listEdges = listEdges;
    }

    public void addNode(String nombre)
    {
        this.getListNodes().add(new GraphNode(nombre));
        this.getListEdges().add(new LinkedList<GraphEdge>());
    }

    public void addEdge(String start, String end, int value)
    {
        int i = 0;
        while (i < this.getListNodes().size())
        {
            if (this.getListNodes().get(i).getName().equalsIgnoreCase(start))
            {
                this.getListEdges().get(i).add(new GraphEdge(new GraphNode(end), value));
                break;
            }
        }
    }

    /**
     * @return the listNodes
     */
    public LinkedList<GraphNode> getListNodes()
    {
        return listNodes;
    }

    /**
     * @param listNodes the listNodes to set
     */
    public void setListNodes(LinkedList<GraphNode> listNodes)
    {
        this.listNodes = listNodes;
    }

    /**
     * @return the listEdges
     */
    public LinkedList<LinkedList<GraphEdge>> getListEdges()
    {
        return listEdges;
    }

    /**
     * @param listEdges the listEdges to set
     */
    public void setListEdges(LinkedList<LinkedList<GraphEdge>> listEdges)
    {
        this.listEdges = listEdges;
    }

}
