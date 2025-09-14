package com.nimblix.driverdashboard.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "non_teaching_staff")
public class Driver {
    
    @Id
    @Column(name = "id")
    private String id; // UUID from main system

    @Column(name = "user_id")
    private String userId; // Links to users table

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @Column(name = "department", nullable = false)
    private String department; // Should be "Transport"

    @Column(name = "designation", nullable = false)
    private String designation; // Should be "Driver"

    @Column(name = "shift")
    private String shift; // "Morning", "Evening", "Night"

    @Column(name = "phone")
    private String phone;

    @Column(name = "vehicle_assigned")
    private String vehicleAssigned; // Bus number like "15A"

    @Column(name = "joining_date")
    private LocalDateTime joiningDate;
    
    public Driver() {}

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getVehicleAssigned() { return vehicleAssigned; }
    public void setVehicleAssigned(String vehicleAssigned) { this.vehicleAssigned = vehicleAssigned; }
    public LocalDateTime getJoiningDate() { return joiningDate; }
    public void setJoiningDate(LocalDateTime joiningDate) { this.joiningDate = joiningDate; }
}