package controller;

import DBconnection.DBAppointments;
import DBconnection.DBContacts;
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
import model.Contacts;
import model.Reports;

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
    @FXML private TableView<?> contactTableView;
    @FXML private TableColumn<?, ?> contactContact_col;
    @FXML private TableColumn<?, ?> contactAppID_col;
    @FXML private TableColumn<?, ?> contactTitle_col;
    @FXML private TableColumn<?, ?> contactType_col;
    @FXML private TableColumn<?, ?> contactDescription_col;
    @FXML private TableColumn<?, ?> contactDate_col;
    @FXML private TableColumn<?, ?> contactStart_col;
    @FXML private TableColumn<?, ?> contactEnd_col;
    @FXML private TableColumn<?, ?> contactCustID_col;
    @FXML private Tab userScheduleTab;
    @FXML private TableView<?> UserTableView;
    @FXML private TableColumn<?, ?> userUserID_col;
    @FXML private TableColumn<?, ?> userAppID_col;
    @FXML private TableColumn<?, ?> userCustomerID_col;
    @FXML private TableColumn<?, ?> userTitle_col;
    @FXML private TableColumn<?, ?> userDescription_col;
    @FXML private TableColumn<?, ?> userDate_col;
    @FXML private TableColumn<?, ?> userStart_col;
    @FXML private TableColumn<?, ?> userEnd_col;
    @FXML private Button logoutButton;
    @FXML private Label headerText;

    public ObservableList<Contacts> contactsList = DBContacts.getAllContacts();

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

        allTableView.setItems(DBAppointments.getAppointmentsByTypeAndMonth());
        allType_col.setCellValueFactory(new PropertyValueFactory<>("type"));
        allMonth_col.setCellValueFactory(new PropertyValueFactory<>("month"));
        allNumber_col.setCellValueFactory(new PropertyValueFactory<>("total"));



    }
}
