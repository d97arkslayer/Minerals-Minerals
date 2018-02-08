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
 * @author Eseban Herrera y Jaime Bernal
 * Clase grafo 
 */
public class Graph
{

    private LinkedList<GraphNode> listNodes;
    private LinkedList<LinkedList<GraphEdge>> listEdges;
    private LinkedHashMap<String, String> dijkstra;
    private LinkedList<String> markedList=new LinkedList<>();

    public Graph()
    {
        this.listNodes = new LinkedList<>();
        this.listEdges = new LinkedList<>();
        this.dijkstra = new LinkedHashMap<>();
    }
    
    /**
     * Constructor
     * @param listNodes lista de nodos del grafo
     * @param listEdges lista de aristas del grafo
     */
    
    public Graph(LinkedList<GraphNode> listNodes, LinkedList<LinkedList<GraphEdge>> listEdges)
    {
        this.listNodes = listNodes;
        this.listEdges = listEdges;
    }
    /**
     * Metodo que agregar un nodo al grafo
     * @param nombre nombre del grafo
     */
    
    public void addNode(String nombre)
    {
        this.getListNodes().add(new GraphNode(nombre));
        this.getListEdges().add(new LinkedList<GraphEdge>());
    }
    
    /**
     * Metodo que agrega la arista de acuerdo al nodo inicial
     * @param start Nombre nodo inical
     * @param end Nombre nodo final
     * @param value valor de la arista
     */

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
     * Metodo que calcula el dijlkstra partiendo desde un nodo inicial
     * @param initialNodeName Nodo inicial sobre el cual se va operar el dijkstra
     */
    
    public void calculateDijkstra(String initialNodeName)
    {
        int initialPositionNode = 0;
        for (int i = 0; i < this.listNodes.size(); i++) 
        {
            if(this.listNodes.get(i).getName().equalsIgnoreCase(initialNodeName))
            {
                this.dijkstra.put(initialNodeName, "0;"+initialNodeName);
                this.markedList.add(initialNodeName);
                initialPositionNode=i;
            }else
            {
                this.dijkstra.put(this.listNodes.get(i).getName(), "&");
            }
        }
        fillDijkstra(initialPositionNode);
        String nextNode=searchLower();
        dijkstra(nextNode);
        System.out.println(this.dijkstra);
    }
    
    /**
     * Metodo que determina el disktra, camino mininmo hasta el punto
     * @param nodeName  Nombre del nodo
     */
    
    private void dijkstra(String nodeName)
    {
        int i=0;
        boolean flag=false;
        while(i<this.listNodes.size() && !flag)
        {
            if(this.listNodes.get(i).getName().equalsIgnoreCase(nodeName))
            {
                flag=true;
            }else
            {
                i++;
            }
        }
        this.markedList.add(nodeName);
        String[] aux=this.dijkstra.get(nodeName).split(";");
        int value=Integer.parseInt(aux[0]);
        String label=aux[1];
        for (GraphEdge edge : this.listEdges.get(i)) 
        {
            if(!this.markedList.contains(edge.getEndNode().getName()))
            {
                if(!this.dijkstra.get(edge.getEndNode().getName()).equalsIgnoreCase("&"))
                {
                    String aux2[]=this.dijkstra.get(edge.getEndNode().getName()).split(";");
                    if(Integer.parseInt(aux[0])>(value+edge.getValue()) )
                    {
                        this.dijkstra.put(edge.getEndNode().getName(), (value+edge.getValue()+";"+nodeName));
                    }
                }else
                {
                      this.dijkstra.put(edge.getEndNode().getName(), (value+edge.getValue()+";"+nodeName));
                }
            }
        }
        String nextNode=searchLower();
        if(!nextNode.equals(""))
        {
            dijkstra(nextNode);
        }
    }
    /**
     * Metodo que busca el camino minimo
     * @return retorna un nodo de camino minimo
     */
    private String searchLower()
    {
        String result="";
        int lower=0;
        for (String nodeName : this.dijkstra.keySet()) 
        {
            if(!this.markedList.contains(nodeName) && !this.dijkstra.get(nodeName).equals("&"))
            {
                String[] aux=this.dijkstra.get(nodeName).split(";");
                if(result.equalsIgnoreCase(""))
                {
                    lower=Integer.parseInt(aux[0]);
                    result=nodeName;
                }else
                {
                    if(lower>Integer.parseInt(aux[0]))
                    {
                        lower=Integer.parseInt(aux[0]);
                        result=nodeName;
                    }
                }
            }
        }
        return  result;
    }
    
    /**
     * agrega la infomación del camino minimo desde un nodo inical
     * @param initialNode Nodo inicial
     */
    
    private void fillDijkstra(int initialNode)
    {
        for (GraphEdge edge : this.listEdges.get(initialNode)) 
        {
            this.dijkstra.put(edge.getEndNode().getName(), edge.getValue()+";"+this.listNodes.get(initialNode).getName());
        }
    }
    /**
     * Obtener ruta que debe seguir el camino
     * @param start nodo inical
     * @param end nodo final
     * @return lista con los nodos que debe visitar
     */
    public LinkedList<String> getRoute(String start,String end)
    {
        LinkedList<String> result=new LinkedList<>();
        return getRoute(start, end, result) ;
    }
    /**
     * Obtener ruta atraves de metodo recursivo
     * @param start nodo inicial
     * @param end nodo final
     * @param route ruta la cual se esta creando
     * @return ruta final de camino
     */
    private LinkedList<String> getRoute(String start,String end,LinkedList<String> route)
    {
        route.add(end);
        String[] aux = this.dijkstra.get(end).split(";");
        if(!end.equals(start))
        {
           return getRoute(start, aux[1], route);
        }else
        {
            return route;
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

    /**
     * @return the dijkstra
     */
    public LinkedHashMap<String, String> getDijkstra() {
        return dijkstra;
    }

    /**
     * @param dijkstra the dijkstra to set
     */
    public void setDijkstra(LinkedHashMap<String, String> dijkstra) {
        this.dijkstra = dijkstra;
    }
    
}
