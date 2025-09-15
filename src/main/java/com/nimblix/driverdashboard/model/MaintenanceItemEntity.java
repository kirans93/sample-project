package com.nimblix.driverdashboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "maintenance_items")
public class MaintenanceItemEntity {
    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("priority")
    private String priority;

    @JsonProperty("category")
    private String category;

    @Column(name = "service_type")
    @JsonProperty("service_type")
    private String serviceType;

    @JsonProperty("action")
    private String action;

    @JsonProperty("status")
    private String status;

    @Column(name = "estimated_cost")
    @JsonProperty("estimated_cost")
    private Double estimatedCost;

    public MaintenanceItemEntity() {}

    public MaintenanceItemEntity(String id, String title, String priority, String category,
                                 String serviceType, String action, String status, Double estimatedCost) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.category = category;
        this.serviceType = serviceType;
        this.action = action;
        this.status = status;
        this.estimatedCost = estimatedCost;
    }
    
 // Getters & Setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

    
    // ...
    
    
}
