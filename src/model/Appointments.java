package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Appointments {
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private LocalDateTime Start;
    private LocalDateTime End;
    private int Customer_ID;
    private int User_ID;
    private int Contact_ID;


    public Appointments(int appointmentID, String title, String description,
                        String location, String type, LocalDateTime start,
                        LocalDateTime end, int contact, int customerID, int userID) {
        Appointment_ID = appointmentID;
        Title = title;
        Description = description;
        Location = location;
        Type = type;
        Start = start;
        End = end;
        Customer_ID = customerID;
        User_ID = userID;
        Contact_ID = contact;
    }

    public int getAppointment_ID() {
        return Appointment_ID;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getLocation() {
        return Location;
    }

    public String getType() {
        return Type;
    }

    public LocalDateTime getStart() {
        return Start;
    }

    public LocalDateTime getEnd() {
        return End;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public int getContact_ID() {
        return Contact_ID;
    }
}
