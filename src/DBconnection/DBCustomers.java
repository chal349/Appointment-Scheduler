package DBconnection;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCustomers {

    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID FROM customers";
        try {
            PreparedStatement PS = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = PS.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");

                Customers customer = new Customers(customerID, name, address, postalCode, phone, divisionID);
                allCustomers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCustomers;
    }

}