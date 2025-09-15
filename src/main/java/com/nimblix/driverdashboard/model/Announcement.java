package com.nimblix.driverdashboard.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "announcements")
public class Announcement {

	    @Id
	    @Column(name = "id", nullable = false, unique = true)
	    @JsonProperty("id")
	    private String id;

	    @Column(name = "title", nullable = false)
	    @JsonProperty("title")
	    private String title;

	    @Column(name = "content", nullable = false)
	    @JsonProperty("content")
	    private String content;

	    @Column(name = "type", nullable = false)
	    @JsonProperty("type")
	    private String type;

	    @Column(name = "priority", nullable = false)
	    @JsonProperty("priority")
	    private String priority = "normal";

	    @Column(name = "target_class")
	    @JsonProperty("target_class")
	    private String targetClass;

	    @Column(name = "created_at", updatable = false, insertable = false)
	    @JsonProperty("created_at")
	    private LocalDateTime createdAt;
	    
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
