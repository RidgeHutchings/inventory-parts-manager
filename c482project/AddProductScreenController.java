/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c482project;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ridgehutchings
 */
public class AddProductScreenController implements Initializable {

     private ObservableList<Part> currentParts = FXCollections.observableArrayList();
     private int productID;
    /**
     * Initializes the controller class.
     */
    Product newProduct;
    
    @FXML
    private TextField IDAddProductText;

    @FXML
    private TextField nameAddProductText;

    @FXML
    private TextField inventoryAddProductText;

    @FXML
    private TextField addProductPriceTextField;

    @FXML
    private TextField maxAddProductText;

    @FXML
    private TextField minAddProductText;


    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> partID;

    @FXML
    private TableColumn<Part, String> partName;

    @FXML
    private TableColumn<Part, Integer> partInventoryLevel;

    @FXML
    private TableColumn<Part, Double> priceCostPerUnit;

    @FXML
    private Button addProduct;

    @FXML
    private TableView<Part> associatedPartTable;

    @FXML
    private TableColumn<Part, Integer> associatedPartID;

    @FXML
    private TableColumn<Part, String> associatedPartName;

    @FXML
    private TableColumn<Part, Integer> associatedPartInventory;

    @FXML
    private TableColumn<Part, Double> associatedPartPrice;

    @FXML
    private Button deleteProduct;

    @FXML
    private Button saveProduct;

    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        updatePartTable();
        partID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
       
        newProduct = new Product(0, null, 0.0, 0, 0, 0);
        associatedPartTable.setItems(newProduct.getAssociatedParts());
        
        associatedPartID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
      
    }    
    public void updatePartTable() {
        partsTable.setItems(Inventory.getAllParts());
        
}
    public void saveProductButtonPushed(ActionEvent event) throws IOException {

        int ID = 0;
        for(Product product : Inventory.getAllProducts()) {

        if(product.getID() > ID)
        ID = product.getID();

        }
        String name = nameAddProductText.getText();
        String inStock = inventoryAddProductText.getText();
        String price = addProductPriceTextField.getText();
        String max = maxAddProductText.getText();
        String min = minAddProductText.getText();
        ObservableList<Part> associatedParts = associatedPartTable.getItems();
        
        
        Product addProduct = new Product(0,"",0,0,0,0);
        addProduct.setID(++ID);
        addProduct.setName(name);
        addProduct.setStock(Integer.parseInt(inStock));
        addProduct.setPrice(Double.parseDouble(price));
        addProduct.setMax(Integer.parseInt(max));
        addProduct.setMin(Integer.parseInt(min));
        addProduct.setAssociatedParts(associatedParts);
        Inventory.addProduct(addProduct);
                            
         Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
            
          
    }
    public void addProductDeleteButtonPressed(ActionEvent event) {

       Part part = associatedPartTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Part Deletion");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure you want to delete " + part.getName() + " from parts?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("The part has been deleted");
            currentParts.remove(part);
        }
        else {
            System.out.println("You clicked cancel.");
        }
        
    }
     public void addProductButtonPushed(ActionEvent event) throws IOException {
  
        Part part = partsTable.getSelectionModel().getSelectedItem();
        currentParts.add(part);
        updateDeletePartTable();
    }
     public void updateDeletePartTable() {
        associatedPartTable.setItems(currentParts);
    }
       
    
     
     
     public void cancelButtonPushed(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field vlaues, do you wish to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
        
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }
    }

}
