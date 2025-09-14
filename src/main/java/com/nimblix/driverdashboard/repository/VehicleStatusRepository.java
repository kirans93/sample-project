package com.nimblix.driverdashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nimblix.driverdashboard.model.VehicleStatus;

import java.util.Optional;

public interface VehicleStatusRepository extends JpaRepository<VehicleStatus, String> {

    // fetch latest by driverId
    Optional<VehicleStatus> findTopByDriverIdOrderByTimestampDesc(String driverId);

    // fetch latest by vehicleNumber
    Optional<VehicleStatus> findTopByVehicleNumberOrderByTimestampDesc(String vehicleNumber);
}
