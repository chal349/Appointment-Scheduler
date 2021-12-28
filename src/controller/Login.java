package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
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

        if((passwordField.getText().equals("test")) && (usernameField.getText().equals("test"))) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Appointments");
            stage.show();
        } else if((!passwordField.getText().equals("test")) || (!usernameField.getText().equals("test"))) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(rb.getString("ErrorHeader"));
                alert.setTitle(rb.getString("ErrorTitle"));
                alert.setContentText(rb.getString("LoginError"));
                alert.show();
            }
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
