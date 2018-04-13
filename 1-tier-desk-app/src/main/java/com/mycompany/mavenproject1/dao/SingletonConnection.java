/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SingletonConnection {
    private static String dbURL = "jdbc:mysql://localhost:3306/banco";
    private static String username = "root";
    private static String password = "utfpr";
    
    private static volatile SingletonConnection instance;
    
    private Connection conn;
    
    private SingletonConnection() throws ClassNotFoundException, Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);

        }catch (SQLException ex){
            throw new Exception(ex);
            
        }         
    }
    
    public static SingletonConnection getIntance() throws Exception{
            synchronized(SingletonConnection.class){                
                if(instance == null){
                    instance = new SingletonConnection();
                }
            } 
            return instance;
    }
    
    public Connection getConnection(){
        if(conn != null){
            return conn;
        }else{
            throw new NullPointerException("Variavel conn não iniciada");
        }
    }

    public void closeConecction(Connection conn){
        try{
            if (conn != null){
                conn.close();
            }
        }catch(SQLException ex){
            throw new RuntimeException("Erro pra fechar conexão: ", ex);
        }
    }
}
