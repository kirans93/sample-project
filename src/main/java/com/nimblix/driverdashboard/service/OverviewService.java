package com.nimblix.driverdashboard.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nimblix.driverdashboard.model.BusRoute;
import com.nimblix.driverdashboard.model.Driver;
import com.nimblix.driverdashboard.model.VehicleTracking;
import com.nimblix.driverdashboard.dto.DriverInfoDto;
import com.nimblix.driverdashboard.dto.OverviewResponse;
import com.nimblix.driverdashboard.dto.PerformanceInfoDto;
import com.nimblix.driverdashboard.dto.VehicleInfoDto;
import com.nimblix.driverdashboard.repository.BusRouteRepository;
import com.nimblix.driverdashboard.repository.DriverRepository;
import com.nimblix.driverdashboard.repository.VehicleTrackingRepository;
import com.nimblix.driverdashboard.exception.ResourceNotFoundException;

@Service
public class OverviewService {

    private final DriverRepository driverRepository;
    private final VehicleTrackingRepository vehicleTrackingRepository;
    private final BusRouteRepository busRouteRepository;

    public OverviewService(
            DriverRepository driverRepository,
            VehicleTrackingRepository vehicleTrackingRepository,
            BusRouteRepository busRouteRepository) {
        this.driverRepository = driverRepository;
        this.vehicleTrackingRepository = vehicleTrackingRepository;
        this.busRouteRepository = busRouteRepository;
    }

    // ---------------- GET ----------------

    public OverviewResponse getDriverOverview(String driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: " + driverId));
        return buildOverviewResponse(driver);
    }

    public OverviewResponse getDriverOverviewByEmployeeId(String employeeId) {
        Driver driver = driverRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with employeeId: " + employeeId));
        return buildOverviewResponse(driver);
    }

    public OverviewResponse getDriverOverviewByVehicle(String vehicleNumber) {
        Driver driver = driverRepository.findByVehicleAssigned(vehicleNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with vehicle: " + vehicleNumber));
        return buildOverviewResponse(driver);
    }
    
    public BusRoute getBusRouteById(String id) {
        return busRouteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BusRoute not found with id: " + id));
    }

    public List<BusRoute> getAllBusRoutes() {
        return busRouteRepository.findAll();
    }

    // ---------------- POST ----------------

    public VehicleTracking saveVehicleTracking(VehicleTracking vehicleTracking) {
        vehicleTracking.setId(UUID.randomUUID());
        return vehicleTrackingRepository.save(vehicleTracking);
    }

    public BusRoute saveBusRoute(BusRoute busRoute) {
        busRoute.setId(UUID.randomUUID().toString());
        return busRouteRepository.save(busRoute);
    }
    
    public Driver saveDriver(Driver driver) {
        driver.setId(UUID.randomUUID().toString());
        return driverRepository.save(driver);
    }

    // ---------------- PUT ----------------

    public VehicleTracking updateVehicleTracking(UUID id, VehicleTracking updatedTracking) {
        VehicleTracking existing = vehicleTrackingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleTracking not found with id: " + id));

        existing.setFuelLevel(updatedTracking.getFuelLevel());
        existing.setEngineStatus(updatedTracking.getEngineStatus());
        existing.setCurrentKm(updatedTracking.getCurrentKm());
        existing.setNextMaintenanceDue(updatedTracking.getNextMaintenanceDue());

        return vehicleTrackingRepository.save(existing);
    }

    public BusRoute updateBusRoute(String id, BusRoute updatedRoute) {
        BusRoute existing = busRouteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BusRoute not found with id: " + id));

        existing.setRouteNumber(updatedRoute.getRouteNumber());
        existing.setDriverName(updatedRoute.getDriverName());
        existing.setCurrentLocation(updatedRoute.getCurrentLocation());
        existing.setNextStop(updatedRoute.getNextStop());
        existing.setEta(updatedRoute.getEta());
        existing.setStatus(updatedRoute.getStatus());
        existing.setStops(updatedRoute.getStops());

        return busRouteRepository.save(existing);
    }
    
    public Driver updateDriver(String id, Driver updatedDriver) {
        Driver existing = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: " + id));

        existing.setEmployeeId(updatedDriver.getEmployeeId());
        existing.setDepartment(updatedDriver.getDepartment());
        existing.setShift(updatedDriver.getShift());
        existing.setVehicleAssigned(updatedDriver.getVehicleAssigned());
        existing.setPhone(updatedDriver.getPhone());

        return driverRepository.save(existing);
    }

    // ---------------- DELETE ----------------

    public void deleteVehicleTracking(UUID id) {
        if (!vehicleTrackingRepository.existsById(id)) {
            throw new ResourceNotFoundException("VehicleTracking not found with id: " + id);
        }
        vehicleTrackingRepository.deleteById(id);
    }

    public void deleteBusRoute(String id) {
        if (!busRouteRepository.existsById(id)) {
            throw new ResourceNotFoundException("BusRoute not found with id: " + id);
        }
        busRouteRepository.deleteById(id);
    }

    public void deleteDriver(String id) {
        if (!driverRepository.existsById(id)) {
            throw new ResourceNotFoundException("Driver not found with id: " + id);
        }
        driverRepository.deleteById(id);
    }

    // ---------------- PRIVATE HELPERS ----------------

    private OverviewResponse buildOverviewResponse(Driver driver) {
        OverviewResponse response = new OverviewResponse();

        DriverInfoDto driverInfo = new DriverInfoDto();
        driverInfo.setDepartment(driver.getDepartment());
        driverInfo.setDriverId(driver.getId());
        driverInfo.setShift(driver.getShift());
        driverInfo.setVehicleAssigned(driver.getVehicleAssigned());
        driverInfo.setPhone(driver.getPhone());
        response.setDriverInfo(driverInfo);

        VehicleTracking vehicleTracking = vehicleTrackingRepository
                .findTopByDriverIdOrderByTimestampDesc(driver.getId())
                .orElse(null);

        if (vehicleTracking != null) {
            VehicleInfoDto vehicleInfo = new VehicleInfoDto();
            vehicleInfo.setEngineHealth(vehicleTracking.getEngineStatus());
            vehicleInfo.setFuelLevel(vehicleTracking.getFuelLevel() + "%");
            vehicleInfo.setNextService("Due at " + vehicleTracking.getNextMaintenanceDue() + " km");
            vehicleInfo.setMileageToday(vehicleTracking.getCurrentKm() + " km");
            response.setVehicleInfo(vehicleInfo);
        }

        PerformanceInfoDto performance = new PerformanceInfoDto();
        performance.setAttendance("Present");
        performance.setTripsCompleted("8/8");
        performance.setOnTimePerformance("100%");
        response.setPerformanceInfo(performance);

        return response;
    }
}
