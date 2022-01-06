package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.Main;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class Loader {

    public static void display(String title, String fxml){

        try {
            Parent root = FXMLLoader.load(Loader.class.getResource(fxml));
            Main.getPrimaryStage().setScene(new Scene(root));
            Main.getPrimaryStage().setTitle(title);
            Main.getPrimaryStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
