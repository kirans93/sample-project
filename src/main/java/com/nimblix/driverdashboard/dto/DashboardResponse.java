package com.nimblix.driverdashboard.dto;

import java.util.List;

public class DashboardResponse {
    private List<MaintenanceSummary> summaries;
    private List<MaintenanceItem> maintenanceSchedule;
  
    public DashboardResponse() {
    }
    
    public List<MaintenanceSummary> getSummaries() {
        return summaries;
    }
    
    public void setSummaries(List<MaintenanceSummary> summaries) {
        this.summaries = summaries;
    }
    
    public List<MaintenanceItem> getMaintenanceSchedule() {
        return maintenanceSchedule;
    }
    
    public void setMaintenanceSchedule(List<MaintenanceItem> maintenanceSchedule) {
        this.maintenanceSchedule = maintenanceSchedule;
    }
    
}