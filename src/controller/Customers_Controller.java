package controller;

import DBconnection.DBCustomers;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static DBconnection.DBCustomers.getAllCustomers;

public class Customers_Controller implements Initializable {

    Stage stage;
    Parent scene;

    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private Button appointmentsButton;
    @FXML private Button customersButton;
    @FXML private Button reportsButton;
    @FXML private Button logoutButton;

    @FXML private Label headerText;

    @FXML private TableView<model.Customers> customerTableView;
    @FXML private TableColumn<model.Customers, Integer> customerID_col;
    @FXML private TableColumn<model.Customers, String> name_col;
    @FXML private TableColumn<model.Customers, String> address_col;
    @FXML private TableColumn<model.Customers, Integer> postalCode_col;
    @FXML private TableColumn<model.Customers, String> country_col;
    @FXML private TableColumn<model.Customers, Integer> phone_col;
    @FXML private TableColumn<model.Customers, Integer> divisionID_col;

    @FXML
    void onActionAddCustomerScreen(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Add Customer");
        stage.show();
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
    void onActionDelete(ActionEvent event) {

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

    @FXML
    void onActionReportsScreen(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Reports");
        stage.show();
    }

    @FXML
    void onActionUpdateCustomerScreen(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/UpdateCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Update Customer");
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBCustomers.getAllCustomers();
        customerTableView.setItems(getAllCustomers());
        customerID_col.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
        address_col.setCellValueFactory(new PropertyValueFactory<>("Address"));
        postalCode_col.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
        phone_col.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        divisionID_col.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));
    }
}
