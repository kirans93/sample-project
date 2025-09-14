package com.nimblix.driverdashboard.dto;

public class DriverInfoDto {
    private String department;
    private String driverId;
    private String shift;
    private String vehicleAssigned;
    private String phone;
    
    
    public DriverInfoDto() {}
    
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getVehicleAssigned() {
		return vehicleAssigned;
	}
	public void setVehicleAssigned(String vehicleAssigned) {
		this.vehicleAssigned = vehicleAssigned;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

    // Getters & Setters
    
    
}
