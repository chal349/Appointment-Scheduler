package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Appointments implements Initializable {

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
    @FXML private TableView<?> allTableView;
    @FXML private TableColumn<?, ?> allApptID_col;
    @FXML private TableColumn<?, ?> allTitle_col;
    @FXML private TableColumn<?, ?> allDescription_col;
    @FXML private TableColumn<?, ?> allLocation_col;
    @FXML private TableColumn<?, ?> allType_col;
    @FXML private TableColumn<?, ?> allStart_col;
    @FXML private TableColumn<?, ?> allEnd_col;
    @FXML private TableColumn<?, ?> allContact_col;
    @FXML private TableColumn<?, ?> allCustID_col;
    @FXML private TableColumn<?, ?> allUserID_col;

    //WEEK TABLEVIEW
    @FXML private Tab weekTab;
    @FXML private TableView<?> weekTableView;
    @FXML private TableColumn<?, ?> weekApptID_col;
    @FXML private TableColumn<?, ?> weekTitle_col;
    @FXML private TableColumn<?, ?> weekDescription_col;
    @FXML private TableColumn<?, ?> weekLocation_col;
    @FXML private TableColumn<?, ?> weekType_Col;
    @FXML private TableColumn<?, ?> weekStart_col;
    @FXML private TableColumn<?, ?> weekEnd_col;
    @FXML private TableColumn<?, ?> weekContact_col;
    @FXML private TableColumn<?, ?> weekCustID_col;
    @FXML private TableColumn<?, ?> weekUserID_col;

    // MONTH TABLEVIEW
    @FXML private Tab monthTab;
    @FXML private TableView<?> monthTableView;
    @FXML private TableColumn<?, ?> monthApptID_col;
    @FXML private TableColumn<?, ?> monthTitle_col;
    @FXML private TableColumn<?, ?> monthDescription_col;
    @FXML private TableColumn<?, ?> monthLocation_col;
    @FXML private TableColumn<?, ?> monthType_col;
    @FXML private TableColumn<?, ?> monthStart_col;
    @FXML private TableColumn<?, ?> monthEnd_col;
    @FXML private TableColumn<?, ?> monthContact_col;
    @FXML private TableColumn<?, ?> monthCustID_col;
    @FXML private TableColumn<?, ?> monthUserID_col;

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
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
