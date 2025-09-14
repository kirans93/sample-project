package com.nimblix.driverdashboard.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimblix.driverdashboard.model.BusRoute;
import com.nimblix.driverdashboard.model.VehicleStatus;
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

    /** Get driver overview by driverId */
    
    //http://localhost:8080/api/non-teaching-staff/drivers/driver-001/overview
    @GetMapping("/{driverId}/overview")
    public ResponseEntity<OverviewResponse> getDriverOverview(@PathVariable String driverId) {
        return ResponseEntity.ok(overviewService.getDriverOverview(driverId));
    }

    /** Get driver overview by employeeId */
    //http://localhost:8080/api/non-teaching-staff/drivers/employee/EMP123/overview
    @GetMapping("/employee/{employeeId}/overview")
    
    public ResponseEntity<OverviewResponse> getDriverOverviewByEmployeeId(@PathVariable String employeeId) {
        return ResponseEntity.ok(overviewService.getDriverOverviewByEmployeeId(employeeId));
    }

    /** Get driver overview by vehicle number */
    
    //http://localhost:8080/api/non-teaching-staff/drivers/vehicle/15A/overview
    @GetMapping("/vehicle/{vehicleNumber}/overview")
    public ResponseEntity<OverviewResponse> getDriverOverviewByVehicle(@PathVariable String vehicleNumber) {
        return ResponseEntity.ok(overviewService.getDriverOverviewByVehicle(vehicleNumber));
    }
    
 // ---------------- GET ----------------

 // ---------------- GET ----------------

    /** Get all bus routes */
    // http://localhost:8080/api/non-teaching-staff/drivers/bus-routes
    @GetMapping("/bus-routes")
    public ResponseEntity<List<BusRoute>> getAllBusRoutes() {
        return ResponseEntity.ok(overviewService.getAllBusRoutes());
    }

    /** Get a single bus route by ID */
    // http://localhost:8080/api/non-teaching-staff/drivers/bus-routes/{id}
    @GetMapping("/bus-routes/{id}")
    public ResponseEntity<BusRoute> getBusRouteById(@PathVariable String id) {
        return ResponseEntity.ok(overviewService.getBusRouteById(id));
    }



    
    /** Health check */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Driver Overview module is healthy ✅");
    }

    // ---------------- POST ----------------

  

    /** Save new vehicle status */
    @PostMapping("/vehicle-status")
    public ResponseEntity<VehicleStatus> createVehicleStatus(@RequestBody VehicleStatus vehicleStatus) {
        return ResponseEntity.ok(overviewService.saveVehicleStatus(vehicleStatus));
    }

    /** Save new bus route */
    @PostMapping("/bus-routes")
    public ResponseEntity<BusRoute> createBusRoute(@RequestBody BusRoute busRoute) {
        return ResponseEntity.ok(overviewService.saveBusRoute(busRoute));
    }

  
    
 // ---------------- PUT ----------------

    /** Update vehicle status by ID */
    @PutMapping("/vehicle-status/{id}")
    public ResponseEntity<VehicleStatus> updateVehicleStatus(
            @PathVariable String id,
            @RequestBody VehicleStatus vehicleStatus) {
        return ResponseEntity.ok(overviewService.updateVehicleStatus(id, vehicleStatus));
    }

    /** Update bus route by ID */
    @PutMapping("/bus-routes/{id}")
    public ResponseEntity<BusRoute> updateBusRoute(
            @PathVariable String id,
            @RequestBody BusRoute busRoute) {
        return ResponseEntity.ok(overviewService.updateBusRoute(id, busRoute));
    }

    // ---------------- DELETE ----------------

    /** Delete vehicle status by ID */
    @DeleteMapping("/vehicle-status/{id}")
    public ResponseEntity<Void> deleteVehicleStatus(@PathVariable String id) {
        overviewService.deleteVehicleStatus(id);
        return ResponseEntity.noContent().build();
    }

    /** Delete bus route by ID */
    @DeleteMapping("/bus-routes/{id}")
    public ResponseEntity<Void> deleteBusRoute(@PathVariable String id) {
        overviewService.deleteBusRoute(id);
        return ResponseEntity.noContent().build();
    }
    
  // ---------------- Error Handling ----------------
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleDriverNotFound(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
