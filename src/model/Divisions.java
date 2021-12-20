package model;

public class Divisions {
    private int divisionID;
    private String name;
    private int countryID;

    public Divisions(int divisionID, String name, int countryID) {
        this.divisionID = divisionID;
        this.name = name;
        this.countryID = countryID;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public String getDivisionName() {
        return name;
    }

    public int getCountryID() {
        return countryID;
    }

    @Override
    public String toString(){
        return getDivisionName();
    }
}
