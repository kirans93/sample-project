package com.nimblix.driverdashboard.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "announcements")
public class Announcement {

	@Id
	@Column(name = "id", nullable = false, unique = true)
	private String id;

	
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "type", nullable = false)
    private String type; // "Transport", "Academic", "General"

    @Column(name = "priority", nullable = false)
    private String priority = "normal"; // default

    @Column(name = "target_class")
    private String targetClass;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt; // auto-filled by DB (DEFAULT now())

    public Announcement() {}

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

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
