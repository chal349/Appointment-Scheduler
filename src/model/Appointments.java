package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @author Corey Hall
 */

/**
 * Appointments Class and variables- contains methods used in controller classes.
 */
public class Appointments {
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int contactID;
    private int customerID;
    private int userID;

    /**
     * Appointments Constructor
     * @param appointmentID
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param contactID
     * @param customerID
     * @param userID
     */
    public Appointments(int appointmentID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int contactID, int customerID, int userID ) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.contactID = contactID;
        this.customerID = customerID;
        this.userID = userID;
    }


    /**
     * additional Appointments Constructor used by Login controller to check for appointments within 15 mins.
     * @param appointmentID
     * @param start
     */
    public Appointments(int appointmentID, LocalDateTime start){
        this.appointmentID = appointmentID;
        this.start = start;
    }

    // GETTERS AND SETTERS

    /**
     * gets appointmentID
     * @return appointmentID
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * Sets appointmentID
     * @param appointmentID
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * gets title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * gets location
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * sets location
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * gets type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * sets type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * gets start time and date
     * @return start time and date
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * sets start time and date
     * @param start time and date
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * gets end time and date
     * @return end time and date
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * sets end time and date
     * @param end time and date
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * gets customerID
     * @return customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * sets customerID
     * @param customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * gets userID
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * sets userID
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * gets contactID
     * @return contactID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * sets contactID
     * @param contactID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    //ADDITIONAL METHODS

    /**
     * method used by Lambda to get and format start time for the tableviews used on Appointments, Reports, and Customers screens
     * @return formatted start time
     */
    public StringProperty getStartFormatted() {
        StringProperty start = new SimpleStringProperty();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm");
        start.setValue(this.start.format(formatter) + " " + ZoneId.systemDefault().getDisplayName(TextStyle.SHORT_STANDALONE, Locale.getDefault()));
        return start;
    }

    /**
     * method used by Lambda to get and format end time for the tableviews used on Appointments, Reports, and Customers screens
     * @return formatted end time
     */
    public StringProperty getEndFormatted() {
        StringProperty end = new SimpleStringProperty();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm");
        end.setValue(this.end.format(formatter)+ " " + ZoneId.systemDefault().getDisplayName(TextStyle.SHORT_STANDALONE, Locale.getDefault()));
        return end;
    }
}
