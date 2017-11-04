/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Chinche
 */
public class GraphEdge {
    
    private GraphNode endNode;
    private int value;

    public GraphEdge() {
    }

    public GraphEdge(GraphNode endNode, int value) {
        this.endNode = endNode;
        this.value = value;
    }

    /**
     * @return the endNode
     */
    public GraphNode getEndNode() {
        return endNode;
    }

    /**
     * @param endNode the endNode to set
     */
    public void setEndNode(GraphNode endNode) {
        this.endNode = endNode;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }
    
}
