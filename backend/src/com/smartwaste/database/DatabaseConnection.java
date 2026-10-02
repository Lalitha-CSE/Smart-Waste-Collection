package com.smartwaste.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Simple JDBC connection utility for Smart Waste Collection.
 * 
 * JDBC (Java Database Connectivity) connects Java applications to MySQL database.
 * The URL points to the 'smart_waste' database on localhost port 3306.
 * 
 * NOTE: Username and password ('root'/'root') may need to be changed
 * depending on your MySQL installation configuration.
 */
public class DatabaseConnection {
    
    private static final String URL =
        System.getenv().getOrDefault(
                "DB_URL",
                "jdbc:mysql://localhost:3306/smart_waste"
        );

    private static final String USER =
        System.getenv().getOrDefault(
                "DB_USER",
                "root"
        );

    private static final String PASSWORD =
        System.getenv().getOrDefault(
                "DB_PASSWORD",
                "YOUR_MYSQL_PASSWORD"
        );
    /**
     * Get a connection to the MySQL database.
     * 
     * @return Connection object, or null if connection fails
     */
    public static Connection getConnection() {
        try {
            // Load MySQL JDBC driver (optional in modern JDBC, but good practice)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
            
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Make sure mysql-connector-j is in classpath.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Database connection failed. Check if MySQL is running and credentials are correct.");
            System.err.println("URL: " + URL);
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}