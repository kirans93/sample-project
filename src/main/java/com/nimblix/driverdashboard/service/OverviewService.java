package com.nimblix.driverdashboard.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nimblix.driverdashboard.model.BusRoute;
import com.nimblix.driverdashboard.model.Driver;
import com.nimblix.driverdashboard.model.VehicleStatus;
import com.nimblix.driverdashboard.dto.DriverInfoDto;
import com.nimblix.driverdashboard.dto.OverviewResponse;
import com.nimblix.driverdashboard.dto.PerformanceInfoDto;
import com.nimblix.driverdashboard.dto.VehicleInfoDto;
import com.nimblix.driverdashboard.repository.BusRouteRepository;
import com.nimblix.driverdashboard.repository.DriverRepository;
import com.nimblix.driverdashboard.repository.VehicleStatusRepository;

@Service
public class OverviewService {

    private final DriverRepository driverRepository;
    private final VehicleStatusRepository vehicleStatusRepository;
    private final BusRouteRepository busRouteRepository;

    public OverviewService(
            DriverRepository driverRepository,
            VehicleStatusRepository vehicleStatusRepository,
            BusRouteRepository busRouteRepository) {
        this.driverRepository = driverRepository;
        this.vehicleStatusRepository = vehicleStatusRepository;
        this.busRouteRepository = busRouteRepository;
    }

    // ---------------- GET ----------------

    /** Overview by driverId */
    public OverviewResponse getDriverOverview(String driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + driverId));
        return buildOverviewResponse(driver);
    }

    /** Overview by employeeId */
    public OverviewResponse getDriverOverviewByEmployeeId(String employeeId) {
        Driver driver = driverRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Driver not found with employeeId: " + employeeId));
        return buildOverviewResponse(driver);
    }

    /** Overview by vehicle number */
    public OverviewResponse getDriverOverviewByVehicle(String vehicleNumber) {
        Driver driver = driverRepository.findByVehicleAssigned(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("Driver not found with vehicle: " + vehicleNumber));
        return buildOverviewResponse(driver);
    }
    
 // ---------------- GET ----------------

    public BusRoute getBusRouteById(String id) {
        return busRouteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BusRoute not found with id: " + id));
    }

    public List<BusRoute> getAllBusRoutes() {
        return busRouteRepository.findAll();
    }


    // ---------------- POST ----------------

    public VehicleStatus saveVehicleStatus(VehicleStatus vehicleStatus) {
        vehicleStatus.setId(UUID.randomUUID().toString());
        return vehicleStatusRepository.save(vehicleStatus);
    }

    public BusRoute saveBusRoute(BusRoute busRoute) {
        busRoute.setId(UUID.randomUUID().toString());
        return busRouteRepository.save(busRoute);
    }

    // ---------------- PRIVATE HELPERS ----------------
    private OverviewResponse buildOverviewResponse(Driver driver) {
        OverviewResponse response = new OverviewResponse();

        // Driver Info
        DriverInfoDto driverInfo = new DriverInfoDto();
        driverInfo.setDepartment(driver.getDepartment());
        driverInfo.setDriverId(driver.getId());
        driverInfo.setShift(driver.getShift());
        driverInfo.setVehicleAssigned(driver.getVehicleAssigned());
        driverInfo.setPhone(driver.getPhone());
        response.setDriverInfo(driverInfo);

        // Vehicle Info
        VehicleStatus vehicleStatus = vehicleStatusRepository
                .findTopByDriverIdOrderByTimestampDesc(driver.getId())
                .orElse(null);

        if (vehicleStatus != null) {
            VehicleInfoDto vehicleInfo = new VehicleInfoDto();
            vehicleInfo.setEngineHealth(vehicleStatus.getEngineStatus());
            vehicleInfo.setFuelLevel(vehicleStatus.getFuelLevel() + "%");
            vehicleInfo.setNextService("Due at " + vehicleStatus.getNextMaintenanceDue() + " km");
            vehicleInfo.setMileageToday(vehicleStatus.getCurrentKm() + " km");
            response.setVehicleInfo(vehicleInfo);
        }

        // Performance Info (dummy for now)
        PerformanceInfoDto performance = new PerformanceInfoDto();
        performance.setAttendance("Present");
        performance.setTripsCompleted("8/8");
        performance.setOnTimePerformance("100%");
        response.setPerformanceInfo(performance);

        return response;
    }
    
 // ---------------- PUT ----------------

    public VehicleStatus updateVehicleStatus(String id, VehicleStatus updatedStatus) {
        VehicleStatus existing = vehicleStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VehicleStatus not found with id: " + id));

        existing.setFuelLevel(updatedStatus.getFuelLevel());
        existing.setEngineStatus(updatedStatus.getEngineStatus());
        existing.setCurrentKm(updatedStatus.getCurrentKm());
        existing.setNextMaintenanceDue(updatedStatus.getNextMaintenanceDue());

        return vehicleStatusRepository.save(existing);
    }

    public BusRoute updateBusRoute(String id, BusRoute updatedRoute) {
        BusRoute existing = busRouteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BusRoute not found with id: " + id));

        existing.setRouteNumber(updatedRoute.getRouteNumber());
        existing.setDriverName(updatedRoute.getDriverName());
        existing.setCurrentLocation(updatedRoute.getCurrentLocation());
        existing.setNextStop(updatedRoute.getNextStop());
        existing.setEta(updatedRoute.getEta());
        existing.setStatus(updatedRoute.getStatus());
        existing.setStops(updatedRoute.getStops()); // ✅ if stops is a JSON/array field

        return busRouteRepository.save(existing);
    }

    // ---------------- DELETE ----------------

    public void deleteVehicleStatus(String id) {
        if (!vehicleStatusRepository.existsById(id)) {
            throw new RuntimeException("VehicleStatus not found with id: " + id);
        }
        vehicleStatusRepository.deleteById(id);
    }

    public void deleteBusRoute(String id) {
        if (!busRouteRepository.existsById(id)) {
            throw new RuntimeException("BusRoute not found with id: " + id);
        }
        busRouteRepository.deleteById(id);
    }


}
