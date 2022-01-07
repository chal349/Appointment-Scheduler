package model;

/**
 * @author Corey Hall
 */

/**
 * Contacts Class and variables- contains methods used in controller classes.
 */
public class Contacts {
    private int contactID;
    private String name;
    private String email;

    /**
     * Contacts Constructor
     * @param id
     * @param name
     * @param email
     */
    public Contacts(int id, String name, String email) {
        this.contactID = id;
        this.name = name;
        this.email = email;
    }

    // GETTERS AND SETTERS

    /**
     * gets contactID
     * @return contactID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * gets name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * gets email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * gets the string value of contactID and name for ContactID combo boxes
     * @return contactID + name
     */
    @Override
    public String toString(){
        return (contactID + " - " + name);
    }
}
