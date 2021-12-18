package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customers {
    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private int Division_ID;

    public Customers(int customerID, String name, String address, String postalCode, String phone, int divisionID) {
        Customer_ID = customerID;
        Customer_Name = name;
        Address = address;
        Postal_Code = postalCode;
        Phone = phone;
        Division_ID = divisionID;
    }

    public int getCustomerID() {
        return Customer_ID;
    }

    public String getName() {
        return Customer_Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getPostalCode() {
        return Postal_Code;
    }

    public String getPhone() {
        return Phone;
    }

    public int getDivisionID() {
        return Division_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setPostal_Code(String postal_Code) {
        Postal_Code = postal_Code;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }
}
