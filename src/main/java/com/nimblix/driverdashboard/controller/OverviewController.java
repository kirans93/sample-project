package com.nimblix.driverdashboard.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nimblix.driverdashboard.model.BusRoute;
import com.nimblix.driverdashboard.model.Driver;
import com.nimblix.driverdashboard.model.VehicleTracking;
import com.nimblix.driverdashboard.dto.OverviewResponse;
import com.nimblix.driverdashboard.service.OverviewService;

@RestController
@RequestMapping("/api/non-teaching-staff/drivers")
public class OverviewController {

    private final OverviewService overviewService;

    public OverviewController(OverviewService overviewService) {
        this.overviewService = overviewService;
    }

    // ---------------- GET ----------------

    /**
     * API: Get driver overview by driverId
     * METHOD: GET
     * URL: http://localhost:8080/api/non-teaching-staff/drivers/{driverId}/overview
     * RESPONSE: OverviewResponse JSON
     */
    @GetMapping("/{driverId}/overview")
    public ResponseEntity<OverviewResponse> getDriverOverview(@PathVariable String driverId) {
        return ResponseEntity.ok(overviewService.getDriverOverview(driverId));
    }

    /**
     * API: Get driver overview by employeeId
     * METHOD: GET
     * URL: http://localhost:8080/api/non-teaching-staff/drivers/employee/{employeeId}/overview
     * RESPONSE: OverviewResponse JSON
     */
    @GetMapping("/employee/{employeeId}/overview")
    public ResponseEntity<OverviewResponse> getDriverOverviewByEmployeeId(@PathVariable String employeeId) {
        return ResponseEntity.ok(overviewService.getDriverOverviewByEmployeeId(employeeId));
    }

    /**
     * API: Get driver overview by vehicle number
     * METHOD: GET
     * URL: http://localhost:8080/api/non-teaching-staff/drivers/vehicle/{vehicleNumber}/overview
     * RESPONSE: OverviewResponse JSON
     */
    @GetMapping("/vehicle/{vehicleNumber}/overview")
    public ResponseEntity<OverviewResponse> getDriverOverviewByVehicle(@PathVariable String vehicleNumber) {
        return ResponseEntity.ok(overviewService.getDriverOverviewByVehicle(vehicleNumber));
    }

    /**
     * API: Get all bus routes
     * METHOD: GET
     * URL: http://localhost:8080/api/non-teaching-staff/drivers/bus-routes
     * RESPONSE: List of BusRoute JSON
     */
    @GetMapping("/bus-routes")
    public ResponseEntity<List<BusRoute>> getAllBusRoutes() {
        return ResponseEntity.ok(overviewService.getAllBusRoutes());
    }

    /**
     * API: Get a single bus route by ID
     * METHOD: GET
     * URL: http://localhost:8080/api/non-teaching-staff/drivers/bus-routes/{id}
     * RESPONSE: BusRoute JSON
     */
    @GetMapping("/bus-routes/{id}")
    public ResponseEntity<BusRoute> getBusRouteById(@PathVariable String id) {
        return ResponseEntity.ok(overviewService.getBusRouteById(id));
    }

    /**
     * API: Health check
     * METHOD: GET
     * URL: http://localhost:8080/api/non-teaching-staff/drivers/health
     * RESPONSE: String
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Driver Overview module is healthy ✅");
    }

    // ---------------- POST ----------------

    /**
     * API: Save new vehicle tracking record
     * METHOD: POST
     * URL: http://localhost:8080/api/non-teaching-staff/drivers/add-vehicle-tracking
     * BODY: VehicleTracking JSON
     * RESPONSE: Success message
     */
    @PostMapping("/add-vehicle-tracking")
    public ResponseEntity<String> createVehicleTracking(@RequestBody VehicleTracking vehicleTracking) {
        overviewService.saveVehicleTracking(vehicleTracking);
        return ResponseEntity.ok("✅ Vehicle tracking successfully added!");
    }

