package com.smartwaste.model;

public class Bin {
    private String binId;
    private String location;
    private double currentFill;
    private double predictedFill;
    private String status;
    private String lastCollected;

    public Bin() {
    }

    public Bin(String binId, String location, double currentFill, double predictedFill, String status, String lastCollected) {
        this.binId = binId;
        this.location = location;
        this.currentFill = currentFill;
        this.predictedFill = predictedFill;
        this.status = status;
        this.lastCollected = lastCollected;
    }

    public String getBinId() {
        return binId;
    }

    public void setBinId(String binId) {
        this.binId = binId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getCurrentFill() {
        return currentFill;
    }

    public void setCurrentFill(double currentFill) {
        this.currentFill = currentFill;
    }

    public double getPredictedFill() {
        return predictedFill;
    }

    public void setPredictedFill(double predictedFill) {
        this.predictedFill = predictedFill;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastCollected() {
        return lastCollected;
    }

    public void setLastCollected(String lastCollected) {
        this.lastCollected = lastCollected;
    }

    @Override
    public String toString() {
        return "Bin{" +
                "binId='" + binId + '\'' +
                ", location='" + location + '\'' +
                ", currentFill=" + currentFill +
                ", predictedFill=" + predictedFill +
                ", status='" + status + '\'' +
                ", lastCollected='" + lastCollected + '\'' +
                '}';
    }
}