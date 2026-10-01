package com.smartwaste.database;

import com.smartwaste.model.Bin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC Repository for Bin operations.
 * 
 * A Repository handles database operations (CRUD) for a specific entity.
 * This class uses JDBC (Java Database Connectivity) to read and write
 * bin data to/from the MySQL database.
 */
public class BinRepository {

    /**
     * Get all bins from the database.
     * 
     * @return List of all Bin objects, or empty list if error occurs
     */
    public List<Bin> getAllBins() {
        List<Bin> bins = new ArrayList<>();
        String sql = "SELECT * FROM bins";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Bin bin = mapRowToBin(rs);
                bins.add(bin);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching bins: " + e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Database connection is null. Check DatabaseConnection configuration.");
        }

        return bins;
    }

    /**
     * Get a specific bin by its ID.
     * 
     * @param binId The bin ID to search for
     * @return Bin object if found, null otherwise
     */
    public Bin getBinById(String binId) {
        String sql = "SELECT * FROM bins WHERE bin_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, binId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToBin(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching bin by ID: " + e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Database connection is null. Check DatabaseConnection configuration.");
        }

        return null;
    }

    /**
     * Add a new bin to the database.
     * 
     * @param bin The Bin object to insert
     * @return true if insertion succeeded, false otherwise
     */
    public boolean addBin(Bin bin) {
        String sql = "INSERT INTO bins (bin_id, location, current_fill, predicted_fill, status, last_collected) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bin.getBinId());
            stmt.setString(2, bin.getLocation());
            stmt.setDouble(3, bin.getCurrentFill());
            stmt.setDouble(4, bin.getPredictedFill());
            stmt.setString(5, bin.getStatus());
            stmt.setString(6, bin.getLastCollected());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error adding bin: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (NullPointerException e) {
            System.err.println("Database connection is null. Check DatabaseConnection configuration.");
            return false;
        }
    }

    /**
     * Helper method to convert a database row to a Bin object.
     * Maps database columns to Bin fields.
     */
    private Bin mapRowToBin(ResultSet rs) throws SQLException {
        return new Bin(
            rs.getString("bin_id"),
            rs.getString("location"),
            rs.getDouble("current_fill"),
            rs.getDouble("predicted_fill"),
            rs.getString("status"),
            rs.getString("last_collected")
        );
    }
}