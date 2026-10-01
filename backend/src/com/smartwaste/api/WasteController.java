package com.smartwaste.api;

import com.smartwaste.model.Bin;
import com.smartwaste.model.Truck;
import com.smartwaste.service.BinService;
import com.smartwaste.service.RouteService;
import com.smartwaste.service.TruckService;
import java.util.List;

public class WasteController {
    private BinService binService;
    private RouteService routeService;
    private TruckService truckService;

    public WasteController() {
        binService = new BinService();
        routeService = new RouteService();
        truckService = new TruckService();
    }

    /**
     * Add a new bin to the system.
     */
    public void addBin(Bin bin) {
        binService.addBin(bin);
    }

    /**
     * Get all bins in the system.
     */
    public List<Bin> getAllBins() {
        return binService.getAllBins();
    }

    /**
     * Get all bins ordered by priority (highest predicted fill first).
     */
    public List<Bin> getPriorityBins() {
        return binService.getPriorityBins();
    }

    /**
     * Get a specific bin by its ID.
     * Returns null if not found.
     */
    public Bin getBin(String binId) {
        return binService.getBinById(binId);
    }

    /**
     * Find the optimal route between two locations.
     * Uses A* pathfinding on the road graph.
     */
    public List<String> getRoute(String start, String destination) {
        return routeService.findRoute(start, destination);
    }

    /**
     * Get the total number of bins in the system.
     */
    public int getTotalBins() {
        return binService.getTotalBins();
    }

    // ========================================
    // Truck Service Methods
    // ========================================
    // WasteController now connects:
    // - BinService (for bin operations)
    // - TruckService (for truck operations)
    // - RouteService (for route planning)

    /**
     * Get all trucks in the system.
     */
    public List<Truck> getAllTrucks() {
        return truckService.getAllTrucks();
    }

    /**
     * Get a specific truck by its ID.
     * Returns null if not found.
     */
    public Truck getTruckById(String truckId) {
        return truckService.getTruckById(truckId);
    }

    /**
     * Add a new truck to the system.
     */
    public boolean addTruck(Truck truck) {
        return truckService.addTruck(truck);
    }

    /**
     * Get the total number of trucks in the system.
     */
    public int getTotalTrucks() {
        return truckService.getTotalTrucks();
    }
}