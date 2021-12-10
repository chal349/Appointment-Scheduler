package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {

    @FXML
    private Label headerText;

    @FXML
    private Label appointmentID;

    @FXML
    private TextField appointmentID_field;

    @FXML
    private Label customerID;

    @FXML
    private ComboBox<?> customerID_box;

    @FXML
    private Label userID;

    @FXML
    private ComboBox<?> userID_box;

    @FXML
    private Label contact;

    @FXML
    private ComboBox<?> contactBox;

    @FXML
    private Label type;

    @FXML
    private ComboBox<?> typeBox;

    @FXML
    private Label location;

    @FXML
    private TextField locationField;

    @FXML
    private Label title;

    @FXML
    private TextField titleField;

    @FXML
    private Label description;

    @FXML
    private TextField descriptionField;

    @FXML
    private Label date;

    @FXML
    private DatePicker datePickerBox;

    @FXML
    private Label start;

    @FXML
    private ComboBox<?> startTimeBox;

    @FXML
    private Label end;

    @FXML
    private ComboBox<?> endTimeBox;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    @FXML
    void onActionCancel(ActionEvent event) {

    }

    @FXML
    void onActionSave(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
