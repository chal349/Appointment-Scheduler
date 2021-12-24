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
import java.time.*;
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

    public static ObservableList<LocalTime> getAllTimes() {
        ObservableList<LocalTime> timesList = FXCollections.observableArrayList();

        ZoneId EST = ZoneId.of("America/New_York");
        ZoneId local = ZoneId.of(TimeZone.getDefault().getID());

        LocalTime openEST = LocalTime.of(8, 0);
        LocalTime closeEST = LocalTime.of(22,0);

        ZonedDateTime openZoned = ZonedDateTime.of(LocalDate.now(), openEST, EST);
        ZonedDateTime closeZoned = ZonedDateTime.of(LocalDate.now(), closeEST, EST);

        ZonedDateTime OPEN = openZoned.withZoneSameInstant(local);
        ZonedDateTime CLOSED = closeZoned.withZoneSameInstant(local);

        ZonedDateTime time = OPEN.minusMinutes(15);

        Boolean timeRange = time.isBefore(CLOSED);
        while (timeRange = true){
            time = time.plusMinutes(15);
            timesList.add(LocalTime.from(time));
            if(time.isAfter(CLOSED) || time.equals(CLOSED)){
                break;
            }
        }
        return timesList;
    }

    public static void newAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID){
        try{
            String sql = "INSERT INTO appointments VALUES(NULL, ?, ?, ?, ?, ?, ?, now(), 'user', now(), 'user', ?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(1, location);
            ps.setString(1, type);
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

    public static void updateAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID, String appointmentID){
        try{
            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
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
            ps.setString(10, appointmentID);
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
