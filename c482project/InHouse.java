/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c482project;

/**
 *
 * @author ridgehutchings
 */
public class InHouse extends Part{
    
    int machineID;
    
    
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }
    public void setMachineID(int machineID){
    this.machineID = machineID;
    }
    public int getMachineID(){
    return machineID;
    }
}
