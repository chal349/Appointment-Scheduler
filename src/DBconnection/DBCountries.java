package DBconnection;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Corey Hall
 */

/**
 * DBCountries Class - contains methods used to access database information.
 */
public class DBCountries {

    /**
     * getAllCountries
     * @return all countries
     */
    public static ObservableList<Countries>getAllCountries() {
        ObservableList<Countries> list = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM countries";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Countries country = new Countries(countryID, countryName);
                list.add(country);
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return list;
    }
}
