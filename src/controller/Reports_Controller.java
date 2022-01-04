package controller;

import DBconnection.DBAppointments;
import DBconnection.DBContacts;
import DBconnection.DBCustomers;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Reports_Controller implements Initializable {

    Stage stage;
    Parent scene;

    @FXML private Button appointmentsButton;
    @FXML private Button customersButton;
    @FXML private Button reportsButton;
    @FXML private Tab allAppointmentsTab;
    @FXML private TableView<Reports> allTableView;
    @FXML private TableColumn<Reports, String> allType_col;
    @FXML private TableColumn<Reports, String> allMonth_col;
    @FXML private TableColumn<Reports, String> allNumber_col;

    @FXML private Tab contactScheduleTab;
    @FXML private ComboBox<Contacts> contactComboBox;
    @FXML private TableView<Appointments> contactTableView;
    @FXML private TableColumn<Appointments, Integer> contactAppID_col;
    @FXML private TableColumn<Appointments, String> contactTitle_col;
    @FXML private TableColumn<Appointments, String> contactType_col;
    @FXML private TableColumn<Appointments, String> contactDescription_col;
    @FXML private TableColumn<Appointments, String> contactStart_col;
    @FXML private TableColumn<Appointments, String> contactEnd_col;
    @FXML private TableColumn<Appointments, Integer> contactCustID_col;

    @FXML private Tab customerScheduleTab;
    @FXML private ComboBox<Customers> customerComboBox;
    @FXML private TableView<Appointments> customerTableView;
    @FXML private TableColumn<Appointments, Integer> customerAppID_col;
    @FXML private TableColumn<Appointments, String> customerType_col;
    @FXML private TableColumn<Appointments, String> customerTitle_col;
    @FXML private TableColumn<Appointments, String> customerDescription_col;
    @FXML private TableColumn<Appointments, String> customerStart_col;
    @FXML private TableColumn<Appointments, String> customerEnd_col;
    @FXML private TableColumn<Appointments, Integer> customerContactID_col;
    @FXML private Button logoutButton;
    @FXML private Label headerText;
    public ObservableList<Contacts> contactsList = DBContacts.getAllContacts();
    public ObservableList<Customers> customersList = DBCustomers.getAllCustomers();


    @FXML
    void onContactSelectionFilterTableview(ActionEvent event) {
        int contactID = contactComboBox.getSelectionModel().getSelectedItem().getContactID();
        contactTableView.setItems(DBAppointments.getAllAppointmentsByContact(contactID));

        contactAppID_col.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        contactType_col.setCellValueFactory(new PropertyValueFactory<>("type"));
        contactTitle_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        contactDescription_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        //LAMBDAS to format Start and End times
        contactStart_col.setCellValueFactory(data -> data.getValue().getStartFormatted());
        contactEnd_col.setCellValueFactory(data -> data.getValue().getEndFormatted());
        contactCustID_col.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            if (DBAppointments.getAllAppointmentsByContact(contactID).isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert");
                alert.setHeaderText(contactComboBox.getSelectionModel().getSelectedItem().getName() + " - has no appointments scheduled.");
                //alert.setContentText();
                alert.show();
            }
    }

    @FXML
    void onCustomerSelectionFilterTableview(ActionEvent event) {
        int customerID = customerComboBox.getSelectionModel().getSelectedItem().getCustomerID();
        customerTableView.setItems(DBAppointments.getAllAppointmentsByCustomer(customerID));

        customerAppID_col.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        customerType_col.setCellValueFactory(new PropertyValueFactory<>("type"));
        customerTitle_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        customerDescription_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        //LAMBDAS to format Start and End times
        customerStart_col.setCellValueFactory(data -> data.getValue().getStartFormatted());
        customerEnd_col.setCellValueFactory(data -> data.getValue().getEndFormatted());
        customerContactID_col.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        if (DBAppointments.getAllAppointmentsByCustomer(customerID).isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setHeaderText(customerComboBox.getSelectionModel().getSelectedItem().getName() + " - has no appointments scheduled.");
            //alert.setContentText();
            alert.show();
        }
    }

    @FXML
    void onActionAppointmentsScreen(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointments");
        stage.show();
    }

    @FXML
    void onActionCustomersScreen(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Customers");
        stage.show();
    }

    @FXML
    void onActionLogout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to Logout?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("SCHEDULER");
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contactComboBox.setItems(contactsList);
        contactTableView.setPlaceholder(new Label("Please select a Contact"));
        customerComboBox.setItems(customersList);
        customerTableView.setPlaceholder(new Label("Please select a Customer"));

        allTableView.setItems(DBAppointments.getAppointmentsByTypeAndMonth());
        allType_col.setCellValueFactory(new PropertyValueFactory<>("type"));
        allMonth_col.setCellValueFactory(new PropertyValueFactory<>("month"));
        allNumber_col.setCellValueFactory(new PropertyValueFactory<>("total"));



    }
}
