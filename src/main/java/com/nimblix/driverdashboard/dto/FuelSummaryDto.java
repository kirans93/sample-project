package com.nimblix.driverdashboard.dto;

import java.util.List;

public class FuelSummaryDto {
    private CurrentFuelStatus currentFuelStatus;
    private MonthlyConsumption monthlyConsumption;
    private CostAnalysis costAnalysis;
    private List<FuelLogDto> recentLogs;

    public static class CurrentFuelStatus {
        private int percentage;
        private int odometerKm;
        private double estimatedRangeKm;

        public int getPercentage() { return percentage; }
        public void setPercentage(int percentage) { this.percentage = percentage; }

        public int getOdometerKm() { return odometerKm; }
        public void setOdometerKm(int odometerKm) { this.odometerKm = odometerKm; }

        public double getEstimatedRangeKm() { return estimatedRangeKm; }
        public void setEstimatedRangeKm(double estimatedRangeKm) { this.estimatedRangeKm = estimatedRangeKm; }
    }

    public static class MonthlyConsumption {
        private double totalFuelLiters;
        private double totalCost;
        private double mileageKmpl;
        private int distanceKm;

        public double getTotalFuelLiters() { return totalFuelLiters; }
        public void setTotalFuelLiters(double totalFuelLiters) { this.totalFuelLiters = totalFuelLiters; }

        public double getTotalCost() { return totalCost; }
        public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

        public double getMileageKmpl() { return mileageKmpl; }
        public void setMileageKmpl(double mileageKmpl) { this.mileageKmpl = mileageKmpl; }

        public int getDistanceKm() { return distanceKm; }
        public void setDistanceKm(int distanceKm) { this.distanceKm = distanceKm; }
    }

    public static class CostAnalysis {
        private String fuelEfficiency;
        private Double changeVsLastMonth;
        private int targetMileage = 12; // static target

        public String getFuelEfficiency() { return fuelEfficiency; }
        public void setFuelEfficiency(String fuelEfficiency) { this.fuelEfficiency = fuelEfficiency; }

        public Double getChangeVsLastMonth() { return changeVsLastMonth; }
        public void setChangeVsLastMonth(Double changeVsLastMonth) { this.changeVsLastMonth = changeVsLastMonth; }

        public int getTargetMileage() { return targetMileage; }
        public void setTargetMileage(int targetMileage) { this.targetMileage = targetMileage; }
    }

    public FuelSummaryDto() {}

    public CurrentFuelStatus getCurrentFuelStatus() { return currentFuelStatus; }
    public void setCurrentFuelStatus(CurrentFuelStatus currentFuelStatus) { this.currentFuelStatus = currentFuelStatus; }

    public MonthlyConsumption getMonthlyConsumption() { return monthlyConsumption; }
    public void setMonthlyConsumption(MonthlyConsumption monthlyConsumption) { this.monthlyConsumption = monthlyConsumption; }

    public CostAnalysis getCostAnalysis() { return costAnalysis; }
    public void setCostAnalysis(CostAnalysis costAnalysis) { this.costAnalysis = costAnalysis; }

    public List<FuelLogDto> getRecentLogs() { return recentLogs; }
    public void setRecentLogs(List<FuelLogDto> recentLogs) { this.recentLogs = recentLogs; }
}
