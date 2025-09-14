package com.nimblix.driverdashboard.dto;

public class FuelLogDto {
    private String date;
    private String station;
    private Double liters;
    private Double cost;
    private Integer odometerKm;
    private String driverId;   // ✅ Added

    public FuelLogDto() {}

    // Old constructor (keep for backward compatibility)
    public FuelLogDto(String date, String station, Double liters, Double cost, Integer odometerKm) {
        this.date = date;
        this.station = station;
        this.liters = liters;
        this.cost = cost;
        this.odometerKm = odometerKm;
    }

    // ✅ New constructor with driverId
    public FuelLogDto(String date, String station, Double liters, Double cost, Integer odometerKm, String driverId) {
        this.date = date;
        this.station = station;
        this.liters = liters;
        this.cost = cost;
        this.odometerKm = odometerKm;
        this.driverId = driverId;
    }

    // Getters and setters...
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getStation() { return station; }
    public void setStation(String station) { this.station = station; }

    public Double getLiters() { return liters; }
    public void setLiters(Double liters) { this.liters = liters; }

    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }

    public Integer getOdometerKm() { return odometerKm; }
    public void setOdometerKm(Integer odometerKm) { this.odometerKm = odometerKm; }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }
}
