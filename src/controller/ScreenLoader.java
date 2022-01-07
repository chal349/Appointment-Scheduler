package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import main.Main;

import java.io.IOException;
import java.util.Optional;


public class ScreenLoader {

    public static void display(String title, String fxml){

        try {
            Parent root = FXMLLoader.load(ScreenLoader.class.getResource(fxml));
            Main.getPrimaryStage().setScene(new Scene(root));
            Main.getPrimaryStage().setTitle(title);
            Main.getPrimaryStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to Logout?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            Parent root = null;
            try {
                root = FXMLLoader.load(ScreenLoader.class.getResource("/view/Login.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.getPrimaryStage().setScene(new Scene(root));
            Main.getPrimaryStage().setTitle("SCHEDULER");
            Main.getPrimaryStage().show();
        }
    }
}
