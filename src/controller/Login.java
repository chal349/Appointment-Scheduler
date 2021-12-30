package controller;

import DBconnection.DBAppointments;
import DBconnection.DBUsers;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Appointments;
import model.Users;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Login implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Label username;

    @FXML
    private TextField usernameField;

    @FXML
    private Label password;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label LocalTimeZone;

    @FXML
    private Button loginButton;

    ResourceBundle rb = ResourceBundle.getBundle("properties.lang", Locale.getDefault());
     int a;
    LocalDateTime start;

    @FXML
    void onActionLoginAppointmentsScreen(ActionEvent event) throws IOException, SQLException {

        String filename = "login_activity.txt";
        FileWriter fileWriter = new FileWriter(filename, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        boolean validLogin = DBUsers.checkLogin(usernameField.getText(), passwordField.getText());
        ObservableList<Appointments> check = DBAppointments.checkForUpcomingAppointments();



        if(validLogin) {

            printWriter.append(usernameField.getText() + " successfully logged in on " + LocalDate.now() + ", at " + LocalTime.now() + "\n");

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Appointments");
            stage.show();

            if (check.size() > 0){
                for (Appointments appt : check){
                     a = appt.getAppointmentID();
                     start = appt.getStart();

                }
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert");
                alert.setHeaderText("User Appointments");
                alert.setContentText("Hello, " + usernameField.getText() +" - you have appointment #" + a + " upcoming soon." + "\n" +  start );
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert");
                alert.setHeaderText("User Appointments");
                alert.setContentText("Hello " + usernameField.getText() + " - you have no upcoming appointments.");
                alert.show();
            }

        } else {

                printWriter.append(usernameField.getText() + " has been denied access on " + LocalDate.now() + ", at " + LocalTime.now() + "\n");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(rb.getString("ErrorHeader"));
                alert.setTitle(rb.getString("ErrorTitle"));
                alert.setContentText(rb.getString("LoginError"));
                alert.show();

        }
        printWriter.close();
    }

    private String getCheck(String check) {
        return check;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
                username.setText(rb.getString("Username"));
                password.setText(rb.getString("Password"));
                loginButton.setText(rb.getString("Button"));
                LocalTimeZone.setText(rb.getString("Location") + ":  " + ZoneId.systemDefault().getId());

        } catch (MissingResourceException e) {

        }
    }
}
