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
public class Outsourced extends Part{
    
    String companyName;
    
    
    public Outsourced(int id, String name, double price, int stock, int min, int max,String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    public void setCompanyName(String companyName){
    this.companyName = companyName;
    }
    public String getCompanyName(){
    return companyName;
    }
}
