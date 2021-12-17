package DBconnection;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBAppointments {

    public static ObservableList<Appointments> List = FXCollections.observableArrayList();

    public static ObservableList<Appointments> getAllAppointments() {
        return List;
    }

    public static void populateTable() {
        try{
            List.clear();
            String sql = "Select Appointment_ID, Title, Description, Location, Type, Start, End, Contact_ID, Customer_ID, User_ID FROM Appointments";
            PreparedStatement PS = JDBC.getConnection().prepareStatement(sql);
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                int appointmentID = RS.getInt("Appointment_ID");
                String Title = RS.getString("Title");
                String Description = RS.getString("Description");
                String Location = RS.getString("Location");
                String Type = RS.getString("Type");
                Timestamp Start = RS.getTimestamp("Start");
                Timestamp End = RS.getTimestamp("End");
                int Customer_ID = RS.getInt("Contact_ID");
                int User_ID = RS.getInt("User_ID");
                int Contact_ID = RS.getInt("Customer_ID");
                Appointments appointments = new Appointments(appointmentID, Title, Description, Location, Type, Start, End, Contact_ID, Customer_ID, User_ID);
                List.add(appointments);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
