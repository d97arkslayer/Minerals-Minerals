/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author darkd
 */
public class SectionMap<T>
{
  private T Object;

    public SectionMap() {
    }

    public SectionMap(T Object) {
        this.Object = Object;
    }

    /**
     * @return the Object
     */
    public T getObject() {
        return Object;
    }

    /**
     * @param Object the Object to set
     */
    public void setObject(T Object) {
        this.Object = Object;
    }
  
}
