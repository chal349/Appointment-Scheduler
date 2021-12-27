package controller;

import DBconnection.DBAppointments;
import DBconnection.DBContacts;
import DBconnection.DBCustomers;
import DBconnection.DBUsers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import model.Appointments;
import model.Contacts;
import model.Customers;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {

    Stage stage;
    Parent scene;

    @FXML private Label headerText;
    @FXML private Label appointmentID;
    @FXML private TextField appointmentID_field;
    @FXML private Label userID;
    @FXML private Label contact;
    @FXML private Label type;
    @FXML private Label location;
    @FXML private Label title;
    @FXML private Label description;
    @FXML private Label date;
    @FXML private Label start;
    @FXML private Label end;
    @FXML private Button cancelButton;
    @FXML private Button saveButton;

    @FXML private ComboBox<Customers> customerID_box;
    @FXML private ComboBox<Users> userID_box;
    @FXML private ComboBox<Contacts> contactBox;
    @FXML private ComboBox<String> typeBox;
    @FXML private TextField locationField;
    @FXML private TextField titleField;
    @FXML private TextField descriptionField;
    @FXML private DatePicker datePickerBox;
    @FXML private ComboBox<LocalTime> startTimeBox;
    @FXML private ComboBox<LocalTime> endTimeBox;

    public ObservableList<Users> userList = DBUsers.getAllUsers();
    public ObservableList<Contacts> contactsList = DBContacts.getAllContacts();
    public ObservableList<String> typesList = DBAppointments.getAllTypes();
    public ObservableList<Customers> customersList = DBCustomers.getAllCustomers();

    private LocalTime openEST = LocalTime.of(8, 0);
    private LocalTime closedEST = LocalTime.of(22, 0);

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointments");
        stage.show();
    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        if
               (titleField.getText().isEmpty()                     ||
                descriptionField.getText().isEmpty()               ||
                locationField.getText().isEmpty()                  ||
                typeBox.getSelectionModel().isEmpty()              ||
                customerID_box.getSelectionModel().isEmpty()       ||
                userID_box.getSelectionModel().isEmpty()           ||
                contactBox.getSelectionModel().isEmpty()           ||
                datePickerBox.getChronology().equals(null)         ||
                startTimeBox.getSelectionModel().isEmpty()         ||
                endTimeBox.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("All fields must be completed!");
            alert.showAndWait();
            return;
        }

        // gets selection and text field inputs
        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();
        String type = typeBox.getSelectionModel().getSelectedItem();
        // gets selection from customerID combo box
        Customers c = customerID_box.getSelectionModel().getSelectedItem();
        int customerID = c.getCustomerID();
        // gets selection from userID combo box
        Users u = userID_box.getSelectionModel().getSelectedItem();
        int userID = u.getUserID();
        // gets selection from contactID combo box
        Contacts C = contactBox.getSelectionModel().getSelectedItem();
        int contactID = C.getContactID();
        // gets start/end time and date selection
        LocalTime startTimeSelection = startTimeBox.getSelectionModel().getSelectedItem();
        LocalTime endTimeSelection = endTimeBox.getSelectionModel().getSelectedItem();
        LocalDate dateSelection = datePickerBox.getValue();
        // combines date and time selection
        LocalDateTime new_StartDateAndTime = LocalDateTime.of(dateSelection, startTimeSelection);
        LocalDateTime new_EndDateAndTime = LocalDateTime.of(dateSelection, endTimeSelection);
        // convert times to system default
        ZoneId myZoneId = ZoneId.systemDefault();
        ZonedDateTime myZoneStart = ZonedDateTime.of(new_StartDateAndTime, myZoneId);
        ZonedDateTime myZoneEnd = ZonedDateTime.of(new_EndDateAndTime, myZoneId);
        // convert times to EST
        ZoneId EST = ZoneId.of("America/New_York");
        ZonedDateTime estStart = myZoneStart.withZoneSameInstant(EST);
        ZonedDateTime estEnd = myZoneEnd.withZoneSameInstant(EST);
        // convert EST back to Local Date Time to validate chosen times
        LocalTime selectedStart = estStart.toLocalDateTime().toLocalTime();
        LocalTime selectedEnd = estEnd.toLocalDateTime().toLocalTime();


        ObservableList<Appointments> appointmentConflicts = DBAppointments.getAllAppointmentsByCustomer(customerID);
        for (Appointments a : appointmentConflicts){
            LocalDateTime startOfBookedAppointment = a.getStart();
            LocalDateTime endOfBookedAppointment = a.getEnd();

            if(new_StartDateAndTime.isBefore(startOfBookedAppointment.plusMinutes(1)) && new_EndDateAndTime.isAfter(endOfBookedAppointment.minusMinutes(1))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("jklasdf;ljsdfklj.");
                alert.showAndWait();
                return;

            } else if (new_StartDateAndTime.isAfter(startOfBookedAppointment.minusMinutes(1)) && new_StartDateAndTime.isBefore(endOfBookedAppointment.plusMinutes(1))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("jklasdf;ljsdfklj.");
                alert.showAndWait();
                return;
            } else if (new_EndDateAndTime.isAfter(startOfBookedAppointment.minusMinutes(1)) && new_EndDateAndTime.isBefore(endOfBookedAppointment.plusMinutes(1))){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("jklasdf;ljsdfklj.");
                alert.showAndWait();
                return;
            }
        }

        //compares selected start and end to Open/Closed hours
        if(selectedStart.isBefore(openEST) || selectedEnd.isAfter(closedEST)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selected times must be within open business hours. 8am - 10pm EST");
            alert.showAndWait();
            return;
        }
        if(selectedStart.equals(selectedEnd) || selectedStart.isAfter(selectedEnd)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selected Start time must be before selected End time.");
            alert.showAndWait();
            return;
        }


        else {
            DBAppointments.newAppointment(title, description, location, type, new_StartDateAndTime, new_EndDateAndTime, customerID, userID, contactID);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Appointments");
            stage.show();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePickerBox.setValue(LocalDate.now());
        typeBox.setItems(typesList);
        userID_box.setItems(userList);
        customerID_box.setItems(customersList);
        contactBox.setItems(contactsList);

        ObservableList<LocalTime> start = FXCollections.observableArrayList();
        ObservableList<LocalTime> end = FXCollections.observableArrayList();
        LocalTime times = LocalTime.MIDNIGHT;
        start.add(times);
        end.add(times);
        times = times.plusMinutes(15);
        while(!times.equals(LocalTime.MIDNIGHT)) {
            start.add(times);
            end.add(times);
            times = times.plusMinutes(15);
        }
            startTimeBox.setItems(start);
            endTimeBox.setItems(end);
        }








}
