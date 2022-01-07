package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import helper.JDBC;

/**
 * @author Corey Hall
 */

/**
 * Main Class - Starts application and connects to database
 */
public class Main extends Application {

    private static Stage primaryStage;

    // opens login screen
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        primaryStage.setTitle("SCHEDULER");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
        Main.primaryStage = primaryStage;
    }

    // method used by ScreenLoader class to get the primaryStage
    public static Stage getPrimaryStage(){
        return Main.primaryStage;
    }

    // connects and closes database
    public static void main(String[] args) {
        JDBC.makeConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
