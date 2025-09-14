package com.nimblix.driverdashboard.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "bus_routes")
public class BusRoute {
    
    @Id
    @Column(name = "id")
    private String id; // UUID from main system

    @Column(name = "route_number", nullable = false)
    private String routeNumber;

    @Column(name = "driver_name", nullable = false)
    private String driverName;

    @Column(name = "current_location")
    private String currentLocation;

    @Column(name = "next_stop")
    private String nextStop;

    @Column(name = "eta")
    private String eta; // Estimated Time of Arrival

    @Column(name = "status")
    private String status; // "on_route", "maintenance", "available"

    /** If you want to store JSON array as a String */
    @ElementCollection
    @CollectionTable(name = "bus_route_stops", joinColumns = @JoinColumn(name = "bus_route_id"))
    @Column(name = "stop")
    private List<String> stops;
   public BusRoute() {}
   
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getRouteNumber() { return routeNumber; }
    public void setRouteNumber(String routeNumber) { this.routeNumber = routeNumber; }
    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
    public String getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(String currentLocation) { this.currentLocation = currentLocation; }
    public String getNextStop() { return nextStop; }
    public void setNextStop(String nextStop) { this.nextStop = nextStop; }
    public String getEta() { return eta; }
    public void setEta(String eta) { this.eta = eta; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

	public List<String> getStops() {
		return stops;
	}

	public void setStops(List<String> stops) {
		this.stops = stops;
	}
    
    
}