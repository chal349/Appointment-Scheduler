package DBconnection;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDivisions {

    public static ObservableList<Divisions> getAllDivisions() {
        ObservableList<Divisions> list = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String name = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");
                Divisions division = new Divisions(divisionID, name, countryID);
                list.add(division);
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return list;
    }
}
