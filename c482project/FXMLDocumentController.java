
package c482project;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author realr
 * This is the controller for the main screen
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label partsLabel;
    @FXML private Label productsLabel;
    @FXML private Label inventoryManagementSystemLabel;
    @FXML private Button addPartsButton;
    @FXML private TableView<Part>partsTable;
    @FXML private TableColumn<Part,Integer> partID;
    @FXML private TableColumn<Part,String> partName;
    @FXML private TableColumn<Part,Integer> partInventoryAmount;
    @FXML private TableColumn<Part,Double> priceCost;
    @FXML private Button exitButton;
    @FXML private TableView<Product>productTable;
    @FXML private TableColumn<Product,Integer> productIDCol;
    @FXML private TableColumn<Product,String>productNameCol;
    @FXML private TableColumn<Product,Integer>productInventoryAmountCol;
    @FXML private TableColumn<Product,Double>productPriceCostCol;
    @FXML private Button addProductButton;
    @FXML private Button modifyPartButton;
    @FXML private Button modifyProductButton;
    private static Part modifyPart;
    private static Product modifyProduct;
    private static int selectedProductIndex;
    /*
    When the add part button is pressed, the scene is changed to the add part screen
   
    */
    
    public void addPartButtonPressed(ActionEvent event) throws IOException {
        
        Parent addPartParent = FXMLLoader.load(getClass().getResource("AddPartsScreen.fxml"));
        Scene addPartsScene = new Scene(addPartParent);
        
        //Get the stage information
        Stage currentWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        currentWindow.setScene(addPartsScene);
        currentWindow.show();
}       
    public void modifyPartButtonPressed(ActionEvent event) throws IOException {
        
         Stage stage; 
        Parent root;       
        stage=(Stage) modifyPartButton.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifyPartScreen.fxml"));
        
        root =loader.load();
        ModifyPartScreenController controller = loader.getController();
        Part part=partsTable.getSelectionModel().getSelectedItem();
        int index = partsTable.getSelectionModel().getSelectedIndex();
        
        if(part != null) {
            controller.setPart(part, index);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
}       
    
    public void addProductButtonPressed(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("AddProductScreen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        productTable.setItems(Inventory.getAllProducts());
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryAmountCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        
        partsTable.setItems(Inventory.getAllParts());
        partID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryAmount.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCost.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        

       
    }    
    public void exitButtonPressed(){
    System.exit(0);
    }
    
    public void deletePartButtonPressed(ActionEvent event){
    
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the entire part, do you wish to continue?");
        alert.setTitle("Confirmation of Delete");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
        
            ObservableList<Part> allParts, singlePart;
            allParts = partsTable.getItems();
            singlePart = partsTable.getSelectionModel().getSelectedItems();
            singlePart.forEach(allParts::remove); 

        }
    }
  
    public void modifyProductButtonPressed(ActionEvent event) throws IOException{
    
     Stage stage; 
        Parent root;       
        stage=(Stage) modifyProductButton.getScene().getWindow();
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifyProductScreen.fxml"));
        
        root =loader.load();
        ModifyProductScreenController controller = loader.getController();
        Product product=productTable.getSelectionModel().getSelectedItem();
        int index = productTable.getSelectionModel().getSelectedIndex();
        
        if(product != null) {
            controller.setProduct(product, index);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void deleteProductButtonPressed(ActionEvent event){
     Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Product");
        alert.setHeaderText("Are you sure you want to delete this product?");
        alert.setContentText("Press OK to delete the product.Press Cancel to cancel the deletion.");
        alert.showAndWait();
        
        if (alert.getResult() == ButtonType.OK) {
            try {
                Product product = productTable.getSelectionModel().getSelectedItem();
                Inventory.removeProduct(product.getID());
            }
            catch (NullPointerException e) {
                Alert nullalert = new Alert(AlertType.ERROR);
                nullalert.setTitle("Product Deletion Error");
                nullalert.setHeaderText("The product was not deleted");
                nullalert.setContentText("There was no product selected");
                nullalert.showAndWait();
            }
        }
        else {
            alert.close();
        }
    
    }
     public static int getSelectedProductIndex() {
        return selectedProductIndex;
    }
    }
    

