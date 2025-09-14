package com.nimblix.driverdashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nimblix.driverdashboard.model.FuelLog;

@Repository
public interface FuelRepository extends JpaRepository<FuelLog, String> {

    // Fetch recent 5 logs
    List<FuelLog> findTop5ByDriverIdOrderByDateDesc(String driverId);

    // Fetch all logs of a driver ordered by date
    List<FuelLog> findByDriverIdOrderByDateDesc(String driverId);

    // Fetch logs within a date range (useful for monthly stats)
    List<FuelLog> findByDriverIdAndDateBetweenOrderByDateAsc(String driverId,
                                                             java.sql.Timestamp startDate,
                                                             java.sql.Timestamp endDate);
}
