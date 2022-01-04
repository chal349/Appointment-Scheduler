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
import main.Main;
import model.Appointments;
import model.Users;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
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
    int id;
    int userID = 1;
    LocalDateTime start;


    @FXML
    void onActionLoginAppointmentsScreen(ActionEvent event) throws IOException, SQLException {

        String filename = "login_activity.txt";
        FileWriter fileWriter = new FileWriter(filename, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        String username = usernameField.getText();
        String password = passwordField.getText();
        if(username.equals("admin")){
            userID = 2;
        }
        boolean validLogin = DBUsers.checkLogin(username, password);
        //boolean validLogin = DBUsers.checkLogin(username, password, userID);
        ObservableList<Appointments> checkForUpcomingAppointments = DBAppointments.checkForUpcomingAppointments(userID);


        if(validLogin) {

            printWriter.append(username + "- successfully logged in on at " + ZonedDateTime.now() + "\n");

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Scheduler");
            stage.show();

            if (checkForUpcomingAppointments.size() > 0){
                for (Appointments appointment : checkForUpcomingAppointments){
                     id = appointment.getAppointmentID();
                     start = appointment.getStart();
                    // userID = appointment.getUserID();


                }
                DateTimeFormatter hoursMinutes = DateTimeFormatter.ofPattern("HH:mm a");
                String time = start.format(hoursMinutes);


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Welcome");
                alert.setHeaderText("Hello " + username.toUpperCase());
                alert.setContentText("You have appointment #" + id + " at " +  time);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Welcome");
                alert.setHeaderText("Hello " + username.toUpperCase());
                alert.setContentText("You have no appointments that start in the next 15 minutes.");
                alert.show();
            }

        } else {

                printWriter.append(username + "- has been denied access on " + ZonedDateTime.now() + "\n");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(rb.getString("ErrorHeader"));
                alert.setTitle(rb.getString("ErrorTitle"));
                alert.setContentText(rb.getString("LoginError"));
                alert.show();

        }
        printWriter.close();
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
