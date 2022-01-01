package DBconnection;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Appointments;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DBUsers {


    public static ObservableList<Users> getAllUsers(){
        ObservableList<Users> list = FXCollections.observableArrayList();
        String sql = "SELECT User_ID, User_Name, Password FROM users";
        try{
            ResultSet rs = JDBC.getConnection().createStatement().executeQuery(sql);
            while(rs.next()){
                int userID = rs.getInt("User_ID");
                String username = rs.getString("User_Name");
                String password = rs.getString("Password");
                Users users = new Users(userID, username, password);
                list.add(users);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public static boolean checkLogin(String username, String password){
        try{
            String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
    public static ObservableList<Users> findUsername() {
        ObservableList<Users> users = FXCollections.observableArrayList();
        String sql = "SELECT User_ID FROM users WHERE User_Name = '$User_Name'";
        try{
            ResultSet rs = JDBC.getConnection().createStatement().executeQuery(sql);
            while(rs.next()){
                int userID = rs.getInt("User_ID");
                Users user = new Users(userID);
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }

}
