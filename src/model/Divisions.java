package model;

/**
 * @author Corey Hall
 */

/**
 * Divisions Class and variables- contains methods used in controller classes.
 */
public class Divisions {
    private int divisionID;
    private String name;
    private int countryID;

    /**
     * Divisions Constructor
     * @param divisionID
     * @param name
     * @param countryID
     */
    public Divisions(int divisionID, String name, int countryID) {
        this.divisionID = divisionID;
        this.name = name;
        this.countryID = countryID;
    }

    //GETTERS AND SETTERS

    /**
     * gets divisionID
     * @return divisionID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * gets divisionName
     * @return divisionName
     */
    public String getDivisionName() {
        return name;
    }

    /**
     * gets countryID
     * @return countryID
     */
    public int getCountryID() {
        return countryID;
    }

    @Override
    public String toString(){
        return getDivisionName();
    }
}
