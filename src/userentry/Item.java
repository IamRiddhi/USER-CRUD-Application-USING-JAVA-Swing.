/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userentry;

/**
 *
 * @author RIDDHI DUTTA
 */
public class Item 
{
    private String name;
    private int id;

    public Item(String name, int index) 
    {
        this.name = name;
        this.id = index;
    }

    public int getId() {
        return id;
    }
    
    public String toString()
    {
        return name;
    }
}
