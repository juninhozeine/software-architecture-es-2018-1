package com.mycompany.mavenproject1.dao;

import java.util.Set;

import com.mycompany.mavenproject1.data.Country;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class CountryDAO {



    public void create(Country country) throws Exception {
        try {

            String sql = "INSERT INTO country (name, acronym, digits) VALUES (?, ?, ?)";

            PreparedStatement statement = DBConnectSingleton.getInstance().prepareStatement(sql);
            statement.setString(1, country.getName());
            statement.setString(2, country.getAcronym());
            statement.setInt(3, country.getDigits());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted == 0) {
                throw new RuntimeException("Country could not be persisted!");
            }

        } catch (SQLException ex) {
            throw new Exception(ex);

        }
    }

    public Country readById(int id) {
        return this.readAll().
                stream().
                filter(
                        country -> country.getId() == id).
                findAny().
                get();
    }

    public Country readByAcronym(String acronym) {
        return this.readAll().
                stream().
                filter(
                        country -> country.getAcronym().
                                equalsIgnoreCase(acronym)).
                findAny().
                get();
    }

    public Country readByName(String name) {
        return this.readAll().
                stream().
                filter(
                        country -> country.getName().
                                equalsIgnoreCase(name)).
                findAny().
                get();
    }

    public Set<Country> readAll() {
        Set<Country> resultSet = new HashSet<>();

        try {

            String sql = "SELECT * FROM country";

            Statement statement = DBConnectSingleton.getInstance().createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                resultSet.add(new Country(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("acronym"),
                        result.getInt("digits")
                ));
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }

        return resultSet;
    }

    public void update(Country newCountry, String name) {
        try {

            String sql = "UPDATE country SET name=?, acronym=?, digits=? WHERE name like ?";

            PreparedStatement statement = DBConnectSingleton.getInstance().prepareStatement(sql);
            statement.setString(1, newCountry.getName());
            statement.setString(2, newCountry.getAcronym());
            statement.setInt(3, newCountry.getDigits());
            statement.setString(4, name);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted == 0) {
                throw new RuntimeException("Country could not be updated!");
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    public void delete(String name) {
        try {

            String sql = "DELETE FROM country WHERE name like ?";

            PreparedStatement statement = DBConnectSingleton.getInstance().prepareStatement(sql);
            statement.setString(1, name);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted == 0) {
                throw new RuntimeException("Country could not be deleted!");
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

}
