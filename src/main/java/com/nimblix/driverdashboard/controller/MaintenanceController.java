package com.nimblix.driverdashboard.controller;

import com.nimblix.driverdashboard.dto.MaintenanceItem;
import com.nimblix.driverdashboard.dto.MaintenanceSummary;
import com.nimblix.driverdashboard.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver/maintenance")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    /**
     * API: Add new maintenance item
     * METHOD: POST
     * URL: http://localhost:8080/driver/maintenance/add
     * BODY: MaintenanceItem JSON
     * RESPONSE: Success message
     */
    @PostMapping("/add")
    public ResponseEntity<String> addItem(@RequestBody MaintenanceItem item) {
        maintenanceService.addMaintenanceItem(item);
        return ResponseEntity.ok("✅ Successfully added maintenance item");
    }

    /**
     * API: Fetch all maintenance items
     * METHOD: GET
     * URL: http://localhost:8080/driver/maintenance/all
     * RESPONSE: List of MaintenanceItem
     */
    @GetMapping("/all")
    public ResponseEntity<List<MaintenanceItem>> getAllItems() {
        return ResponseEntity.ok(maintenanceService.getAllMaintenanceItems());
    }

    /**
     * API: Fetch maintenance summary (Urgent / Scheduled / Completed)
     * METHOD: GET
     * URL: http://localhost:8080/driver/maintenance/summary
     * RESPONSE: MaintenanceSummary
     */
    @GetMapping("/summary")
    public ResponseEntity<MaintenanceSummary> getSummary() {
        return ResponseEntity.ok(maintenanceService.getSummary());
    }

    /**
     * API: Update a maintenance item by ID
     * METHOD: PUT
     * URL: http://localhost:8080/driver/maintenance/{id}
     * BODY: MaintenanceItem JSON
     * RESPONSE: Success message
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(
            @PathVariable String id,
            @RequestBody MaintenanceItem item) {
        maintenanceService.updateMaintenanceItem(id, item);
        return ResponseEntity.ok("✅ Successfully updated maintenance item with ID: " + id);
    }

    /**
     * API: Delete a maintenance item by ID
     * METHOD: DELETE
     * URL: http://localhost:8080/driver/maintenance/{id}
     * RESPONSE: Success message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable String id) {
        maintenanceService.deleteMaintenanceItem(id);
        return ResponseEntity.ok("✅ Successfully deleted maintenance item with ID: " + id);
    }
}
