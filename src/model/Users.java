package model;

public class Users {
    private int userID;
    private static String username;
    private String password;

    public Users(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public static String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
