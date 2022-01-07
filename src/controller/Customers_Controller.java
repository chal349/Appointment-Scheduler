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
import java.sql.SQLException;
import java.util.ResourceBundle;
import static DBconnection.DBCustomers.*;
import static controller.ScreenLoader.display;
import static controller.ScreenLoader.exit;

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
    @FXML private TableColumn<Customers, Integer> customerID_col;
    @FXML private TableColumn<Customers, String> name_col;
    @FXML private TableColumn<Customers, String> address_col;
    @FXML private TableColumn<Customers, Integer> postalCode_col;
    @FXML private TableColumn<Customers, Integer> phone_col;
    @FXML private TableColumn<Customers, String> divisionName_col;
    @FXML private TableColumn<Customers, String> countryName_col;

    private static Customers customerToModify;
    private static Customers customerToDelete;
    public static Customers getCustomerToModify() {
        return customerToModify;
    }

    @FXML
    void onActionDelete(ActionEvent event) throws SQLException {
        customerToDelete = customerTableView.getSelectionModel().getSelectedItem();
        if (customerToDelete == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No Customer was selected.");
            alert.showAndWait();
        } else {
            Customers customer = customerTableView.getSelectionModel().getSelectedItem();
            getCustomerAppointments(customer);
            DBCustomers.deleteCustomer(customer);
            customerTableView.setItems(getAllCustomers());
        }
    }

    @FXML
    void onActionUpdateCustomerScreen(ActionEvent event) throws IOException {
        customerToModify = customerTableView.getSelectionModel().getSelectedItem();
        if (customerToModify == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No Customer was selected.");
            alert.showAndWait();
        } else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/UpdateCustomer.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Update Customer");
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointmentsButton.setOnAction(actionEvent -> display("Appointments", "../view/Appointments.fxml"));
        reportsButton.setOnAction(actionEvent -> display("Reports", "../view/Reports.fxml"));
        addButton.setOnAction(actionEvent -> display("Add Customer", "../view/AddCustomer.fxml"));
        logoutButton.setOnAction(actionEvent -> exit());

        customerTableView.setItems(getAllCustomers());
        customerID_col.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        address_col.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCode_col.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phone_col.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divisionName_col.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        countryName_col.setCellValueFactory(new PropertyValueFactory<>("countryName"));
    }
}
