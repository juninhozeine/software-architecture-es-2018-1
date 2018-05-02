package com.mycompany.mavenproject1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectSingleton {

    private static Connection conn;
    private static DBConnectSingleton instance;
    private String dbURL = "jdbc:mysql://localhost:3306/saapp";
    private String username = "root";
    private String password = "gabriel";

    private DBConnectSingleton() throws SQLException { 
        conn = DriverManager.getConnection(dbURL, username, password);
    }

    public static synchronized Connection getInstance() throws SQLException {
        if (instance == null) 
            instance = new DBConnectSingleton();
        
        return conn;
    }

}
