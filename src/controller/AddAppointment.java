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

/**
 * @author Corey Hall
 */

/**
 * AddAppointments Controller Class - adds new appointments to the database
 */
public class AddAppointment implements Initializable {

    Stage stage;
    Parent scene;

    //VARIABLES
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

    //Lists for populating combo boxes
    public ObservableList<Users> userList = DBUsers.getAllUsers();
    public ObservableList<Contacts> contactsList = DBContacts.getAllContacts();
    public ObservableList<String> typesList = DBAppointments.getAllTypes();
    public ObservableList<Customers> customersList = DBCustomers.getAllCustomers();

    // Establishes Business hours based on EST
    private LocalTime openEST = LocalTime.of(8, 0);
    private LocalTime closedEST = LocalTime.of(22, 0);
    
    
     /**
     * Initializes the AddAppointments screen
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        // all combo boxes and the date picker box is populated
        datePickerBox.setValue(LocalDate.now());
        typeBox.setItems(typesList);
        userID_box.setItems(userList);
        customerID_box.setItems(customersList);
        contactBox.setItems(contactsList);

        //populates start and time boxes
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

    /**
     * actionEvent goes back to Appointments screen when clicked
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointments");
        stage.show();
    }

    /**
     * actionEvent saves new appointment to database and returns to Appointments screen
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        // checks that all text fields and combo boxes have selections
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
        Customers customerSelected = customerID_box.getSelectionModel().getSelectedItem();
        int customerID = customerSelected.getCustomerID();

        // gets selection from userID combo box
        Users userSelected = userID_box.getSelectionModel().getSelectedItem();
        int userID = userSelected.getUserID();

        // gets selection from contactID combo box
        Contacts contactSelected = contactBox.getSelectionModel().getSelectedItem();
        int contactID = contactSelected.getContactID();

        // gets start + end time and date selection and combines them
        LocalTime startTimeSelection = startTimeBox.getSelectionModel().getSelectedItem();
        LocalTime endTimeSelection = endTimeBox.getSelectionModel().getSelectedItem();
        LocalDate dateSelection = datePickerBox.getValue();
        LocalDateTime new_StartDateAndTime = LocalDateTime.of(dateSelection, startTimeSelection);
        LocalDateTime new_EndDateAndTime = LocalDateTime.of(dateSelection, endTimeSelection);

        // converts chosen times to system default
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zoneStart = ZonedDateTime.of(new_StartDateAndTime, zoneId);
        ZonedDateTime zoneEnd = ZonedDateTime.of(new_EndDateAndTime, zoneId);

        // converts chosen times to EST
        ZoneId EST = ZoneId.of("America/New_York");
        ZonedDateTime estStart = zoneStart.withZoneSameInstant(EST);
        ZonedDateTime estEnd = zoneEnd.withZoneSameInstant(EST);

        // convert EST back to Local Date Time to validate chosen times
        LocalTime selectedStart = estStart.toLocalDateTime().toLocalTime();
        LocalTime selectedEnd = estEnd.toLocalDateTime().toLocalTime();

        // checks for appointments in database based on customer selected. Checks chosen times against already booked appointments for that customer
        ObservableList<Appointments> appointmentConflicts = DBAppointments.getAllAppointmentsByCustomer(customerID);
        for (Appointments a : appointmentConflicts){
            LocalDateTime startOfBookedAppointment = a.getStart();
            LocalDateTime endOfBookedAppointment = a.getEnd();

                if ((startOfBookedAppointment.isAfter(new_StartDateAndTime) && startOfBookedAppointment.isBefore(new_EndDateAndTime)) ||
                    (endOfBookedAppointment.isAfter(new_StartDateAndTime) && endOfBookedAppointment.isBefore(new_EndDateAndTime))      ||
                    (startOfBookedAppointment.isBefore(new_StartDateAndTime) && endOfBookedAppointment.isAfter(new_EndDateAndTime))    ||
                    (startOfBookedAppointment.isEqual(new_StartDateAndTime)) || (endOfBookedAppointment.isEqual(new_EndDateAndTime))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Appointment Time Conflict");
                alert.setContentText("The times selected conflict with existing appointment times for Customer, Please select a different time.");
                alert.showAndWait();
                return;
            }
        }

        //compares selected start and end times to Open/Closed business hours
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

        //If all selections were made and valid - new appointment is added and user returns to Appointments screen.
        else {
            DBAppointments.newAppointment(title, description, location, type, new_StartDateAndTime, new_EndDateAndTime, customerID, userID, contactID);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Appointments");
            stage.show();
        }
    }
}
