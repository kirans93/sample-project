package com.nimblix.driverdashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nimblix.driverdashboard.model.BusRoute;

public interface BusRouteRepository extends JpaRepository<BusRoute, String> {

    BusRoute findByDriverName(String driverName);
    BusRoute findByRouteNumber(String routeNumber);

}
