package com.smartwaste.algorithm;

import java.util.*;

public class AStar {

    public List<String> findPath(Graph graph, String start, String goal) {
        if (start == null || goal == null) {
            return new ArrayList<>();
        }

        if (start.equals(goal)) {
            List<String> singleNodePath = new ArrayList<>();
            singleNodePath.add(start);
            return singleNodePath;
        }

        // Open set: nodes to be evaluated, ordered by f-score (lowest first)
        java.util.PriorityQueue<Node> openSet =
        new java.util.PriorityQueue<>(Comparator.comparingDouble(n -> n.f));
        
        // Maps to store g-score and where we came from
        Map<String, Double> gScore = new HashMap<>();
        Map<String, String> cameFrom = new HashMap<>();

        // Initialize start node
        gScore.put(start, 0.0);
        double h = heuristic(start, goal);
        openSet.add(new Node(start, 0.0, h, 0.0 + h));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.name.equals(goal)) {
                // Goal reached - reconstruct path
                return reconstructPath(cameFrom, current.name);
            }

            // Explore neighbors
            for (Graph.Edge edge : graph.getNeighbors(current.name)) {
                String neighbor = edge.getDestination();
                double tentativeG = current.g + edge.getDistance();

                // If this path to neighbor is better than any previous one
                if (!gScore.containsKey(neighbor) || tentativeG < gScore.get(neighbor)) {
                    cameFrom.put(neighbor, current.name);
                    gScore.put(neighbor, tentativeG);
                    double neighborH = heuristic(neighbor, goal);
                    double neighborF = tentativeG + neighborH;
                    openSet.add(new Node(neighbor, tentativeG, neighborH, neighborF));
                }
            }
        }

        // No path found
        return new ArrayList<>();
    }

    private List<String> reconstructPath(Map<String, String> cameFrom, String current) {
        List<String> path = new ArrayList<>();
        path.add(current);
        
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(current);
        }
        
        Collections.reverse(path);
        return path;
    }

    /**
     * Heuristic function estimating distance from current to goal.
     * Currently returns 0, making A* behave like Dijkstra's algorithm.
     * 
     * When geographic coordinates (latitude/longitude) are available for each location,
     * this can be replaced with a proper Euclidean or Haversine distance:
     * return haversineDistance(currentLat, currentLon, goalLat, goalLon);
     */
    private double heuristic(String current, String goal) {
        // TODO: Implement with real coordinates
        // For now, returning 0 makes this equivalent to Dijkstra's algorithm
        return 0.0;
    }

    // Internal node class for the priority queue
    private static class Node {
        String name;
        double g; // Actual distance from start to this node
        double h; // Heuristic estimate from this node to goal
        double f; // Total estimated cost (g + h)

        Node(String name, double g, double h, double f) {
            this.name = name;
            this.g = g;
            this.h = h;
            this.f = f;
        }
    }
}