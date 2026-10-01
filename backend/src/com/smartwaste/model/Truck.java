package com.smartwaste.model;

public class Truck {
    private String truckId;
    private String driver;
    private double capacity;
    private String status;
    private String currentRoute;

    public Truck() {
    }

    public Truck(String truckId, String driver, double capacity, String status, String currentRoute) {
        this.truckId = truckId;
        this.driver = driver;
        this.capacity = capacity;
        this.status = status;
        this.currentRoute = currentRoute;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentRoute() {
        return currentRoute;
    }

    public void setCurrentRoute(String currentRoute) {
        this.currentRoute = currentRoute;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "truckId='" + truckId + '\'' +
                ", driver='" + driver + '\'' +
                ", capacity=" + capacity +
                ", status='" + status + '\'' +
                ", currentRoute='" + currentRoute + '\'' +
                '}';
    }
}