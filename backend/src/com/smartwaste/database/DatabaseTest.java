package com.smartwaste.database;

import java.sql.Connection;

/**
 * Simple test class to verify MySQL database connection.
 * Uses the DatabaseConnection utility class.
 */
public class DatabaseTest {
    public static void main(String[] args) {
        System.out.println("Testing database connection...");
        
        // Get connection from the utility class
        Connection connection = DatabaseConnection.getConnection();
        
        if (connection != null) {
            System.out.println("Database connection successful!");
            
            // Close the connection to avoid leaks
            try {
                connection.close();
                System.out.println("Connection closed properly.");
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        } else {
            System.out.println("Database connection failed.");
            System.out.println("Make sure:");
            System.out.println("  1. MySQL server is running");
            System.out.println("  2. Database 'smart_waste' exists (run smart_waste.sql)");
            System.out.println("  3. Username/password in DatabaseConnection.java are correct");
            System.out.println("  4. MySQL JDBC driver (mysql-connector-j) is in classpath");
        }
    }
}