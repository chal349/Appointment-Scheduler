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

/**
 * @author Corey Hall
 */

/**
 * DBCustomers Class - contains methods used to access database information.
 */
public class DBCustomers {

    /**
     * getAllCustomers
     * @return all customers
     */
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customers  INNER JOIN first_level_divisions  ON customers.Division_ID = first_level_divisions.Division_ID INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID order by Customer_ID";
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
                String divisionName = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");

                Customers customer = new Customers(customerID, name, address, postalCode, phone, divisionID, divisionName, countryID, countryName);
                allCustomers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCustomers;
    }

    /**
     * Creates a newCustomer
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionID
     */
    public static void newCustomer(String name, String address, String postalCode, String phone, int divisionID) {
        try{
            String sql = "INSERT INTO customers VALUES (NULL, ?, ?, ?, ?, now(), 'user', now(), 'user',?)";
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

    /**
     * Updates a Customer
     * @param customerID
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionID
     */
    public static void updateCustomer(int customerID, String name, String address, String postalCode, String phone, int divisionID){
        try{
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
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

    /**
     * getCustomerAppointments by specific customerID
     * @param customer
     * @return all appointments for specific customer
     */
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

    /**
     * deletes Customer
     * @param customer
     * @throws SQLException if customer has appointments
     */
    public static void deleteCustomer(Customers customer) throws SQLException {
        if(getCustomerAppointments(customer) == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to delete Customer?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                String sql = "DELETE FROM customers WHERE Customer_ID = ?";
                PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
                ps.setInt(1, customer.getCustomerID());
                ps.executeUpdate();
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setContentText(customer.getName()+" "+"has been deleted.");
                alert2.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Customer's appointments must be deleted before deleting customer.");
            alert.showAndWait();
        }
    }
}