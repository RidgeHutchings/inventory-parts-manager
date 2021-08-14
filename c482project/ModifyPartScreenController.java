/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class ModifyPartScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Part selectedPart;
    int selectedIndex;
    
    
    @FXML private Button modifyPartCancelButton; 
    @FXML private Button modifyPartSaveButton;
    @FXML private RadioButton modifyPartInHouse;
    @FXML private RadioButton modifyPartOutSourced;
    @FXML private Label modifyPartCompNameMachineIDLabel;
    @FXML private ToggleGroup modifyPartRadioButtonToggleGroup;
    @FXML private TextField modifyPartCompNameMachineIDText;
    @FXML private TextField modifyPartNameText;
    @FXML private TextField modifyPartInvText;
    @FXML private TextField modifyPartPriceCostText;
    @FXML private TextField modifyPartMinText;
    @FXML private TextField modifyPartMaxText;
    @FXML private TextField modifyPartIDText;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        modifyPartRadioButtonToggleGroup = new ToggleGroup();
        this.modifyPartInHouse.setToggleGroup(modifyPartRadioButtonToggleGroup);
        this.modifyPartOutSourced.setToggleGroup(modifyPartRadioButtonToggleGroup);
    }    
    //this method will change the company/machine id label depending on what radio button is selected
    
    public void setCompanyNameMachineIDLabel(){
        if(modifyPartRadioButtonToggleGroup.getSelectedToggle().equals(this.modifyPartInHouse)){
        
        modifyPartCompNameMachineIDText.setText("Mach ID");
        modifyPartCompNameMachineIDLabel.setText("Machine ID");

        }
        
        if(modifyPartRadioButtonToggleGroup.getSelectedToggle().equals(this.modifyPartOutSourced)){
        modifyPartCompNameMachineIDLabel.setText("Company Name");
        modifyPartCompNameMachineIDText.setText("Comp Nm");
        }
    }
     public void modifyPartCancelButtonPressed(ActionEvent event) throws IOException{
    
    Parent addPartParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene addPartsScene = new Scene(addPartParent);
        
        //Get the stage information
        Stage currentWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        currentWindow.setScene(addPartsScene);
        currentWindow.show();
    }
    
    
    public void modifyPartSaveButtonPressed(ActionEvent event) throws IOException {
       
       int id = selectedPart.getID();
       String name = modifyPartNameText.getText();
       int inventory = Integer.parseInt(modifyPartInvText.getText());
       double price = Double.parseDouble(modifyPartPriceCostText.getText());
       int max = Integer.parseInt(modifyPartMaxText.getText());
       int min = Integer.parseInt(modifyPartMinText.getText());
       
       if (modifyPartRadioButtonToggleGroup.getSelectedToggle().equals(this.modifyPartInHouse)) {
           
           int machineID = Integer.parseInt(modifyPartCompNameMachineIDText.getText());
           
           InHouse inhousePart = new InHouse(id, name, price, inventory, min, max, machineID);
           Inventory.getAllParts().set(selectedIndex, inhousePart);
       }
       
       if (modifyPartRadioButtonToggleGroup.getSelectedToggle().equals(this.modifyPartOutSourced)) {
           
           String companyName = modifyPartCompNameMachineIDText.getText();
          
           Outsourced outsourcedPart = new Outsourced(id, name, price, inventory, min, max, companyName);
           Inventory.getAllParts().set(selectedIndex, outsourcedPart);
       }
        
       Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
       Object scene = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       stage.setScene(new Scene((Parent) scene));
       stage.show();
       

    }
    
    public void setPart(Part part, int index){
    
      selectedPart = part;
      selectedIndex = index;
        
         if (part instanceof InHouse) {

            InHouse newPart = (InHouse) part;
            modifyPartInHouse.setSelected(true);
            modifyPartCompNameMachineIDLabel.setText("Machine ID");
            this.modifyPartNameText.setText(newPart.getName());
            this.modifyPartInvText.setText((Integer.toString(newPart.getStock())));
            this.modifyPartPriceCostText.setText((Double.toString(newPart.getPrice())));
            this.modifyPartMinText.setText((Integer.toString(newPart.getMin())));
            this.modifyPartMaxText.setText((Integer.toString(newPart.getMax())));
            this.modifyPartCompNameMachineIDText.setText((Integer.toString(newPart.getMachineID())));

        }

        if (part instanceof Outsourced) {

            Outsourced newPart = (Outsourced) part;
            modifyPartOutSourced.setSelected(true);
            modifyPartCompNameMachineIDLabel.setText("Company Name");
            this.modifyPartNameText.setText(newPart.getName());
            this.modifyPartInvText.setText((Integer.toString(newPart.getStock())));
            this.modifyPartPriceCostText.setText((Double.toString(newPart.getPrice())));
            this.modifyPartMinText.setText((Integer.toString(newPart.getMin())));
            this.modifyPartMaxText.setText((Integer.toString(newPart.getMax())));
            this.modifyPartCompNameMachineIDText.setText(newPart.getCompanyName());
        }  
    }
    }

