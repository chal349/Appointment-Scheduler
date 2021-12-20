package model;

public class Countries {
    private int countryID;
    private String countryName;

    public Countries(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    public int getCountryID() {
        return countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    public String toString(){
        return getCountryName();
    }

}
