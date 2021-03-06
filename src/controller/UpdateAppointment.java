package controller;

import DBconnection.DBAppointments;
import DBconnection.DBContacts;
import DBconnection.DBCustomers;
import DBconnection.DBUsers;
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
 * UpdateAppointment Class - used for updating an appointment in the database
 */
public class UpdateAppointment implements Initializable {

    Stage stage;
    Parent scene;

    //VARIABLES
    @FXML private Label headerText;
    @FXML private TextField appointmentID_field;
    @FXML private Label customerID;
    @FXML private Label userID;
    @FXML private Label contact;
    @FXML private Label type;
    @FXML private Label location;
    @FXML private TextField locationField;
    @FXML private Label title;
    @FXML private TextField titleField;
    @FXML private Label description;
    @FXML private TextField descriptionField;
    @FXML private Label date;
    @FXML private Label start;
    @FXML private Label end;
    @FXML private Button cancelButton;
    @FXML private Button saveButton;
    @FXML private ComboBox<Customers> customerID_box;
    @FXML private ComboBox<Users> userID_box;
    @FXML private ComboBox<Contacts> contactBox;
    @FXML private ComboBox<String> typeBox;
    @FXML private DatePicker datePickerBox;
    @FXML private ComboBox<LocalTime> startTimeBox;
    @FXML private ComboBox<LocalTime> endTimeBox;

    //Lists for populating combo boxes
    public ObservableList<Users> userList = DBUsers.getAllUsers();
    public ObservableList<Contacts> contactsList = DBContacts.getAllContacts();
    public ObservableList<String> typesList = DBAppointments.getAllTypes();
    public ObservableList<Customers> customersList = DBCustomers.getAllCustomers();

    //Variables for user selected appointment and establishing EST business hours
    private Appointments appointmentSelected;
    private LocalTime openEST = LocalTime.of(8, 0);
    private LocalTime closedEST = LocalTime.of(22, 0);
    
   /**
    * Initializes UpdateAppointment screen
    */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //populates start and end time boxes
        ObservableList<LocalTime> start = FXCollections.observableArrayList();
        ObservableList<LocalTime> end = FXCollections.observableArrayList();
        LocalTime times = LocalTime.MIDNIGHT;
        start.add(times);
        end.add(times);
        times = times.plusMinutes(15);
        while (!times.equals(LocalTime.MIDNIGHT)) {
            start.add(times);
            end.add(times);
            times = times.plusMinutes(15);
        }
        startTimeBox.setItems(start);
        endTimeBox.setItems(end);
        //Populates date picker and combo boxes with database info
        datePickerBox.setValue(LocalDate.now());
        typeBox.setItems(typesList);
        userID_box.setItems(userList);
        customerID_box.setItems(customersList);
        contactBox.setItems(contactsList);

        //Populates all text fields and combo boxes to have appointment to modify selections preselected
        appointmentSelected = Appointments_Controller.getAppointmentToModify();
        appointmentID_field.setText(String.valueOf(appointmentSelected.getAppointmentID()));
        typeBox.setValue(appointmentSelected.getType());
        titleField.setText(String.valueOf(appointmentSelected.getTitle()));
        descriptionField.setText(String.valueOf(appointmentSelected.getDescription()));
        locationField.setText(String.valueOf(appointmentSelected.getLocation()));
        startTimeBox.setValue(appointmentSelected.getStart().toLocalTime());
        endTimeBox.setValue(appointmentSelected.getEnd().toLocalTime());
        datePickerBox.setValue(appointmentSelected.getStart().toLocalDate());

