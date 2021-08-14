/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c482project;

import javafx.collections.ObservableList;

/**
 *
 * @author ridgehutchings
 */
public class Product {
    //attributes
  static ObservableList<Part> associatedParts;
    int id;
    String name;
    double price;
    int stock;
    int min;
    int max;
   
    //constructor 
    Product(int id,String name,double price,int stock,int min,int max){
    this.id = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.min = min;
    this.max = max;
    }

    Product() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
   //methods
    public void setID(int id){
    this.id = id;
    }
    public void setName(String name){
    this.name = name;
    }
    public void setPrice(double price){
    this.price = price;
    }
    public void setStock(int stock){
    this.stock=stock;
    }
    public void setMin(int min){
    this.min=min;
    }
    public void setMax(int max){
    this.max=max;
    }
    public int getID(){
    return id;
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
    public void addAssociatedPart(Part part){
    associatedParts.add(part);
    }
    public ObservableList<Part> getAssociatedParts(){
    return associatedParts;
    }
    public static void setAssociatedParts(ObservableList<Part> associatedParts) {
        Product.associatedParts = associatedParts;
    }
      public static boolean removeAssociatedParts(int partID) {
        for (Part p : associatedParts) {
            if (p.getID() == partID) {
                associatedParts.remove(p);
                return true;
            }
        }
        return false;
    
      }
}
