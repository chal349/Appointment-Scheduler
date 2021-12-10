package controller;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Customer implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button appointmentsButton;

    @FXML
    private Button customersButton;

    @FXML
    private Button reportsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Label headerText;

    @FXML
    private TableView<?> customerTableView;

    @FXML
    private TableColumn<?, ?> custID_col;

    @FXML
    private TableColumn<?, ?> name_col;

    @FXML
    private TableColumn<?, ?> address_col;

    @FXML
    private TableColumn<?, ?> zip_col;

    @FXML
    private TableColumn<?, ?> phone_col;

    @FXML
    private TableColumn<?, ?> DateCreated_col;

    @FXML
    private TableColumn<?, ?> createdBy_col;

    @FXML
    private TableColumn<?, ?> lastUpdate_col;

    @FXML
    private TableColumn<?, ?> updatedBy_col;

    @FXML
    private TableColumn<?, ?> divID_col;

    @FXML
    void onActionAddCustomerScreen(ActionEvent event) {

    }

    @FXML
    void onActionAppointmentsScreen(ActionEvent event) {

    }

    @FXML
    void onActionDelete(ActionEvent event) {

    }

    @FXML
    void onActionLogout(ActionEvent event) {

    }

    @FXML
    void onActionReportsScreen(ActionEvent event) {

    }

    @FXML
    void onActionUpdateCustomerScreen(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
