package com.nimblix.driverdashboard.controller;

import com.nimblix.driverdashboard.dto.FuelLogDto;
import com.nimblix.driverdashboard.dto.FuelSummaryDto;
import com.nimblix.driverdashboard.dto.FuelTrendDto;
import com.nimblix.driverdashboard.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/driver/fuel")
public class FuelController {

    @Autowired
    private FuelService fuelService;

    // Utility method to build a response message with URL
    private ResponseEntity<Map<String, String>> buildResponse(String message, String url) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        response.put("url", url);
        return ResponseEntity.ok(response);
    }

    // ---------------- POST ----------------
    /**
     * API: Add a new fuel log entry
     * METHOD: POST
     * URL: http://localhost:8080/driver/fuel/logs
     * BODY: FuelLogDto JSON
     * RESPONSE: Success message + URL
     */
    @PostMapping("/logs")
    public ResponseEntity<Map<String, String>> addFuelLog(@RequestBody FuelLogDto fuelLogDto) {
        fuelService.addFuelLog(fuelLogDto);
        return buildResponse("✅ Successfully added fuel log", "http://localhost:8080/driver/fuel/logs");
    }

    // ---------------- GET ----------------
    /**
     * API: Get fuel summary for a driver
     * METHOD: GET
     * URL: http://localhost:8080/driver/fuel/summary?driverId=driver-001
     * RESPONSE: FuelSummaryDto JSON
     */
    @GetMapping("/summary")
    public ResponseEntity<FuelSummaryDto> getFuelSummary(@RequestParam("driverId") String driverId) {
        FuelSummaryDto summary = fuelService.getFuelSummary(driverId);
        return ResponseEntity.ok(summary);
    }

    /**
     * API: Get recent fuel logs (last 5 entries) for a driver
     * METHOD: GET
     * URL: http://localhost:8080/driver/fuel/logs?driverId=driver-001
     * RESPONSE: List<FuelLogDto>
     */
    @GetMapping("/logs")
    public ResponseEntity<List<FuelLogDto>> getRecentLogs(@RequestParam("driverId") String driverId) {
        List<FuelLogDto> logs = fuelService.getRecentLogs(driverId);
        return ResponseEntity.ok(logs);
    }

    /**
     * API: Get monthly fuel trends (last 6 months)
     * METHOD: GET
     * URL: http://localhost:8080/driver/fuel/trends?driverId=driver-001
     * RESPONSE: List<FuelTrendDto>
     */
    @GetMapping("/trends")
    public ResponseEntity<List<FuelTrendDto>> getFuelTrends(@RequestParam("driverId") String driverId) {
        List<FuelTrendDto> trends = fuelService.getFuelTrends(driverId);
        return ResponseEntity.ok(trends);
    }

    // ---------------- PUT ----------------
    /**
     * API: Update an existing fuel log
     * METHOD: PUT
     * URL: http://localhost:8080/driver/fuel/logs/{id}
     * BODY: FuelLogDto JSON
     * RESPONSE: Success message + URL
     */
    @PutMapping("/logs/{id}")
    public ResponseEntity<Map<String, String>> updateFuelLog(
            @PathVariable String id,
            @RequestBody FuelLogDto fuelLogDto) {
        fuelService.updateFuelLog(id, fuelLogDto);
        return buildResponse("✅ Successfully updated fuel log", "http://localhost:8080/driver/fuel/logs/" + id);
    }

    // ---------------- DELETE ----------------
    /**
     * API: Delete a fuel log by ID
     * METHOD: DELETE
     * URL: http://localhost:8080/driver/fuel/logs/{id}
     * RESPONSE: Success message + URL
     */
    @DeleteMapping("/logs/{id}")
    public ResponseEntity<Map<String, String>> deleteFuelLog(@PathVariable String id) {
        fuelService.deleteFuelLog(id);
        return buildResponse("🗑️ Successfully deleted fuel log", "http://localhost:8080/driver/fuel/logs/" + id);
    }
}
