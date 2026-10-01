package com.smartwaste.database;

import com.smartwaste.model.Truck;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC Repository for Truck operations.
 * 
 * A Repository handles database operations (CRUD) for a specific entity.
 * This class uses JDBC (Java Database Connectivity) to read and write
 * truck data to/from the MySQL database.
 */
public class TruckRepository {

    /**
     * Get all trucks from the database.
     * 
     * @return List of all Truck objects, or empty list if error occurs
     */
    public List<Truck> getAllTrucks() {
        List<Truck> trucks = new ArrayList<>();
        String sql = "SELECT * FROM trucks";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Truck truck = mapRowToTruck(rs);
                trucks.add(truck);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching trucks: " + e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Database connection is null. Check DatabaseConnection configuration.");
        }

        return trucks;
    }

    /**
     * Get a specific truck by its ID.
     * 
     * @param truckId The truck ID to search for
     * @return Truck object if found, null otherwise
     */
    public Truck getTruckById(String truckId) {
        String sql = "SELECT * FROM trucks WHERE truck_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, truckId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToTruck(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching truck by ID: " + e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Database connection is null. Check DatabaseConnection configuration.");
        }

        return null;
    }

    /**
     * Add a new truck to the database.
     * 
     * @param truck The Truck object to insert
     * @return true if insertion succeeded, false otherwise
     */
    public boolean addTruck(Truck truck) {
        String sql = "INSERT INTO trucks (truck_id, driver, capacity, status, current_route) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, truck.getTruckId());
            stmt.setString(2, truck.getDriver());
            stmt.setDouble(3, truck.getCapacity());
            stmt.setString(4, truck.getStatus());
            stmt.setString(5, truck.getCurrentRoute());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error adding truck: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (NullPointerException e) {
            System.err.println("Database connection is null. Check DatabaseConnection configuration.");
            return false;
        }
    }

    /**
     * Helper method to convert a database row to a Truck object.
     * Maps database columns to Truck fields.
     */
    private Truck mapRowToTruck(ResultSet rs) throws SQLException {
        return new Truck(
            rs.getString("truck_id"),
            rs.getString("driver"),
            rs.getDouble("capacity"),
            rs.getString("status"),
            rs.getString("current_route")
        );
    }
}