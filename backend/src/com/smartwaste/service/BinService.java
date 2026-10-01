package com.smartwaste.service;

import com.smartwaste.model.Bin;
import com.smartwaste.algorithm.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class BinService {
    private List<Bin> bins;

    public BinService() {
        bins = new ArrayList<>();
    }

    /**
     * Add a new bin to the service.
     */
    public void addBin(Bin bin) {
        bins.add(bin);
    }

    /**
     * Get all bins stored in the service.
     */
    public List<Bin> getAllBins() {
        return new ArrayList<>(bins); // Return a copy to prevent external modification
    }

    /**
     * Find a bin by its ID.
     * Returns the bin if found, or null if not found.
     */
    public Bin getBinById(String binId) {
        for (Bin bin : bins) {
            if (bin.getBinId().equals(binId)) {
                return bin;
            }
        }
        return null;
    }

    /**
     * Get all bins sorted by priority using the custom PriorityQueue (Max-Heap).
     * Higher predictedFill = higher priority.
     */
    public List<Bin> getPriorityBins() {
        PriorityQueue priorityQueue = new PriorityQueue();
        
        // Add all bins to the priority queue
        for (Bin bin : bins) {
            priorityQueue.add(bin);
        }

        // Poll all bins in priority order
        List<Bin> priorityList = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            priorityList.add(priorityQueue.poll());
        }
        
        return priorityList;
    }

    /**
     * Get the total number of bins.
     */
    public int getTotalBins() {
        return bins.size();
    }
}