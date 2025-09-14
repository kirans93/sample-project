package com.nimblix.driverdashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nimblix.driverdashboard.model.VehicleTracking;

import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleTracking, UUID> {

    // Latest vehicle record for a driver
    VehicleTracking findFirstByDriverIdOrderByTimestampDesc(String driverId);

	
}
