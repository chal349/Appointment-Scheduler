package controller;


import DBconnection.DBAppointments;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Appointments_Controller implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button appointmentsButton;

    @FXML
    private Button customersButton;

    @FXML
    private Button reportsButton;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    //ALL TABLEVIEW
    @FXML private Tab allTab;
    @FXML private TableView<model.Appointments> allTableView;
    @FXML private TableColumn<model.Appointments, Integer> allApptID_col;
    @FXML private TableColumn<model.Appointments, String> allTitle_col;
    @FXML private TableColumn<model.Appointments, String> allDescription_col;
    @FXML private TableColumn<model.Appointments, String> allLocation_col;
    @FXML private TableColumn<model.Appointments, String> allType_col;
    @FXML private TableColumn<model.Appointments, String> allStart_col;
    @FXML private TableColumn<model.Appointments, String> allEnd_col;
    @FXML private TableColumn<model.Appointments, Integer> allContact_col;
    @FXML private TableColumn<model.Appointments, Integer> allCustID_col;
    @FXML private TableColumn<model.Appointments, Integer> allUserID_col;

    //WEEK TABLEVIEW
    @FXML private Tab weekTab;
    @FXML private TableView<model.Appointments> weekTableView;
    @FXML private TableColumn<model.Appointments, Integer> weekApptID_col;
    @FXML private TableColumn<model.Appointments, String> weekTitle_col;
    @FXML private TableColumn<model.Appointments, String> weekDescription_col;
    @FXML private TableColumn<model.Appointments, String> weekLocation_col;
    @FXML private TableColumn<model.Appointments, String> weekType_Col;
    @FXML private TableColumn<model.Appointments, String> weekStart_col;
    @FXML private TableColumn<model.Appointments, String> weekEnd_col;
    @FXML private TableColumn<model.Appointments, Integer> weekContact_col;
    @FXML private TableColumn<model.Appointments, Integer> weekCustID_col;
    @FXML private TableColumn<model.Appointments, Integer> weekUserID_col;

    // MONTH TABLEVIEW
    @FXML private Tab monthTab;
    @FXML private TableView<model.Appointments> monthTableView;
    @FXML private TableColumn<model.Appointments, Integer> monthApptID_col;
    @FXML private TableColumn<model.Appointments, String> monthTitle_col;
    @FXML private TableColumn<model.Appointments, String> monthDescription_col;
    @FXML private TableColumn<model.Appointments, String> monthLocation_col;
    @FXML private TableColumn<model.Appointments, String> monthType_col;
    @FXML private TableColumn<model.Appointments, String> monthStart_col;
    @FXML private TableColumn<model.Appointments, String> monthEnd_col;
    @FXML private TableColumn<model.Appointments, Integer> monthContact_col;
    @FXML private TableColumn<model.Appointments, Integer> monthCustID_col;
    @FXML private TableColumn<model.Appointments, Integer> monthUserID_col;

    @FXML
    private Button logoutButton;

    @FXML
    private Label headerText;

    @FXML
    void onActionAddAppointmentScreen(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Add Appointment");
        stage.show();
    }

    @FXML
    void onActionGoToCustomerScreen(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Customers");
        stage.show();
    }

    @FXML
    void onActionDelete(ActionEvent event) {

    }

    @FXML
    void onActionLogout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to close Application?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    void onActionReportsScreen(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Reports");
        stage.show();
    }

    @FXML
    void onActionUpdateScreen(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/UpdateAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Update Appointment");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DBAppointments.populateTable();
        //Populate ALL TABLEVIEW with info
        allTableView.setItems(DBAppointments.getAllAppointments());
        allApptID_col.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        allTitle_col.setCellValueFactory(new PropertyValueFactory<>("Title"));
        allDescription_col.setCellValueFactory(new PropertyValueFactory<>("Description"));
        allLocation_col.setCellValueFactory(new PropertyValueFactory<>("Location"));
        allContact_col.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
        allType_col.setCellValueFactory(new PropertyValueFactory<>("Type"));
        allStart_col.setCellValueFactory(new PropertyValueFactory<>("Start"));
        allEnd_col.setCellValueFactory(new PropertyValueFactory<>("End"));
        allCustID_col.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        allUserID_col.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        //Populate MONTH TABLEVIEW with info
        monthTableView.setItems(DBAppointments.getAllAppointments());
        monthApptID_col.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        monthTitle_col.setCellValueFactory(new PropertyValueFactory<>("Title"));
        monthDescription_col.setCellValueFactory(new PropertyValueFactory<>("Description"));
        monthLocation_col.setCellValueFactory(new PropertyValueFactory<>("Location"));
        monthContact_col.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
        monthType_col.setCellValueFactory(new PropertyValueFactory<>("Type"));
        monthStart_col.setCellValueFactory(new PropertyValueFactory<>("Start"));
        monthEnd_col.setCellValueFactory(new PropertyValueFactory<>("End"));
        monthCustID_col.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        monthUserID_col.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        //Populate MONTH TABLEVIEW with info
        weekTableView.setItems(DBAppointments.getAllAppointments());
        weekApptID_col.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        weekTitle_col.setCellValueFactory(new PropertyValueFactory<>("Title"));
        weekDescription_col.setCellValueFactory(new PropertyValueFactory<>("Description"));
        weekLocation_col.setCellValueFactory(new PropertyValueFactory<>("Location"));
        weekContact_col.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
        weekType_Col.setCellValueFactory(new PropertyValueFactory<>("Type"));
        weekStart_col.setCellValueFactory(new PropertyValueFactory<>("Start"));
        weekEnd_col.setCellValueFactory(new PropertyValueFactory<>("End"));
        weekCustID_col.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        weekUserID_col.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
    }
}
