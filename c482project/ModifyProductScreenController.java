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
public class ModifyProductScreenController implements Initializable {

     public static ObservableList<Part> currentAssociatedParts = FXCollections.observableArrayList();
     private static ObservableList<Part> currentParts = FXCollections.observableArrayList();
     private static int productID;
     
    Product selectedProduct;
    private static int selectedIndex;
    Product newProduct;
    
    @FXML
    private TextField IDAddPartText;

    @FXML
    private TextField nameAddPartText;

    @FXML
    private TextField inventoryAddPartText;

    @FXML
    private TextField addPriceTextField;

    @FXML
    private TextField maxAddPartText;

    @FXML
    private TextField minAddPartText;


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

       int id = selectedProduct.getID();
       String name = nameAddPartText.getText();
       int inventory = Integer.parseInt(inventoryAddPartText.getText());
       double price = Double.parseDouble(addPriceTextField.getText());
       int max = Integer.parseInt(maxAddPartText.getText());
       int min = Integer.parseInt(minAddPartText.getText());
       

       
       Product modifyProduct = new Product(id, name, price, inventory, min, max);
       Inventory.getAllProducts().set(selectedIndex, modifyProduct);
        
       Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
       Object scene = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       stage.setScene(new Scene((Parent) scene));
       stage.show();
        
    }
    public void modifyDeleteButtonPressed(ActionEvent event) {

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Associated Part");
            alert.setHeaderText("Are you sure you want to delete the associated part?");
            alert.setContentText("Press OK to delete the associated part. \nPress Cancel to cancel the deletion.");
            alert.showAndWait();
        
        if (alert.getResult() == ButtonType.OK) {
            try {
                Part part = associatedPartTable.getSelectionModel().getSelectedItem();
                Product.removeAssociatedParts(part.getID());
            }
            catch (NullPointerException e) {
                Alert nullalert = new Alert(Alert.AlertType.ERROR);
                nullalert.setTitle("Associated Part Deletion Error");
                nullalert.setHeaderText("The part was not deleted");
                nullalert.setContentText("A part was not selected");
                nullalert.showAndWait();
            }
        }
        else {
            alert.close();
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
    
     public void setProduct(Product product, int index) {
        selectedProduct = product;
        selectedIndex = index;
        
    if (product instanceof Product) {
            Product newProduct = (Product) product;

            this.nameAddPartText.setText(newProduct.getName());
            this.inventoryAddPartText.setText((Integer.toString(newProduct.getStock())));
            this.addPriceTextField.setText((Double.toString(newProduct.getPrice())));
            this.minAddPartText.setText((Integer.toString(newProduct.getMin())));
            this.maxAddPartText.setText((Integer.toString(newProduct.getMax())));
        
        }    
    }
     
}
