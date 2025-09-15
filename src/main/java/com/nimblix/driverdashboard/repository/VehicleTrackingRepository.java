package com.nimblix.driverdashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nimblix.driverdashboard.model.VehicleTracking;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleTrackingRepository extends JpaRepository<VehicleTracking, UUID> {

    // fetch latest by driverId
    Optional<VehicleTracking> findTopByDriverIdOrderByTimestampDesc(String driverId);

    // fetch latest by vehicleNumber
    Optional<VehicleTracking> findTopByVehicleNumberOrderByTimestampDesc(String vehicleNumber);

    // alternative naming (Spring Data understands both)
    VehicleTracking findFirstByDriverIdOrderByTimestampDesc(String driverId);
}
