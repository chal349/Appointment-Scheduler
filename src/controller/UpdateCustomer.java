package controller;

import DBconnection.DBCountries;
import DBconnection.DBCustomers;
import DBconnection.DBDivisions;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;
import model.Divisions;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Corey Hall
 */

/**
 * UpdateCustomer Class - used for updating Customers in the database
 */
public class UpdateCustomer implements Initializable {

    Stage stage;
    Parent scene;

    //VARIABLES
    @FXML private Label headerText;
    @FXML private Label customerID;
    @FXML private TextField customerID_field;
    @FXML private Label fullName;
    @FXML private Button saveButton;
    @FXML private TextField fullNameField;
    @FXML private Label phoneNumber;
    @FXML private TextField phoneNumberField;
    @FXML private Label streetAddress;
    @FXML private TextField streetAddressField;
    @FXML private Label cityTown;
    @FXML private TextField cityTownField;
    @FXML private Label postalCode;
    @FXML private TextField postalCodeField;
    @FXML private Label country;
    @FXML private ComboBox<Countries> countryBox;
    @FXML private Label stateProvince;
    @FXML private ComboBox<Divisions> stateProvinceBox;
    @FXML private Button cancelButton;
    private Customers customerSelected;
    
    //Lists for populating combo boxes
    ObservableList<Countries> countries = DBCountries.getAllCountries();
    ObservableList<Divisions> divisions = DBDivisions.getAllDivisions();

    /**
     * actionEvent clears stateProvince combo box and repopulates based on country selection
     * @param event
     */
    @FXML
    void country_box(ActionEvent event) {
        divisions.clear();
        stateProvinceBox.setItems(divisions);
    }

    /**
     * mouseEvent populates stateProvince box based on country selection
     * @param event
     */
    public void stateProvince_box(MouseEvent event) {
        try{
            divisions.setAll();
            int divisionSelection = countryBox.getSelectionModel().getSelectedItem().getCountryID();

            for (Divisions division : DBDivisions.getAllDivisions()){
                if(division.getCountryID() == divisionSelection){
                    divisions.add(division);
                }
                stateProvinceBox.getSelectionModel().select(customerSelected.getDivisionID()-1);
            }
        } catch (NullPointerException n){
        }
    }

    /**
     * onaction Cancel and return to Customers screen
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Customers");
        stage.show();
    }

    /**
     * onAction Save customer to database and return to Customers screen
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionSave(ActionEvent event)throws IOException {
        //checks all text fields and combo boxes for inputs
        if      (fullNameField.getText().isEmpty()          || 
                streetAddressField.getText().isEmpty()      ||
                postalCodeField.getText().isEmpty()         ||
                phoneNumberField.getText().isEmpty()        ||
                countryBox.getSelectionModel().isEmpty()    ||
                stateProvinceBox.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("All fields must be completed.");
            alert.showAndWait();
        } 
        //gets inputs from all text fields and combo boxes and Updates customer in database - returns to Customers screen
        else{
            int customerID = customerSelected.getCustomerID();
            String name = fullNameField.getText();
            String address = streetAddressField.getText() + ", " + cityTownField.getText();
            String postalCode = postalCodeField.getText();
            String phone = phoneNumberField.getText();
            Divisions selection = stateProvinceBox.getSelectionModel().getSelectedItem();
            int divisionID = selection.getDivisionID();
            DBCustomers.updateCustomer(customerID, name, address, postalCode, phone, divisionID);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Customers");
            stage.show();
        }
    }

    /**
     * Initializes UpdateCustomer screen
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //gets customer selected from customers screen
        customerSelected = Customers_Controller.getCustomerToModify();
        //populates all text fields with customers info from database
        customerID_field.setText(String.valueOf(customerSelected.getCustomerID()));
        fullNameField.setText(String.valueOf(customerSelected.getName()));
        phoneNumberField.setText(String.valueOf(customerSelected.getPhone()));
        String street = customerSelected.getAddress();
        String streetRevised = street.substring(0, street.indexOf(","));
        streetAddressField.setText(streetRevised);
        String city = customerSelected.getAddress();
        String cityFromStreet = city.substring(city.lastIndexOf(',')+ 1).trim();
        cityTownField.setText(cityFromStreet);
        postalCodeField.setText(String.valueOf(customerSelected.getPostalCode()));
        
        //populates state/province combo box and country combo box with available choices based on customers previous selection
        divisions = DBDivisions.getAllDivisions();
        for(Divisions divisionSelected : divisions) {
            if(customerSelected.getDivisionID() == divisionSelected.getDivisionID()){
                stateProvinceBox.setValue(divisionSelected);
                
                for(Countries C : countries){
                    if(divisionSelected.getCountryID() == C.getCountryID()) {
                        countryBox.setValue(C);
                    }
                }
            }
        }
        stateProvinceBox.setItems(divisions);
        countryBox.setItems(countries);
    }
}
