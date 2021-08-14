/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c482project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

/**
 *
 * @author ridgehutchings
 */
public class Inventory {
    
    //attributes
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int productID;
    private static int partID;
    
    
    
    //methods
    public static void addPart(Part part){
    
        allParts.add(part);
    }
    public static void addProduct(Product product){
        allProducts.add(product);
    }
    public Part lookupPart(int partID){
        
        return null;
    }
    public Product lookupProduct(int productID){
        return null;
        
    }
    public Part lookupPart(String partName){
    return null;
    }
    
    public Product lookupProduct(String productName){
    return null;
    }
    
    public void updatePart(int index,Part selectedPart){
    
    }
    public static void updateProduct(int index,Product newProduct){
    allProducts.set(index, newProduct);
    }
    public boolean deletePart(Part selectedPart){
    return true;
    }
    public boolean deleteProduct(Product selectedProduct){
    return true;
    }
    public static ObservableList<Part> getAllParts(){
    return allParts;
    }
    public static ObservableList<Product> getAllProducts(){
    return allProducts;
    }
    public static int getPartID() {
    partID++;
    return partID;
    }
    
    public static int getProductID() {
    productID++;
    return productID;
    }
    public static boolean removeProduct(int productID) {
        for (Product p : allProducts){
            if (p.getID() == productID) {
                allProducts.remove(p);
                return true;
            }
        }
        return false;
    }
   public static ObservableList<Part> getParts() {
        return allParts;
    }
    public static ObservableList<Product> getProducts() {
        return allProducts;
    }
}



