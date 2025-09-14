package com.nimblix.driverdashboard.dto;

import java.time.LocalDateTime;

public class AnnouncementInfoDto {
    private String id;              // unique identifier
    private String title;
    private String description;   // maps to `content` in entity
    private String type;          // "Transport", "Academic", "General"
    private String priority;      // "High", "Normal", "Low"
    private String targetClass;   // optional
    private LocalDateTime date;   // maps to `createdAt` in entity

    public AnnouncementInfoDto() {}

    // --- Getters & Setters ---
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

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTargetClass() {
        return targetClass;
    }
    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
