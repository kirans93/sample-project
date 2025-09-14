package com.nimblix.driverdashboard.dto;

public class MaintenanceItem {
    private String title;
    private String priority;
    private String category;
    private String serviceType;
    private String action;
    private String status;       // ✅
    private Double estimatedCost; // ✅

    public MaintenanceItem() {}

    public MaintenanceItem(String title, String priority, String category,
                           String serviceType, String action, String status, Double estimatedCost) {
        this.title = title;
        this.priority = priority;
        this.category = category;
        this.serviceType = serviceType;
        this.action = action;
        this.status = status;
        this.estimatedCost = estimatedCost;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

    // Getters & Setters
    
    
}
