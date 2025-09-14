package com.nimblix.driverdashboard.dto;

public class PerformanceInfoDto {
    private String attendance;       // e.g. "Present"
    private String tripsCompleted;   // e.g. "8/8"
    private String onTimePerformance; // e.g. "100%"
    
    public PerformanceInfoDto() {}
    
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public String getTripsCompleted() {
		return tripsCompleted;
	}
	public void setTripsCompleted(String tripsCompleted) {
		this.tripsCompleted = tripsCompleted;
	}
	public String getOnTimePerformance() {
		return onTimePerformance;
	}
	public void setOnTimePerformance(String onTimePerformance) {
		this.onTimePerformance = onTimePerformance;
	}

    // Getters & Setters
    
    
}
