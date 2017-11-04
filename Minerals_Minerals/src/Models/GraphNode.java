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
public class GraphNode {
    
    private String name;

    public GraphNode() {
    }

    public GraphNode(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the Nombre to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
