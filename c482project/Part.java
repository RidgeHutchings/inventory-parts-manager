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
abstract class Part {
    
    
    //attributes 
    int partID;
    String name;
    double price;
    int stock;
    int min;
    int max;
    
    
    
    //methods
    
    //constructor
    Part(int id,String name,double price,int stock,int min,int max)
    {
    this.partID = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.min = min;
    this.max = max;
    }
    
    //getters and setters
    public void setID(int id){
    this.partID = id;
    }
    public void setName(String name){
    this.name = name;
    }
    public void setPrice(double price){
    this.price = price;
    }
    public void setStock(int stock){
    this.stock = stock;
    }
    public void setMin(int min){
    this.min = min;
    }
    public void setMax(int max){
    this.max = max;
    }
    public int getID(){
    return partID;
    }
    public String getName(){
    return name;
    }
    public double getPrice(){
    return price;
    }
    public int getStock(){
    return stock;
    }
    public int getMin(){
    return min;
    }
    public int getMax(){
    return max;
    }
}
