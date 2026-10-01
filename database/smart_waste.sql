-- Smart Waste Collection Database Schema
-- Simple schema for B.Tech class project

-- Create database
CREATE DATABASE IF NOT EXISTS smart_waste;
USE smart_waste;

-- ========================================
-- Bins Table
-- ========================================
CREATE TABLE IF NOT EXISTS bins (
    bin_id VARCHAR(20) PRIMARY KEY,
    location VARCHAR(100) NOT NULL,
    current_fill DECIMAL(5,2) NOT NULL,
    predicted_fill DECIMAL(5,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    last_collected VARCHAR(50) NOT NULL
);

-- ========================================
-- Trucks Table
-- ========================================
CREATE TABLE IF NOT EXISTS trucks (
    truck_id VARCHAR(20) PRIMARY KEY,
    driver VARCHAR(100) NOT NULL,
    capacity DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    current_route VARCHAR(100)
);

-- ========================================
-- Sample Data: Bins
-- ========================================
INSERT INTO bins (bin_id, location, current_fill, predicted_fill, status, last_collected) VALUES
('B001', 'Main Gate', 92, 97, 'Critical', 'Today'),
('B002', 'Library', 76, 84, 'Warning', 'Yesterday'),
('B003', 'Canteen', 45, 62, 'Normal', 'Today'),
('B004', 'Block A', 88, 94, 'Critical', '2 days ago'),
('B005', 'Block B', 55, 70, 'Normal', 'Yesterday'),
('B006', 'Parking', 81, 89, 'Critical', '2 days ago');

-- ========================================
-- Sample Data: Trucks
-- ========================================
INSERT INTO trucks (truck_id, driver, capacity, status, current_route) VALUES
('T001', 'Ravi Kumar', 5000, 'Available', NULL),
('T002', 'Suresh', 5000, 'On Route', 'Route A'),
('T003', 'Mahesh', 3000, 'Available', NULL),
('T004', 'Ramesh', 5000, 'On Route', 'Route B'),
('T005', 'Arjun', 3000, 'Maintenance', NULL);

-- ========================================
-- Verify data
-- ========================================
-- SELECT * FROM bins;
-- SELECT * FROM trucks;