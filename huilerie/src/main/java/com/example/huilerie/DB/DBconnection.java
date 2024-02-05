package com.example.huilerie.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class DBconnection {
    public static Connection bdConnection() {
        Connection con = null;

        try {
            // changer le nom de server
            String url = "jdbc:sqlserver://LARIBI\\SQLEXPRESS:1433;databaseName=huilerie;integratedSecurity=true;trustServerCertificate=true";
            con = DriverManager.getConnection(url);
        }
        catch ( SQLException ex) {
            System.err.println("ConnectionDb : "+ex.getMessage());
        }

        return  con ;

    }
}