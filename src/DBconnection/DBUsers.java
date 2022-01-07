package DBconnection;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Corey Hall
 */

/**
 * DBUsers Class - contains methods used to access database information.
 */
public class DBUsers {

    /**
     * getAllUsers
     * @return all users
     */
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

    /**
     * checkLogin checks for valid login
     * @param username
     * @param password
     * @return true or false
     */
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
}
