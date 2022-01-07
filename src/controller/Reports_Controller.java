package controller;

import DBconnection.DBAppointments;
import DBconnection.DBContacts;
import DBconnection.DBCustomers;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import java.net.URL;
import java.util.ResourceBundle;
import static controller.ScreenLoader.display;
import static controller.ScreenLoader.exit;

/**
 * @author Corey Hall
 */

/**
 * Reports Controller Class - User can view and select Reports - contains LAMBDAS in Initialize, and both onActionEvent methods
 */
public class Reports_Controller implements Initializable {

    Stage stage;
    Parent scene;
    //VARIABLES
    @FXML private Button appointmentsButton;
    @FXML private Button customersButton;
    @FXML private Button reportsButton;
    @FXML private Tab allAppointmentsTab;
    @FXML private Button logoutButton;
    @FXML private Label headerText;

    //ALL APPOINTMENTS BY TYPE AND MONTH TABLEVIEW
    @FXML private TableView<Reports> allTableView;
    @FXML private TableColumn<Reports, String> allType_col;
    @FXML private TableColumn<Reports, String> allMonth_col;
    @FXML private TableColumn<Reports, String> allNumber_col;

    //CONTACTS SCHEDULE TABLEVIEW
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

    //CUSTOMER SCHEDULE TABLEVIEW
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

    // Lists for populating combo boxes
    public ObservableList<Contacts> contactsList = DBContacts.getAllContacts();
    public ObservableList<Customers> customersList = DBCustomers.getAllCustomers();

    /**
     * onAction filters Contacts Schedule tableview based on combo box selection
     * LAMBDAS for formatting Start and end times - used for clean concise code
     * @param event
     */
    @FXML
    void onContactSelectionFilterTableview(ActionEvent event) {
        //gets contact selected from combo box and sets tableview based on that contacts schedule
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
        //displays alert if contact has no appointments scheduled
            if (DBAppointments.getAllAppointmentsByContact(contactID).isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert");
                alert.setHeaderText(contactComboBox.getSelectionModel().getSelectedItem().getName() + " - has no appointments scheduled.");
                alert.show();
            }
    }

    /**
     * onAction filters Customer Schedule tableview based on combo box selection
     * LAMBDAS for formatting Start and end times - used for clean concise code
     * @param event
     */
    @FXML
    void onCustomerSelectionFilterTableview(ActionEvent event) {
        //gets customer selected from combo box and filters tableview based on that customers schedule
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
        //displays alert if customer has no appointments scheduled
        if (DBAppointments.getAllAppointmentsByCustomer(customerID).isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setHeaderText(customerComboBox.getSelectionModel().getSelectedItem().getName() + " - has no appointments scheduled.");
            alert.show();
        }
    }

    /**
     * Initializes Reports Controller screen -Contains LAMBDAS for moving throughout application
     * LAMBDA- setOnAction button Lambdas replaces each navigation button with one line of code.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //LAMBDAS FOR NAVIGATION
        customersButton.setOnAction(actionEvent -> display("Customers", "../view/Customers.fxml"));
        appointmentsButton.setOnAction(actionEvent -> display("Appointments", "../view/Appointments.fxml"));
        logoutButton.setOnAction(actionEvent -> exit());

        //Populate combo boxes
        contactComboBox.setItems(contactsList);
        contactTableView.setPlaceholder(new Label("PLEASE SELECT A CONTACT"));
        customerComboBox.setItems(customersList);
        customerTableView.setPlaceholder(new Label("PLEASE SELECT A CUSTOMER"));

        //Populate tableview
        allTableView.setItems(DBAppointments.getAppointmentsByTypeAndMonth());
        allType_col.setCellValueFactory(new PropertyValueFactory<>("type"));
        allMonth_col.setCellValueFactory(new PropertyValueFactory<>("month"));
        allNumber_col.setCellValueFactory(new PropertyValueFactory<>("total"));
    }
}
