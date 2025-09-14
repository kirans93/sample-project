package com.nimblix.driverdashboard.dto;

public class VehicleInfoDto {
    private String engineHealth;   // "Excellent"
    private String fuelLevel;      // "75%"
    private String nextService;    // "Due Tomorrow"
    private String mileageToday;   // "186 km"
    
    public VehicleInfoDto() {}
    
	public String getEngineHealth() {
		return engineHealth;
	}
	public void setEngineHealth(String engineHealth) {
		this.engineHealth = engineHealth;
	}
	public String getFuelLevel() {
		return fuelLevel;
	}
	public void setFuelLevel(String fuelLevel) {
		this.fuelLevel = fuelLevel;
	}
	public String getNextService() {
		return nextService;
	}
	public void setNextService(String nextService) {
		this.nextService = nextService;
	}
	public String getMileageToday() {
		return mileageToday;
	}
	public void setMileageToday(String mileageToday) {
		this.mileageToday = mileageToday;
	}

    // Getters & Setters
    
    
}
