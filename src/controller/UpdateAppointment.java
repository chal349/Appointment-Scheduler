package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateAppointment implements Initializable {

    Stage stage;
    Parent scene;

    @FXML private Label headerText;
    @FXML private Label appointmentID;
    @FXML private TextField appointmentID_field;
    @FXML private Label customerID;
    @FXML private ComboBox<?> customerID_box;
    @FXML private Label userID;
    @FXML private ComboBox<?> userID_box;
    @FXML private Label contact;
    @FXML private ComboBox<?> contactBox;
    @FXML private Label type;
    @FXML private ComboBox<?> typeBox;
    @FXML private Label location;
    @FXML private TextField locationField;
    @FXML private Label title;
    @FXML private TextField titleField;
    @FXML private Label description;
    @FXML private TextField descriptionField;
    @FXML private Label date;
    @FXML private DatePicker datePickerBox;
    @FXML private Label start;
    @FXML private ComboBox<?> startBox;
    @FXML private Label end;
    @FXML private ComboBox<?> endBox;
    @FXML private Button cancelButton;
    @FXML private Button saveButton;

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointments");
        stage.show();
    }

    @FXML
    void onActionSave(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