        // set customerID combo box to selected appointment to modify
        for (Customers customer : customerID_box.getItems()) {
            if (appointmentSelected.getCustomerID() == customer.getCustomerID()) {
                customerID_box.setValue(customer);
            }
        }
        // set contactID combo box to selected appointment to modify
        for (Contacts contact : contactBox.getItems()) {
            if (appointmentSelected.getContactID() == contact.getContactID()) {
                contactBox.setValue(contact);
            }
        }
        // set userID combo box to selected appointment to modify
        for (Users user : userID_box.getItems()) {
            if (appointmentSelected.getUserID() == user.getUserID()) {
                userID_box.setValue(user);
            }
        }
    }

    /**
     * onAction Cancel and return to Appointments screen
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
     * onAction Save if all inputs are valid
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        
        // Checks that all text fields and combo boxes have selections/entries
        if
               (titleField.getText().isEmpty() ||
                descriptionField.getText().isEmpty() ||
                locationField.getText().isEmpty() ||
                typeBox.getSelectionModel().isEmpty() ||
                customerID_box.getSelectionModel().isEmpty() ||
                userID_box.getSelectionModel().isEmpty() ||
                contactBox.getSelectionModel().isEmpty() ||
                datePickerBox.getChronology().equals(null) ||
                startTimeBox.getSelectionModel().isEmpty() ||
                endTimeBox.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("All fields must be completed!");
            alert.showAndWait();
            return;
        }

        // gets selection and text field inputs
        String appointmentID = appointmentID_field.getText();
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

        // gets start/end time and date selection then combines them
        LocalTime startTimeSelection = startTimeBox.getSelectionModel().getSelectedItem();
        LocalTime endTimeSelection = endTimeBox.getSelectionModel().getSelectedItem();
        LocalDate dateSelection = datePickerBox.getValue();
        LocalDateTime new_StartDateAndTime = LocalDateTime.of(dateSelection, startTimeSelection);
        LocalDateTime new_EndDateAndTime = LocalDateTime.of(dateSelection, endTimeSelection);

        // convert chosen times to system default
        ZoneId zoneID = ZoneId.systemDefault();
        ZonedDateTime zoneStart = ZonedDateTime.of(new_StartDateAndTime, zoneID);
        ZonedDateTime zoneEnd = ZonedDateTime.of(new_EndDateAndTime, zoneID);

        // convert chosen times to EST
        ZoneId EST = ZoneId.of("America/New_York");
        ZonedDateTime estStart = zoneStart.withZoneSameInstant(EST);
        ZonedDateTime estEnd = zoneEnd.withZoneSameInstant(EST);

        // convert EST back to Local Date Time to validate chosen times
        LocalTime selectedStart = estStart.toLocalDateTime().toLocalTime();
        LocalTime selectedEnd = estEnd.toLocalDateTime().toLocalTime();

        // checks for appointments in database based on customer selected. Checks chosen times against already booked appointments for that customer
        ObservableList<Appointments> appointmentConflicts = DBAppointments.getAllAppointmentsByCustomer(customerID);
        for (Appointments a : appointmentConflicts) {
            LocalDateTime startOfBookedAppointment = a.getStart();
            LocalDateTime endOfBookedAppointment = a.getEnd();
            int ID = a.getAppointmentID();

            // checks all appointments in database except current appointmentID. Checks chosen times against other already booked appointments for that customer
            if (ID == appointmentSelected.getAppointmentID()) {
                break;
            }
              else if ((startOfBookedAppointment.isAfter(new_StartDateAndTime) && startOfBookedAppointment.isBefore(new_EndDateAndTime)) ||
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
        //compares selected start and end to Open/Closed hours
        if (selectedStart.isBefore(openEST) || selectedEnd.isAfter(closedEST)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selected times must be within open business hours. 8am - 10pm EST");
            alert.showAndWait();
            return;
        }
        if (selectedStart.equals(selectedEnd) || selectedStart.isAfter(selectedEnd)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selected Start time must be before selected End time.");
            alert.showAndWait();
            return;
            
            //If all selections were made and valid - new appointment is added and user returns to Appointments screen.
        } else {
            DBAppointments.updateAppointment(title, description, location, type, new_StartDateAndTime, new_EndDateAndTime, customerID, userID, contactID, appointmentID);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Appointments");
            stage.show();
        }
    }
}
