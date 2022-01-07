package model;

/**
 * @author Corey Hall
 */

/**
 * Users Class and variables- contains methods used in controller classes.
 */
public class Users {
    private int userID;
    private String username;
    private String password;

    /**
     * Users Constructor
     * @param userID
     * @param username
     * @param password
     */
    public Users(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    /**
     * additional Users Constructor used in Login screen
     * @param userID
     */
    public Users(int userID) {
        this.userID = userID;
    }


    //GETTERS AND SETTERS

    /**
     * gets userID
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * gets username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * gets password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * gets string value of userID and username for User combo boxes
     * @return userID + username
     */
    @Override
    public String toString(){
        return userID + " - " + username;
    }
}
