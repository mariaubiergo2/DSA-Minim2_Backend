package edu.upc.dsa.CRUD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactorySession {
    public FactorySession() {
    }

    public static Session openSession(String url, String user, String password) {
        Connection conn = getConnection(url, user, password);
        return new SessionImpl(conn);
    }

    private static Connection getConnection(String url, String user, String password) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }
}
