/*
 * This is the controller for the add parts screen
 */
package c482project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ridgehutchings
 */
public class AddPartsScreenController implements Initializable {

    @FXML private Button cancelButton; 
    @FXML private Button saveButton;
    @FXML private RadioButton inHouse;
    @FXML private RadioButton outSourced;
    @FXML private Label compNameMachineIDLabel;
    @FXML private ToggleGroup radioButtonToggleGroup;
    @FXML private TextField compNameMachineIDText;
    @FXML private TextField partNameText;
    @FXML private TextField invText;
    @FXML private TextField priceCostText;
    @FXML private TextField minText;
    @FXML private TextField maxText;
    @FXML private TextField IDText;
    private int partID;
     
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        radioButtonToggleGroup = new ToggleGroup();
        this.inHouse.setToggleGroup(radioButtonToggleGroup);
        this.outSourced.setToggleGroup(radioButtonToggleGroup);
        partID = Inventory.getPartID();
        IDText.setText("Auto Gen: " + partID);
    }    
    
    public void cancelButtonPressed(ActionEvent event) throws IOException{
    
    Parent addPartParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene addPartsScene = new Scene(addPartParent);
        
        //Get the stage information
        Stage currentWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        currentWindow.setScene(addPartsScene);
        currentWindow.show();
    }
    
    public void saveButtonPressed(ActionEvent event) throws IOException{
        
        
        
        String name = partNameText.getText();
        int inventory = Integer.parseInt(invText.getText());
        double priceCost = Double.parseDouble(priceCostText.getText());
        int min = Integer.parseInt(minText.getText());
        int max = Integer.parseInt(maxText.getText());
        
        try{
            if(min>max){
                Alert alert = new Alert(Alert.AlertType.ERROR,"Your min cannot be greater than your max");
                alert.showAndWait();
            }
            else if(inventory > max || inventory < min){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Inventory must be between min and max values");
                alert.showAndWait();
            }
            else {
                if(radioButtonToggleGroup.getSelectedToggle().equals(inHouse)){
                int machineID = Integer.parseInt(compNameMachineIDText.getText());
                InHouse newPart = new InHouse(partID,name,priceCost,inventory,min,max,machineID);
                
                Inventory.addPart(newPart);
                
                }
                if(radioButtonToggleGroup.getSelectedToggle().equals(outSourced)){
                String companyName = compNameMachineIDText.getText();
                Outsourced newPart = new Outsourced(partID,name,priceCost,inventory,min,max,companyName);
                Inventory.addPart(newPart);
                }
                
                 Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                 Object scene = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                 stage.setScene(new Scene((Parent) scene));
                 stage.show();
                 
            }
            
           
            
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please make sure you've entered a valid value for each text field");
            alert.showAndWait();
        }
                
        
        
       
        
   
    
    }
    //this method will change the company/machine id label depending on what radio button is selected
    
    public void setCompanyNameMachineIDLabel(){
        if(radioButtonToggleGroup.getSelectedToggle().equals(this.inHouse)){
        compNameMachineIDLabel.setText("Machine ID");
        compNameMachineIDText.setText("Mach ID");
        }
        
        
        
        if(radioButtonToggleGroup.getSelectedToggle().equals(this.outSourced)){
        compNameMachineIDLabel.setText("Company Name");
        compNameMachineIDText.setText("Comp Nm");
        }
    }
}
