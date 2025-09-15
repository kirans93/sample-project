package com.nimblix.driverdashboard.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "vehicle_tracking")
public class VehicleTracking {

	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    @JsonProperty("id")
    private UUID id;

    @Column(name = "driver_id", nullable = false)
    @JsonProperty("driver_id")
    private String driverId;

    @Column(name = "vehicle_number", nullable = false)
    @JsonProperty("vehicle_number")
    private String vehicleNumber;

    @Column(name = "route_number", nullable = false)
    @JsonProperty("route_number")
    private String routeNumber;

    @Column(name = "current_km", nullable = false)
    @JsonProperty("current_km")
    private Integer currentKm;

    @Column(name = "fuel_level", nullable = false)
    @JsonProperty("fuel_level")
    private Integer fuelLevel;

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("speed")
    private String speed;

    @Column(name = "engine_status", nullable = false)
    @JsonProperty("engine_status")
    private String engineStatus;

    @Column(name = "is_moving")
    @JsonProperty("is_moving")
    private Boolean isMoving;

    @Column(name = "last_maintenance_km")
    @JsonProperty("last_maintenance_km")
    private Integer lastMaintenanceKm;

    @Column(name = "next_maintenance_due")
    @JsonProperty("next_maintenance_due")
    private Integer nextMaintenanceDue;

    @Column(name = "emergency_alert")
    @JsonProperty("emergency_alert")
    private Boolean emergencyAlert;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT now()")
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    public VehicleTracking() {}

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public String getRouteNumber() { return routeNumber; }
    public void setRouteNumber(String routeNumber) { this.routeNumber = routeNumber; }

    public Integer getCurrentKm() { return currentKm; }
    public void setCurrentKm(Integer currentKm) { this.currentKm = currentKm; }

    public Integer getFuelLevel() { return fuelLevel; }
    public void setFuelLevel(Integer fuelLevel) { this.fuelLevel = fuelLevel; }

    public String getLatitude() { return latitude; }
    public void setLatitude(String latitude) { this.latitude = latitude; }

    public String getLongitude() { return longitude; }
    public void setLongitude(String longitude) { this.longitude = longitude; }

    public String getSpeed() { return speed; }
    public void setSpeed(String speed) { this.speed = speed; }

    public String getEngineStatus() { return engineStatus; }
    public void setEngineStatus(String engineStatus) { this.engineStatus = engineStatus; }

    public Boolean getIsMoving() { return isMoving; }
    public void setIsMoving(Boolean isMoving) { this.isMoving = isMoving; }

    public Integer getLastMaintenanceKm() { return lastMaintenanceKm; }
    public void setLastMaintenanceKm(Integer lastMaintenanceKm) { this.lastMaintenanceKm = lastMaintenanceKm; }

    public Integer getNextMaintenanceDue() { return nextMaintenanceDue; }
    public void setNextMaintenanceDue(Integer nextMaintenanceDue) { this.nextMaintenanceDue = nextMaintenanceDue; }

    public Boolean getEmergencyAlert() { return emergencyAlert; }
    public void setEmergencyAlert(Boolean emergencyAlert) { this.emergencyAlert = emergencyAlert; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
