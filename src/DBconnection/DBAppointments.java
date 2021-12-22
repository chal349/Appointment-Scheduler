package DBconnection;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

public class DBAppointments {


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
                int customerID = rs.getInt("Contact_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Customer_ID");
                Appointments appointments = new Appointments(appointmentID, title, description, location, type, start, end, contactID, customerID, userID);
                allList.add(appointments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allList;
    }


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
                int customerID = rs.getInt("Contact_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Customer_ID");
                Appointments appointments = new Appointments(appointmentID, title, description, location, type, start, end, contactID, customerID, userID);
                monthList.add(appointments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthList;
    }

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
                int customerID = rs.getInt("Contact_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Customer_ID");
                Appointments appointments = new Appointments(appointmentID, title, description, location, type, start, end, contactID, customerID, userID);
                weekList.add(appointments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weekList;
    }

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

}
