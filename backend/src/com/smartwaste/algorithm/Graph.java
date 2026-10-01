package com.smartwaste.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<String, List<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public static class Edge {
        private String destination;
        private double distance;

        public Edge(String destination, double distance) {
            this.destination = destination;
            this.distance = distance;
        }

        public String getDestination() {
            return destination;
        }

        public double getDistance() {
            return distance;
        }

        @Override
        public String toString() {
            return destination + " (" + distance + " km)";
        }
    }

    public void addLocation(String location) {
        if (!adjacencyList.containsKey(location)) {
            adjacencyList.put(location, new ArrayList<>());
        }
    }

    public void addRoad(String from, String to, double distance) {
        addLocation(from);
        addLocation(to);

        adjacencyList.get(from).add(new Edge(to, distance));
        adjacencyList.get(to).add(new Edge(from, distance));
    }

    public List<Edge> getNeighbors(String location) {
        return adjacencyList.getOrDefault(location, new ArrayList<>());
    }

    public List<String> getLocations() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    public void displayGraph() {
        System.out.println("Road Network Graph:");
        for (String location : adjacencyList.keySet()) {
            System.out.print(location + " -> ");
            List<Edge> edges = adjacencyList.get(location);
            for (int i = 0; i < edges.size(); i++) {
                System.out.print(edges.get(i));
                if (i < edges.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}