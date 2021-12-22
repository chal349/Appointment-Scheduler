package DBconnection;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

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


}
