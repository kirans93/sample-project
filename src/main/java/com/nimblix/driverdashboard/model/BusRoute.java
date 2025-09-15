package com.nimblix.driverdashboard.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "bus_routes")
public class BusRoute {
    
	@Id
    @Column(name = "id")
    @JsonProperty("id")
    private String id;

    @Column(name = "route_number", nullable = false)
    @JsonProperty("route_number")
    private String routeNumber;

    @Column(name = "driver_name", nullable = false)
    @JsonProperty("driver_name")
    private String driverName;

    @Column(name = "current_location")
    @JsonProperty("current_location")
    private String currentLocation;

    @Column(name = "next_stop")
    @JsonProperty("next_stop")
    private String nextStop;

    @Column(name = "eta")
    @JsonProperty("eta")
    private String eta;

    @Column(name = "status")
    @JsonProperty("status")
    private String status;

    @ElementCollection
    @CollectionTable(name = "bus_route_stops", joinColumns = @JoinColumn(name = "bus_route_id"))
    @Column(name = "stop")
    @JsonProperty("stops")
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