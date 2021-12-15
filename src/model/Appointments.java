package model;

import java.time.LocalDateTime;

public class Appointments {
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int contact;
    private int customerID;
    private int userID;


    public Appointments(int appointmentID, String title, String description,
                        String location, String type, LocalDateTime start,
                        LocalDateTime end, int contact, int customerID, int userID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.contact = contact;
        this.customerID = customerID;
        this.userID = userID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public int getContact() {
        return contact;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getUserID() {
        return userID;
    }
}
