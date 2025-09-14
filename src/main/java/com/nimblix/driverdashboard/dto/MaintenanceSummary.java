package com.nimblix.driverdashboard.dto;

public class MaintenanceSummary {
    private int urgentCount;
    private int scheduledCount;
    private int completedCount;

    public MaintenanceSummary() {}

    public MaintenanceSummary(int urgentCount, int scheduledCount, int completedCount) {
        this.urgentCount = urgentCount;
        this.scheduledCount = scheduledCount;
        this.completedCount = completedCount;
    }

    public int getUrgentCount() {
        return urgentCount;
    }

    public void setUrgentCount(int urgentCount) {
        this.urgentCount = urgentCount;
    }

    public int getScheduledCount() {
        return scheduledCount;
    }

    public void setScheduledCount(int scheduledCount) {
        this.scheduledCount = scheduledCount;
    }

    public int getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(int completedCount) {
        this.completedCount = completedCount;
    }
}
