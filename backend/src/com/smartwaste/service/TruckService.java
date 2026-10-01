package com.smartwaste.service;

import com.smartwaste.database.TruckRepository;
import com.smartwaste.model.Truck;
import java.util.List;

/**
 * Service layer for Truck operations.
 * 
 * The Service layer contains business logic and acts as an intermediary
 * between the controller/API layer and the repository/database layer.
 * 
 * TruckService uses TruckRepository to perform database operations.
 * This separation keeps business logic separate from database code,
 * making the application easier to maintain and test.
 */
public class TruckService {
    private TruckRepository truckRepository;

    public TruckService() {
        truckRepository = new TruckRepository();
    }

    /**
     * Get all trucks from the system.
     * Delegates to the repository layer.
     */
    public List<Truck> getAllTrucks() {
        return truckRepository.getAllTrucks();
    }

    /**
     * Get a specific truck by its ID.
     * Delegates to the repository layer.
     */
    public Truck getTruckById(String truckId) {
        return truckRepository.getTruckById(truckId);
    }

    /**
     * Add a new truck to the system.
     * Delegates to the repository layer.
     */
    public boolean addTruck(Truck truck) {
        return truckRepository.addTruck(truck);
    }

    /**
     * Get the total number of trucks in the system.
     */
    public int getTotalTrucks() {
        return getAllTrucks().size();
    }
}