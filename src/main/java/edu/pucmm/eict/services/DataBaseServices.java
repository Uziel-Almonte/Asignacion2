package edu.pucmm.eict.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseServices {

    private static DataBaseServices instance;
    private String URL = "jdbc:h2:file:./database/test;AUTO_SERVER=TRUE";

    private DataBaseServices(){
        registerDriver();
    }

    public static DataBaseServices getInstance(){
        if(instance==null){
            instance = new DataBaseServices();
        }
        return instance;
    }

    private void registerDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, "sa", "");
    }
}