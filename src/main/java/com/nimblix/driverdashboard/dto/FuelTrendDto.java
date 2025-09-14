package com.nimblix.driverdashboard.dto;

public class FuelTrendDto {
    private String month;
    private Double fuelLiters;
    private Double cost;
    private Integer distanceKm;
    private Double mileageKmpl;

    public FuelTrendDto() {}

    public FuelTrendDto(String month, Double fuelLiters, Double cost,
                        Integer distanceKm, Double mileageKmpl) {
        this.month = month;
        this.fuelLiters = fuelLiters;
        this.cost = cost;
        this.distanceKm = distanceKm;
        this.mileageKmpl = mileageKmpl;
    }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    public Double getFuelLiters() { return fuelLiters; }
    public void setFuelLiters(Double fuelLiters) { this.fuelLiters = fuelLiters; }

    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }

    public Integer getDistanceKm() { return distanceKm; }
    public void setDistanceKm(Integer distanceKm) { this.distanceKm = distanceKm; }

    public Double getMileageKmpl() { return mileageKmpl; }
    public void setMileageKmpl(Double mileageKmpl) { this.mileageKmpl = mileageKmpl; }
}
