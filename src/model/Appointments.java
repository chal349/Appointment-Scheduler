package model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;
import java.util.TimeZone;

public class Appointments {
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerID;
    private int userID;
    private int contactID;

    public Appointments(int appointmentID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

  /*  public static ObservableList<LocalTime> getAllTimes() {
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
*/

}
