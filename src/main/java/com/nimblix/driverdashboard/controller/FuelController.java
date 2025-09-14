package com.nimblix.driverdashboard.controller;

import com.nimblix.driverdashboard.dto.FuelLogDto;
import com.nimblix.driverdashboard.dto.FuelSummaryDto;
import com.nimblix.driverdashboard.dto.FuelTrendDto;
import com.nimblix.driverdashboard.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver/fuel")
public class FuelController {

    @Autowired
    private FuelService fuelService;
    
    
    @PostMapping("/logs")
    public ResponseEntity<FuelLogDto> addFuelLog(
            @RequestBody FuelLogDto fuelLogDto) {
        FuelLogDto savedLog = fuelService.addFuelLog(fuelLogDto);
        return ResponseEntity.ok(savedLog);
    }


    /**
     * Driver fuel dashboard summary
     */
    //http://localhost:8080/driver/fuel/summary?driverId=driver-001
    @GetMapping("/summary")
    public ResponseEntity<FuelSummaryDto> getFuelSummary(
            @RequestParam("driverId") String driverId) {
        FuelSummaryDto summary = fuelService.getFuelSummary(driverId);
        return ResponseEntity.ok(summary);
    }

    /**
     * Recent fuel logs (last 5 entries)
     */
    //http://localhost:8080/driver/fuel/logs?driverId=driver-001
    @GetMapping("/logs")
    public ResponseEntity<List<FuelLogDto>> getRecentLogs(
            @RequestParam("driverId") String driverId) {
        List<FuelLogDto> logs = fuelService.getRecentLogs(driverId);
        return ResponseEntity.ok(logs);
    }

    /**
     * Monthly trends (e.g., last 6 months)
     */
    
    // http://localhost:8080/driver/fuel/trends?driverId=driver-001
    @GetMapping("/trends")
    public ResponseEntity<List<FuelTrendDto>> getFuelTrends(
            @RequestParam("driverId") String driverId) {
        List<FuelTrendDto> trends = fuelService.getFuelTrends(driverId);
        return ResponseEntity.ok(trends);
    }
    
    /**
     * Update an existing fuel log
     */
    @PutMapping("/logs/{id}")
    public ResponseEntity<FuelLogDto> updateFuelLog(
            @PathVariable String id,
            @RequestBody FuelLogDto fuelLogDto) {
        FuelLogDto updatedLog = fuelService.updateFuelLog(id, fuelLogDto);
        return ResponseEntity.ok(updatedLog);
    }

    /**
     * Delete a fuel log
     */
    @DeleteMapping("/logs/{id}")
    public ResponseEntity<Void> deleteFuelLog(@PathVariable String id) {
        fuelService.deleteFuelLog(id);
        return ResponseEntity.noContent().build();
    }

}
