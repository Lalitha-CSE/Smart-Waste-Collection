package com.smartwaste.service;

import com.smartwaste.algorithm.Graph;
import com.smartwaste.algorithm.AStar;
import java.util.List;

public class RouteService {
    private Graph graph;

    public RouteService() {
        graph = new Graph();

        // Build the road network with bidirectional roads
        // Each addRoad call adds the connection in both directions
        
        graph.addRoad("Main Gate", "Library", 2.0);
        graph.addRoad("Library", "Canteen", 3.0);
        graph.addRoad("Main Gate", "Canteen", 6.0);
        graph.addRoad("Canteen", "Parking", 2.0);
        graph.addRoad("Library", "Parking", 5.0);
        graph.addRoad("Parking", "Block A", 3.0);
        graph.addRoad("Block A", "Block B", 2.0);
    }

    /**
     * Find the optimal route between two locations using A* algorithm.
     * 
     * @param start Starting location
     * @param destination Target location
     * @return List of location names representing the path from start to destination.
     *         Empty list if no path exists.
     */
    public List<String> findRoute(String start, String destination) {
        AStar aStar = new AStar();
        return aStar.findPath(graph, start, destination);
    }

    /**
     * Get the underlying road graph.
     * Useful for displaying the network or adding more roads.
     */
    public Graph getGraph() {
        return graph;
    }
}