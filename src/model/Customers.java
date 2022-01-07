package model;

/**
 * @author Corey Hall
 */

/**
 * Customers Class and variables- contains methods used in controller classes.
 */
public class Customers {
    private int customerID;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionID;
    private String divisionName;
    private int countryID;
    private String countryName;

    /**
     * Customers Constructor
     * @param customerID
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionID
     * @param divisionName
     * @param countryID
     * @param countryName
     */
    public Customers(int customerID, String name, String address, String postalCode, String phone, int divisionID, String divisionName, int countryID, String countryName) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
        this.countryName = countryName;
    }

    //GETTERS AND SETTERS

    /**
     * gets countryID
     * @return countryID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * sets countryID
     * @param countryID
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * gets countryName
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * sets countryName
     * @param countryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
     * gets name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * sets address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * gets postalCode
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * sets postalCode
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * gets phone
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * sets phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * gets divisionID
     * @return divisionID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * sets divisionID
     * @param divisionID
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * gets divisionName
     * @return divisionName
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * sets divisionName
     * @param divisionName
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * gets string value of customerID and Name for customer combo boxes
     * @return customerID + name
     */
    @Override
    public String toString(){
        return (customerID + " - " + name);
    }
}
