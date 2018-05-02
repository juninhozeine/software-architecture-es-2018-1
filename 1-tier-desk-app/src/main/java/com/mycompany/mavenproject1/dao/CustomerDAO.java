package com.mycompany.mavenproject1.dao;

import java.util.Set;

import com.mycompany.mavenproject1.data.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class CustomerDAO {

    public void create(Customer customer) throws Exception {
        try {

            String sql = "INSERT INTO customer (name, phone, age, country_id, creditLimit) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = DBConnectSingleton.getInstance().prepareStatement(sql);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getPhone());
            statement.setInt(3, customer.getAge());
            statement.setInt(4, customer.getCountry().getId());
            statement.setDouble(5, customer.getCreditLimit());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted == 0) {
                throw new RuntimeException("Customer could not be persisted!");
            }

        } catch (SQLException ex) {
            throw new Exception(ex);

        }
    }

    public Customer readById(int id) {
        return this.readAll().
                stream().
                filter(
                        customer -> customer.getId() == id).
                findAny().
                get();
    }

    public Customer readByName(String name) {
        return this.readAll().
                stream().
                filter(
                        customer -> customer.getName().
                                equalsIgnoreCase(name)).
                findAny().
                get();
    }

    public Set<Customer> readAll() {

        Set<Customer> resultSet = new HashSet<>();

        try {

            String sql = "SELECT * FROM customer";

            Statement statement = DBConnectSingleton.getInstance().createStatement();
            ResultSet result = statement.executeQuery(sql);

            Customer customer = null;

            while (result.next()) {
                customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setAge(result.getInt("age"));
                customer.setName(result.getString("name"));
                customer.setCountry(new CountryDAO().readById(result.getInt("country_id")));
                customer.setPhone(result.getString("phone"));
                customer.setCreditLimit(result.getDouble("creditLimit"));
                
                resultSet.add(customer);

            }

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            // Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultSet;
    }

    public void update(Customer newCustomer, String name) throws Exception {
        try {

            String sql = "UPDATE customer SET name=?, age=?, country_id=?, phone=?, creditLimit=? WHERE name like ?";

            PreparedStatement statement = DBConnectSingleton.getInstance().prepareStatement(sql);
            statement.setString(1, newCustomer.getName());
            statement.setInt(2, newCustomer.getAge());
            statement.setInt(3, newCustomer.getCountry().getId());
            statement.setString(4, newCustomer.getPhone());
            statement.setDouble(5, newCustomer.getCreditLimit());
            statement.setString(6, name);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted == 0) {
                throw new RuntimeException("Customer could not be updated!");
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    public void delete(String name) {
        try {

            String sql = "DELETE FROM customer WHERE name like ?";

            PreparedStatement statement = DBConnectSingleton.getInstance().prepareStatement(sql);
            statement.setString(1, name);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted == 0) {
                throw new RuntimeException("Customer could not be deleted!");
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

}
