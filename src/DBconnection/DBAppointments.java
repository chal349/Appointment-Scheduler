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
            allList.clear();
            String sql = "Select Appointment_ID, Title, Description, Location, Type, Start, End, Contact_ID, Customer_ID, User_ID FROM Appointments";
            PreparedStatement PS = JDBC.getConnection().prepareStatement(sql);
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                int appointmentID = RS.getInt("Appointment_ID");
                String title = RS.getString("Title");
                String description = RS.getString("Description");
                String location = RS.getString("Location");
                String type = RS.getString("Type");

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                format.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date start = format.parse(RS.getString("Start"));
                Date end = format.parse(RS.getString("End"));

                int customerID = RS.getInt("Contact_ID");
                int userID = RS.getInt("User_ID");
                int contactID = RS.getInt("Customer_ID");
                Appointments appointments = new Appointments(appointmentID, title, description, location, type, start, end, contactID, customerID, userID);
                allList.add(appointments);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return allList;
    }

}
