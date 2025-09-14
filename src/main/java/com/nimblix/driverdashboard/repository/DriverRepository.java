package com.nimblix.driverdashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nimblix.driverdashboard.model.Driver;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, String> {

	Optional<Driver> findByEmployeeId(String employeeId);
	Optional<Driver> findByVehicleAssigned(String vehicleNumber);


}
