package com.nimblix.driverdashboard.controller;

import com.nimblix.driverdashboard.dto.MaintenanceItem;
import com.nimblix.driverdashboard.dto.MaintenanceSummary;
import com.nimblix.driverdashboard.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver/maintenance")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    /**
     * ✅ Add new maintenance item
     */
    @PostMapping("/add")
    public MaintenanceItem addItem(@RequestBody MaintenanceItem item) {
        return maintenanceService.addMaintenanceItem(item);
    }

    /**
     * ✅ Fetch all maintenance items
     */
    //http://localhost:8080/driver/maintenance/all
    @GetMapping("/all")
    public List<MaintenanceItem> getAllItems() {
        return maintenanceService.getAllMaintenanceItems();
    }
    
 // ✅ GET -> Summary (Urgent / Scheduled / Completed)
    
    //http://localhost:8080/driver/maintenance/summary
    @GetMapping("/summary")
    public MaintenanceSummary getSummary() {
        return maintenanceService.getSummary();
    }
    
    
    /**
     * ✅ Update a maintenance item by ID
     */
    @PutMapping("/{id}")
    public MaintenanceItem updateItem(
            @PathVariable String id,
            @RequestBody MaintenanceItem item) {
        return maintenanceService.updateMaintenanceItem(id, item);
    }

    /**
     * ✅ Delete a maintenance item by ID
     */
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        maintenanceService.deleteMaintenanceItem(id);
    }

}
