package DBconnection;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DBAppointments {


    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> allList = FXCollections.observableArrayList();

        try{
            allList.clear();
            String sql = "Select Appointment_ID, Title, Description, Location, Type, Start, End, Contact_ID, Customer_ID, User_ID FROM Appointments";
            PreparedStatement PS = JDBC.getConnection().prepareStatement(sql);
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                int appointmentID = RS.getInt("Appointment_ID");
                String Title = RS.getString("Title");
                String Description = RS.getString("Description");
                String Location = RS.getString("Location");
                String Type = RS.getString("Type");
                LocalDateTime Start = RS.getTimestamp("Start").toLocalDateTime();
                LocalDateTime End = RS.getTimestamp("End").toLocalDateTime();
                int Customer_ID = RS.getInt("Contact_ID");
                int User_ID = RS.getInt("User_ID");
                int Contact_ID = RS.getInt("Customer_ID");
                Appointments appointments = new Appointments(appointmentID, Title, Description, Location, Type, Start, End, Contact_ID, Customer_ID, User_ID);
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
            monthList.clear();
            String sql = "Select Appointment_ID, Title, Description, Location, Type, Start, End, Contact_ID, Customer_ID, User_ID FROM Appointments WHERE month(Start) = month(now())";
            PreparedStatement PS = JDBC.getConnection().prepareStatement(sql);
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                int appointmentID = RS.getInt("Appointment_ID");
                String Title = RS.getString("Title");
                String Description = RS.getString("Description");
                String Location = RS.getString("Location");
                String Type = RS.getString("Type");
                LocalDateTime Start = RS.getTimestamp("Start").toLocalDateTime();
                LocalDateTime End = RS.getTimestamp("End").toLocalDateTime();
                int Customer_ID = RS.getInt("Contact_ID");
                int User_ID = RS.getInt("User_ID");
                int Contact_ID = RS.getInt("Customer_ID");
                Appointments appointments = new Appointments(appointmentID, Title, Description, Location, Type, Start, End, Contact_ID, Customer_ID, User_ID);
                monthList.add(appointments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthList;
    }
}
