package com.nimblix.driverdashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nimblix.driverdashboard.model.MaintenanceItemEntity;

public interface MaintenanceItemRepository extends JpaRepository<MaintenanceItemEntity, String> {
	
}
