package controller;

import DBconnection.DBCountries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Countries;
import model.Divisions;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomer implements Initializable {

    Stage stage;
    Parent scene;

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
    @FXML private ComboBox<?> countryBox;
    @FXML private Label stateProvince;
    @FXML private ComboBox<?> stateProvinceBox;
    @FXML private Button cancelButton;

    public ObservableList<Divisions> divisions = FXCollections.observableArrayList();
    public ObservableList<Countries> countries = DBCountries.getAllCountries();

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Customers");
        stage.show();
    }

    @FXML
    void onActionSave(ActionEvent event) {
        if (fullNameField.getText().isEmpty() ||
            streetAddressField.getText().isEmpty() ||
            postalCodeField.getText().isEmpty() ||
            phoneNumberField.getText().isEmpty() ||
            countryBox.getSelectionModel().isEmpty() ||
            stateProvinceBox.getSelectionModel().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("All fields must be completed.");
            alert.showAndWait();
        } else{
            String name = fullNameField.getText();
            String address = streetAddressField.getText();
            String postalCode = postalCodeField.getText();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
