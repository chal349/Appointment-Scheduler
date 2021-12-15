package model;

public class Customers {
    private int customerID;
    private String name;
    private String address;
    private String postalCode;
    private String Phone;
    private int DivisionID;

    public Customers(int customerID, String name, String address, String postalCode, String phone, int divisionID) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        Phone = phone;
        DivisionID = divisionID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return Phone;
    }

    public int getDivisionID() {
        return DivisionID;
    }
}
