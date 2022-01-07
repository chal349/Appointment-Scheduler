package model;

public class Users {
    private int userID;
    private String username;
    private String password;

    public Users(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public Users(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString(){
        return userID + " - " + username;
    }
}
