package model;

/**
 * @author Corey Hall
 */

/**
 * Countries Class and variables- contains methods used in controller classes.
 */
public class Countries {
    private int countryID;
    private String countryName;

    /**
     * Countries Constructor
     * @param countryID
     * @param countryName
     */
    public Countries(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    // GETTERS AND SETTERS

    /**
     * gets countryID
     * @return countryID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * gets countryName
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }

    @Override
    public String toString(){
        return getCountryName();
    }

}