    /**
     * API: Save new bus route
     * METHOD: POST
     * URL: http://localhost:8080/api/non-teaching-staff/drivers/add-bus-routes
     * BODY: BusRoute JSON
     * RESPONSE: Success message
     */
    @PostMapping("/add-bus-routes")
    public ResponseEntity<String> createBusRoute(@RequestBody BusRoute busRoute) {
        overviewService.saveBusRoute(busRoute);
        return ResponseEntity.ok("✅ Bus route successfully added!");
    }

    /**
     * API: Save new driver
     * METHOD: POST
     * URL: http://localhost:8080/api/non-teaching-staff/drivers/add-driver
     * BODY: Driver JSON
     * RESPONSE: Success message
     */
    @PostMapping("/add-driver")
    public ResponseEntity<String> createDriver(@RequestBody Driver driver) {
        overviewService.saveDriver(driver);
        return ResponseEntity.ok("✅ Driver successfully added!");
    }

    // ---------------- PUT ----------------

    /**
     * API: Update vehicle tracking by ID
     * METHOD: PUT
     * URL: http://localhost:8080/api/non-teaching-staff/drivers/vehicle-tracking/{id}
     * BODY: VehicleTracking JSON
     * RESPONSE: Success message
     */
    @PutMapping("/vehicle-tracking/{id}")
    public ResponseEntity<String> updateVehicleTracking(
            @PathVariable UUID id,
            @RequestBody VehicleTracking vehicleTracking) {
        overviewService.updateVehicleTracking(id, vehicleTracking);
        return ResponseEntity.ok("✅ Vehicle tracking successfully updated!");
    }

    /**
     * API: Update bus route by ID
     * METHOD: PUT
     * URL: http://localhost:8080/api/non-teaching-staff/drivers/bus-routes/{id}
     * BODY: BusRoute JSON
     * RESPONSE: Success message
     */
    @PutMapping("/bus-routes/{id}")
    public ResponseEntity<String> updateBusRoute(
            @PathVariable String id,
            @RequestBody BusRoute busRoute) {
        overviewService.updateBusRoute(id, busRoute);
        return ResponseEntity.ok("✅ Bus route successfully updated!");
    }

    /**
     * API: Update driver by ID
     * METHOD: PUT
     * URL: http://localhost:8080/api/non-teaching-staff/drivers/drivers/{id}
     * BODY: Driver JSON
     * RESPONSE: Success message
     */
    @PutMapping("/drivers/{id}")
    public ResponseEntity<String> updateDriver(
            @PathVariable String id,
            @RequestBody Driver driver) {
        overviewService.updateDriver(id, driver);
        return ResponseEntity.ok("✅ Driver successfully updated!");
    }

    // ---------------- DELETE ----------------

    /**
     * API: Delete vehicle tracking by ID
     * METHOD: DELETE
     * URL: http://localhost:8080/api/non-teaching-staff/drivers/vehicle-tracking/{id}
     * RESPONSE: Success message
     */
    @DeleteMapping("/vehicle-tracking/{id}")
    public ResponseEntity<String> deleteVehicleTracking(@PathVariable UUID id) {
        overviewService.deleteVehicleTracking(id);
        return ResponseEntity.ok("🗑️ Vehicle tracking successfully deleted!");
    }

    /**
     * API: Delete bus route by ID
     * METHOD: DELETE
     * URL: http://localhost:8080/api/non-teaching-staff/drivers/bus-routes/{id}
     * RESPONSE: Success message
     */
    @DeleteMapping("/bus-routes/{id}")
    public ResponseEntity<String> deleteBusRoute(@PathVariable String id) {
        overviewService.deleteBusRoute(id);
        return ResponseEntity.ok("🗑️ Bus route successfully deleted!");
    }

    /**
     * API: Delete driver by ID
     * METHOD: DELETE
     * URL: http://localhost:8080/api/non-teaching-staff/drivers/drivers/{id}
     * RESPONSE: Success message
     */
    @DeleteMapping("/drivers/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable String id) {
        overviewService.deleteDriver(id);
        return ResponseEntity.ok("🗑️ Driver successfully deleted!");
    }

    // ---------------- Error Handling ----------------

    /**
     * API: Global Runtime Exception Handler
     * METHOD: Auto triggered
     * RESPONSE: Error message as String
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException ex) {
        return ResponseEntity.badRequest().body("❌ Error: " + ex.getMessage());
    }
}
