package com.nimblix.driverdashboard.model;

import java.sql.Timestamp;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fuel_logs")
public class FuelLog {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "CHAR(36)")
	private String id;


    @Column(name = "driver_id", nullable = false)
    private String driverId;

    @Column(nullable = false)
    private Timestamp date;

    @Column(nullable = false)
    private String station;

    @Column(nullable = false)
    private Double liters;

    @Column(nullable = false)
    private Double cost;

    @Column(name = "odometer_km", nullable = false)
    private Integer odometerKm;

    public FuelLog() {}

    public FuelLog(String id, String driverId, Timestamp date, String station,
                   Double liters, Double cost, Integer odometerKm) {
        this.id = id;
        this.driverId = driverId;
        this.date = date;
        this.station = station;
        this.liters = liters;
        this.cost = cost;
        this.odometerKm = odometerKm;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public Timestamp getDate() { return date; }
    public void setDate(Timestamp date) { this.date = date; }

    public String getStation() { return station; }
    public void setStation(String station) { this.station = station; }

    public Double getLiters() { return liters; }
    public void setLiters(Double liters) { this.liters = liters; }

    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }

    public Integer getOdometerKm() { return odometerKm; }
    public void setOdometerKm(Integer odometerKm) { this.odometerKm = odometerKm; }

    @Override
    public String toString() {
        return "FuelLog{" +
                "id=" + id +
                ", driverId='" + driverId + '\'' +
                ", date=" + date +
                ", station='" + station + '\'' +
                ", liters=" + liters +
                ", cost=" + cost +
                ", odometerKm=" + odometerKm +
                '}';
    }
}
