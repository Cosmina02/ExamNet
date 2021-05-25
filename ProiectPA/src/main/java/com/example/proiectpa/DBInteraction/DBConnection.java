package com.example.proiectpa.DBInteraction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static  DBConnection dbConnection;
    private Connection conn;

    public DBConnection(String path) {

        try {
            String url="jdbc:sqlite:"+path;
            conn= DriverManager.getConnection(url);
            System.out.println("Connected to SQLite!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static DBConnection getInstance(String path){
        if(dbConnection==null){
            dbConnection=new DBConnection(path);
        }
        return dbConnection;
    }
    public Connection getConn(){
        return conn;
    }
}

