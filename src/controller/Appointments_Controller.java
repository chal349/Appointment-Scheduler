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
import model.Appointments;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static controller.ScreenLoader.display;
import static controller.ScreenLoader.exit;

/**
 * @author Corey Hall
 */

/**
 * Appointments Controller Class - User can view and select appointments for adding, updating, or deleting
 */
public class Appointments_Controller implements Initializable {

    Stage stage;
    Parent scene;

    //VARIABLES
    @FXML private Button appointmentsButton;
    @FXML private Button customersButton;
    @FXML private Button reportsButton;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private Button logoutButton;
    @FXML private Label headerText;

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

    //Variables for selecting appointment to update in UpdateAppointment screen
    private static Appointments appointmentToDelete;
    private static Appointments appointmentToModify;
    public static Appointments getAppointmentToModify() {return appointmentToModify;}
    
   /**
    * Initializes Appointments Controller screen -LAMBDAS for moving throughout application and for formatting start and end date and time
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //LAMBDAS for navigation buttons
        customersButton.setOnAction(actionEvent -> display("Customers", "../view/Customers.fxml"));
        reportsButton.setOnAction(actionEvent -> display("Reports", "../view/Reports.fxml"));
        addButton.setOnAction(actionEvent -> display("Add Appointment", "../view/AddAppointment.fxml"));
        logoutButton.setOnAction(actionEvent -> exit());

        //Populate ALL TABLEVIEW with info
        allTableView.setItems(DBAppointments.getAllAppointments());
        allApptID_col.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        allTitle_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        allDescription_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        allLocation_col.setCellValueFactory(new PropertyValueFactory<>("location"));
        allType_col.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        //LAMBDAS to format Start and End times
        allStart_col.setCellValueFactory(data -> data.getValue().getStartFormatted());
        allEnd_col.setCellValueFactory(data -> data.getValue().getEndFormatted());
        allContact_col.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        allCustID_col.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        allUserID_col.setCellValueFactory(new PropertyValueFactory<>("userID"));

       //Populate MONTH TABLEVIEW with info
        monthTableView.setItems(DBAppointments.getMonthAppointments());
        monthApptID_col.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        monthTitle_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthDescription_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthLocation_col.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthContact_col.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        monthType_col.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        //LAMBDAS to format Start and End times
        monthStart_col.setCellValueFactory(data -> data.getValue().getStartFormatted());
        monthEnd_col.setCellValueFactory(data -> data.getValue().getEndFormatted());
        monthCustID_col.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        monthUserID_col.setCellValueFactory(new PropertyValueFactory<>("userID"));

        //Populate MONTH TABLEVIEW with info
        weekTableView.setItems(DBAppointments.getWeekAppointments());
        weekApptID_col.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        weekTitle_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        weekDescription_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        weekLocation_col.setCellValueFactory(new PropertyValueFactory<>("location"));
        weekContact_col.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        weekType_Col.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        //LAMBDAS to format Start and End times
        weekStart_col.setCellValueFactory(data -> data.getValue().getStartFormatted());
        weekEnd_col.setCellValueFactory(data -> data.getValue().getEndFormatted());
        weekCustID_col.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        weekUserID_col.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }

    /**
     * actionEvent deletes appointment from database based on which tableview is selected
     */
    @FXML
    void onActionDelete(ActionEvent event) throws SQLException {
        
        //ALL TABLEVIEW
        if (allTab.isSelected()) {
            appointmentToDelete = allTableView.getSelectionModel().getSelectedItem();
            if(appointmentToDelete == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("No Appointment was selected.");
                alert.showAndWait();
                return;
            }
            
        //MONTH TABLEVIEW
        } else if (monthTab.isSelected()) {
            appointmentToDelete = monthTableView.getSelectionModel().getSelectedItem();
            if(appointmentToDelete == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("No Appointment was selected.");
                alert.showAndWait();
                return;
            }
            
        //WEEK TABLEVIEW
        } else if (weekTab.isSelected()) {
            appointmentToDelete = weekTableView.getSelectionModel().getSelectedItem();
            if(appointmentToDelete == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("No Appointment was selected.");
                alert.showAndWait();
                return;
            }
        }
                    Appointments appointment = appointmentToDelete;
                    DBAppointments.deleteAppointment(appointment);
                    allTableView.setItems(DBAppointments.getAllAppointments());
                    monthTableView.setItems(DBAppointments.getMonthAppointments());
                    weekTableView.setItems(DBAppointments.getWeekAppointments());
        }

    /**
     * actionEvent takes appointment selected based on table selected to UpdateAppointment screen
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionUpdateScreen(ActionEvent event) throws IOException {
        
        //ALL TABLEVIEW
        if (allTab.isSelected()) {
            appointmentToModify = allTableView.getSelectionModel().getSelectedItem();
            if(appointmentToModify == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("No Appointment was selected.");
                alert.showAndWait();
                return;
            }
            
        //MONTH TABLEVIEW
        } else if (monthTab.isSelected()) {
            appointmentToModify = monthTableView.getSelectionModel().getSelectedItem();
            if(appointmentToModify == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("No Appointment was selected.");
                alert.showAndWait();
                return;
            }
            
        //WEEK TABLEVIEW
        } else if (weekTab.isSelected()) {
            appointmentToModify = weekTableView.getSelectionModel().getSelectedItem();
            if(appointmentToModify == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("No Appointment was selected.");
                alert.showAndWait();
                return;
            }
        }
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/UpdateAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Update Appointment");
        stage.show();
    }
    
}
