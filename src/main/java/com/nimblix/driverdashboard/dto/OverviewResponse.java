package com.nimblix.driverdashboard.dto;

public class OverviewResponse {
    private DriverInfoDto driverInfo;
   
    private PerformanceInfoDto performanceInfo; 
    private VehicleInfoDto vehicleInfo;
   

    public OverviewResponse() {}

    public DriverInfoDto getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(DriverInfoDto driverInfo) {
        this.driverInfo = driverInfo;
    }

    public VehicleInfoDto getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(VehicleInfoDto vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public PerformanceInfoDto getPerformanceInfo() {
        return performanceInfo;
    }

    public void setPerformanceInfo(PerformanceInfoDto performanceInfo) {
        this.performanceInfo = performanceInfo;
    }

    
}
