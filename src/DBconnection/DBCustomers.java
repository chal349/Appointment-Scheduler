package DBconnection;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

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

    public static void newCustomer(String name, String address, String postalCode, String phone, int divisionID) {
        try{
            String sql = "INSERT INTO customers VALUES(NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionID);
            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCustomer(int customerID, String name, String address, String postalCode, String phone, int divisionID){
        try{
            String sql = "UPDATE customers set Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionID);
            ps.setInt(6, customerID);
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int getCustomerAppointments(Customers customer) {
        String sql = "SELECT COUNT(*) AS total FROM appointments WHERE Customer_ID = ?";
        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, customer.getCustomerID());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            } else{
                return 0;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public static void deleteCustomer(Customers customer) throws SQLException {
        if(getCustomerAppointments(customer) == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish delete Customer?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                String sql = "DELETE FROM customers WHERE Customer_ID = ?";
                PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
                ps.setInt(1, customer.getCustomerID());
                ps.executeUpdate();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Customer's appointments must be deleted before deleting customer.");
            alert.showAndWait();
        }
    }
}