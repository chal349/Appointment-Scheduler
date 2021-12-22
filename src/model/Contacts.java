package model;

public class Contacts {
    private int contactID;
    private String name;
    private String email;

    public Contacts(int id, String name, String email) {
        this.contactID = id;
        this.name = name;
        this.email = email;
    }

    public int getContactID() {
        return contactID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString(){
        return String.valueOf(contactID + " - " + name);
    }
}
