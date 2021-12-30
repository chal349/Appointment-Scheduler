package controller;

import DBconnection.DBUsers;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
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


    @FXML
    void onActionLoginAppointmentsScreen(ActionEvent event) throws IOException {

        String filename = "login_activity.txt";
        FileWriter fileWriter = new FileWriter(filename, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        boolean validLogin = DBUsers.checkLogin(usernameField.getText(), passwordField.getText());

        if(validLogin) {

            printWriter.append(usernameField.getText() + " successfully logged in on " + LocalDate.now() + ", at " + LocalTime.now() + "\n");

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Appointments");
            stage.show();

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
