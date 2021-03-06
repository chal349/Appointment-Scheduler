package DBconnection;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Appointments;
import model.Reports;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.Optional;

/**
 * @author Corey Hall
 */

/**
 * DBAppointments Class - contains methods used to access database information.
 */
public class DBAppointments {

    /**
     * getAllAppointments
     * @return all appointments
     */
    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> allList = FXCollections.observableArrayList();

        try{
            String sql = "Select Appointment_ID, Title, Description, Location, Type, Start, End, Contact_ID, Customer_ID, User_ID FROM Appointments";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int contactID = rs.getInt("Contact_ID");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                Appointments appointments = new Appointments(appointmentID, title, description, location, type, start, end, contactID, customerID, userID);
                allList.add(appointments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allList;
    }

    /**
     * getMonthAppointments
     * @return all appointments by month
     */
    public static ObservableList<Appointments> getMonthAppointments() {
        ObservableList<Appointments> monthList = FXCollections.observableArrayList();

        try{
            String sql = "Select Appointment_ID, Title, Description, Location, Type, Start, End, Contact_ID, Customer_ID, User_ID FROM Appointments WHERE MONTH(Start) = MONTH(current_date())";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int contactID = rs.getInt("Contact_ID");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");

                Appointments appointments = new Appointments(appointmentID, title, description, location, type, start, end, contactID, customerID, userID);
                monthList.add(appointments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthList;
    }

    /**
     * getWeekAppointments
     * @return all appointments by week
     */
    public static ObservableList<Appointments> getWeekAppointments() {
        ObservableList<Appointments> weekList = FXCollections.observableArrayList();

        try{
            String sql = "Select Appointment_ID, Title, Description, Location, Type, Start, End, Contact_ID, Customer_ID, User_ID FROM Appointments WHERE WEEK(Start) = WEEK(current_date())";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int contactID = rs.getInt("Contact_ID");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");

                Appointments appointments = new Appointments(appointmentID, title, description, location, type, start, end, contactID, customerID, userID);
                weekList.add(appointments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weekList;
    }

    /**
     * getAllTypes
     * @return all types of appointments
     */
    public static ObservableList<String> getAllTypes() {
        ObservableList<String> typesList = FXCollections.observableArrayList();
        String sql = "Select DISTINCT Type FROM appointments";
        try{
            ResultSet rs = JDBC.getConnection().createStatement().executeQuery(sql);
            while(rs.next()){
                typesList.add(rs.getString("Type"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return typesList;
    }

    /**
     * Creates a newAppointment
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param customerID
     * @param userID
     * @param contactID
     */
    public static void newAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID){
        try{
            String sql = "INSERT INTO appointments VALUES(NULL, ?, ?, ?, ?, ?, ?, now(), 'user', now(), 'user', ?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, customerID);
            ps.setInt(8,userID);
            ps.setInt(9, contactID);
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Updates an appointment
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param customerID
     * @param userID
     * @param contactID
     * @param appointmentID
     */
    public static void updateAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID, String appointmentID){
        try{
            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Contact_ID = ?, Customer_ID = ?, User_ID = ?  WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, contactID);
            ps.setInt(8, customerID);
            ps.setInt(9,userID);
            ps.setString(10, appointmentID);
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * getAllAppointments by specific customer using customerID
     * @param customer_ID
     * @return
     */
    public static ObservableList<Appointments> getAllAppointmentsByCustomer(int customer_ID) {
        ObservableList<Appointments> allList = FXCollections.observableArrayList();

        try{
            String sql = "Select * FROM appointments WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, customer_ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int contactID = rs.getInt("Contact_ID");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");

                Appointments appointments = new Appointments(appointmentID, title, description, location, type, start, end, contactID, customerID, userID);
                allList.add(appointments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allList;
    }

    /**
     * Deletes an appointment from database
     * @param appointment
     * @throws SQLException
     */
    public static void deleteAppointment(Appointments appointment) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to cancel Appointment?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, appointment.getAppointmentID());
            ps.executeUpdate();
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setContentText("Appointment #" + appointment.getAppointmentID() + " - " + appointment.getType() + ", has been canceled.");
            alert2.showAndWait();
        }
    }

    /**
     * checks for upcoming appointments by userID
     * @param userID
     * @return appointments upcoming within next 15 mins
     */
    public static ObservableList<Appointments> checkForUpcomingAppointments(int userID) {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        try{
            String sql = "Select Appointment_ID, Start From appointments WHERE Start <= now() + INTERVAL 15 MINUTE AND Start > now() AND User_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int appointmentID = rs.getInt("Appointment_ID");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                Appointments a = new Appointments(appointmentID, start);
                appointments.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointments;
    }

    /**
     * getAppointments by type and month
     * @return number of appointments sorted by type and month
     */
    public static ObservableList<Reports> getAppointmentsByTypeAndMonth() {
        ObservableList<Reports> list = FXCollections.observableArrayList();

        try{
            String sql = "SELECT monthname(start) AS Month, Type, count(Type) AS Total FROM appointments GROUP BY Month(start), Type;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next()){
                String type = rs.getString("Type");
                String month = rs.getString("Month");
                String total = rs.getString("Total");
                Reports report = new Reports(type, month, total);
                list.add(report);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * getAllAppointments by specific contact selected
     * @param contactSelected
     * @return appointments for chosen contact
     */
    public static ObservableList<Appointments> getAllAppointmentsByContact(int contactSelected) {
        ObservableList<Appointments> contactList = FXCollections.observableArrayList();

        try{
            String sql = "Select * FROM Appointments WHERE Contact_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, contactSelected);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int contactID = rs.getInt("Contact_ID");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                Appointments appointments = new Appointments(appointmentID, title, description, location, type, start, end, contactID, customerID, userID);
                contactList.add(appointments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactList;
    }
}
