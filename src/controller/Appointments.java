package controller;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class Appointments implements Initializable {

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

    @FXML
    private Tab allTab;

    @FXML
    private TableView<?> allTableView;

    @FXML
    private TableColumn<?, ?> allAppID_col;

    @FXML
    private TableColumn<?, ?> allTitle_col;

    @FXML
    private TableColumn<?, ?> allDescription_col;

    @FXML
    private TableColumn<?, ?> allLocation_col;

    @FXML
    private TableColumn<?, ?> allContact_col;

    @FXML
    private TableColumn<?, ?> allType_col;

    @FXML
    private TableColumn<?, ?> allStart_col;

    @FXML
    private TableColumn<?, ?> allEnd_col;

    @FXML
    private TableColumn<?, ?> custID_col;

    @FXML
    private TableColumn<?, ?> userID_col;

    @FXML
    private Tab weekTab;

    @FXML
    private TableView<?> weekTableView;

    @FXML
    private TableColumn<?, ?> weekAppID_col;

    @FXML
    private TableColumn<?, ?> weekTitle_col;

    @FXML
    private TableColumn<?, ?> weekDescription_col;

    @FXML
    private TableColumn<?, ?> weekLocation_col;

    @FXML
    private TableColumn<?, ?> weekContact_col;

    @FXML
    private TableColumn<?, ?> weekType_Col;

    @FXML
    private TableColumn<?, ?> weekStart_col;

    @FXML
    private TableColumn<?, ?> weekEnd_col;

    @FXML
    private Tab monthTab;

    @FXML
    private Button logoutButton;

    @FXML
    private Label headerText;

    @FXML
    void onActionAddScreen(ActionEvent event) {

    }

    @FXML
    void onActionCustomersScreen(ActionEvent event) {

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
    void onActionUpdateScreen(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
