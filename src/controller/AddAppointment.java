package controller;

import DBconnection.DBAppointments;
import DBconnection.DBContacts;
import DBconnection.DBCustomers;
import DBconnection.DBUsers;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import model.Customers;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {

    Stage stage;
    Parent scene;

    @FXML private Label headerText;
    @FXML private Label appointmentID;
    @FXML private TextField appointmentID_field;
    @FXML private Label customerID;
    @FXML private ComboBox<Customers> customerID_box;
    @FXML private Label userID;
    @FXML private ComboBox<Users> userID_box;
    @FXML private Label contact;
    @FXML private ComboBox<Contacts> contactBox;
    @FXML private Label type;
    @FXML private ComboBox<String> typeBox;
    @FXML private Label location;
    @FXML private TextField locationField;
    @FXML private Label title;
    @FXML private TextField titleField;
    @FXML private Label description;
    @FXML private TextField descriptionField;
    @FXML private Label date;
    @FXML private DatePicker datePickerBox;
    @FXML private Label start;
    @FXML private ComboBox<LocalTime> startTimeBox;
    @FXML private Label end;
    @FXML private ComboBox<LocalTime> endTimeBox;
    @FXML private Button cancelButton;
    @FXML private Button saveButton;


    public ObservableList<Users> userList = DBUsers.getAllUsers();
    public ObservableList<Contacts> contactsList = DBContacts.getAllContacts();
    public ObservableList<String> typesList = DBAppointments.getAllTypes();
    public ObservableList<Customers> customersList = DBCustomers.getAllCustomers();
    private LocalTime OPEN = LocalTime.of(8, 0);
    private LocalTime CLOSED = LocalTime.of(21, 45);


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
        datePickerBox.setValue(LocalDate.now());
        typeBox.setItems(typesList);
        userID_box.setItems(userList);
        customerID_box.setItems(customersList);
        contactBox.setItems(contactsList);

        LocalTime startStart = OPEN;
        LocalTime startEnd = CLOSED;
        while(startStart.isBefore(startEnd.plusSeconds(1))){
            startTimeBox.getItems().add(startStart);
            startStart = startStart.plusMinutes(15);
        }
        LocalTime endStart = OPEN.plusMinutes(15);
        LocalTime endEnd = CLOSED;
        while(endStart.isBefore(endEnd.plusSeconds(1))){
            endTimeBox.getItems().add(endStart);
            endStart = endStart.plusMinutes(15);
        }

    }
}
