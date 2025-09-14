package com.nimblix.driverdashboard.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimblix.driverdashboard.dto.FuelLogDto;
import com.nimblix.driverdashboard.dto.FuelSummaryDto;
import com.nimblix.driverdashboard.dto.FuelTrendDto;
import com.nimblix.driverdashboard.repository.FuelRepository;
import com.nimblix.driverdashboard.repository.VehicleRepository;
import com.nimblix.driverdashboard.model.FuelLog;
import com.nimblix.driverdashboard.model.VehicleTracking;

@Service
public class FuelService {

    @Autowired
    private FuelRepository fuelRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    /**
     * Dashboard summary for a driver
     */
    public FuelSummaryDto getFuelSummary(String driverId) {
        FuelSummaryDto summary = new FuelSummaryDto();

        // 🔹 1. Current Fuel Status
        VehicleTracking vehicle = vehicleRepository.findFirstByDriverIdOrderByTimestampDesc(driverId);
        FuelSummaryDto.CurrentFuelStatus status = new FuelSummaryDto.CurrentFuelStatus();
        if (vehicle != null) {
            status.setPercentage(vehicle.getFuelLevel());
            status.setOdometerKm(vehicle.getCurrentKm());
            // simple range estimate: assume 12 km/l target mileage
            status.setEstimatedRangeKm(vehicle.getFuelLevel() * 1.0 * 5);
        }
        summary.setCurrentFuelStatus(status);

        // 🔹 2. Monthly Consumption
        YearMonth thisMonth = YearMonth.now();
        LocalDate start = thisMonth.atDay(1);
        LocalDate end = thisMonth.atEndOfMonth();

        List<FuelLog> thisMonthLogs = fuelRepository.findByDriverIdAndDateBetweenOrderByDateAsc(
                driverId,
                Timestamp.valueOf(start.atStartOfDay()),
                Timestamp.valueOf(end.atTime(23, 59, 59))
        );

        FuelSummaryDto.MonthlyConsumption consumption = new FuelSummaryDto.MonthlyConsumption();
        if (!thisMonthLogs.isEmpty()) {
            double totalLiters = thisMonthLogs.stream().mapToDouble(FuelLog::getLiters).sum();
            double totalCost = thisMonthLogs.stream().mapToDouble(FuelLog::getCost).sum();
            int distance = thisMonthLogs.get(thisMonthLogs.size() - 1).getOdometerKm()
                    - thisMonthLogs.get(0).getOdometerKm();
            double mileage = (totalLiters > 0) ? (distance / totalLiters) : 0.0;

            consumption.setTotalFuelLiters(totalLiters);
            consumption.setTotalCost(totalCost);
            consumption.setDistanceKm(distance);
            consumption.setMileageKmpl(mileage);
        }
        summary.setMonthlyConsumption(consumption);

        // 🔹 3. Cost Analysis (compare with last month)
        YearMonth lastMonth = thisMonth.minusMonths(1);
        LocalDate lastStart = lastMonth.atDay(1);
        LocalDate lastEnd = lastMonth.atEndOfMonth();

        List<FuelLog> lastMonthLogs = fuelRepository.findByDriverIdAndDateBetweenOrderByDateAsc(
                driverId,
                Timestamp.valueOf(lastStart.atStartOfDay()),
                Timestamp.valueOf(lastEnd.atTime(23, 59, 59))
        );

        FuelSummaryDto.CostAnalysis analysis = new FuelSummaryDto.CostAnalysis();
        if (!thisMonthLogs.isEmpty()) {
            analysis.setFuelEfficiency(
                    consumption.getMileageKmpl() >= analysis.getTargetMileage() ? "Good" : "Poor"
            );

            if (!lastMonthLogs.isEmpty()) {
                int lastDistance = lastMonthLogs.get(lastMonthLogs.size() - 1).getOdometerKm()
                        - lastMonthLogs.get(0).getOdometerKm();
                double lastLiters = lastMonthLogs.stream().mapToDouble(FuelLog::getLiters).sum();
                double lastMileage = (lastLiters > 0) ? (lastDistance / lastLiters) : 0.0;

                double change = ((consumption.getMileageKmpl() - lastMileage) / lastMileage) * 100;
                analysis.setChangeVsLastMonth(change);
            }
        }
        summary.setCostAnalysis(analysis);

        // 🔹 4. Recent Logs
        List<FuelLog> recentLogs = fuelRepository.findTop5ByDriverIdOrderByDateDesc(driverId);
        List<FuelLogDto> logDtos = recentLogs.stream()
                .map(log -> new FuelLogDto(
                        log.getDate().toString(),
                        log.getStation(),
                        log.getLiters(),
                        log.getCost(),
                        log.getOdometerKm(),
                        log.getDriverId()
                ))
                .collect(Collectors.toList());

        summary.setRecentLogs(logDtos);

        return summary;
    }

    /**
     * Recent fuel logs
     */
    public List<FuelLogDto> getRecentLogs(String driverId) {
        return fuelRepository.findTop5ByDriverIdOrderByDateDesc(driverId).stream()
                .map(log -> new FuelLogDto(
                        log.getDate().toString(),
                        log.getStation(),
                        log.getLiters(),
                        log.getCost(),
                        log.getOdometerKm(),
                        log.getDriverId()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Trends for last 6 months
     */
    public List<FuelTrendDto> getFuelTrends(String driverId) {
        List<FuelTrendDto> trends = new ArrayList<>();

        for (int i = 5; i >= 0; i--) {
            YearMonth month = YearMonth.now().minusMonths(i);
            LocalDate start = month.atDay(1);
            LocalDate end = month.atEndOfMonth();

            List<FuelLog> logs = fuelRepository.findByDriverIdAndDateBetweenOrderByDateAsc(
                    driverId,
                    Timestamp.valueOf(start.atStartOfDay()),
                    Timestamp.valueOf(end.atTime(23, 59, 59))
            );

            if (!logs.isEmpty()) {
                double liters = logs.stream().mapToDouble(FuelLog::getLiters).sum();
                double cost = logs.stream().mapToDouble(FuelLog::getCost).sum();
                int distance = logs.get(logs.size() - 1).getOdometerKm() - logs.get(0).getOdometerKm();
                double mileage = (liters > 0) ? (distance / liters) : 0.0;

                trends.add(new FuelTrendDto(
                        month.toString(),
                        liters,
                        cost,
                        distance,
                        mileage
                ));
            } else {
                trends.add(new FuelTrendDto(
                        month.toString(),
                        0.0,
                        0.0,
                        0,
                        0.0
                ));
            }
        }

        return trends;
    }

    /**
     * Add new fuel log
     */
  

    public FuelLogDto addFuelLog(FuelLogDto fuelLogDto) {
        FuelLog log = new FuelLog();
        log.setId(UUID.randomUUID().toString()); // ✅ manually generate UUID

        // 🔹 Convert String → Timestamp safely
        if (fuelLogDto.getDate() != null) {
            log.setDate(Timestamp.valueOf(fuelLogDto.getDate())); 
            // make sure dto.getDate() is in format: "yyyy-MM-dd HH:mm:ss"
        }

        log.setStation(fuelLogDto.getStation());
        log.setLiters(fuelLogDto.getLiters());
        log.setCost(fuelLogDto.getCost());
        log.setOdometerKm(fuelLogDto.getOdometerKm());
        log.setDriverId(fuelLogDto.getDriverId());

        FuelLog saved = fuelRepository.save(log);

        // 🔹 Convert Timestamp → String for DTO return
        return new FuelLogDto(
            (saved.getDate() != null) ? saved.getDate().toString() : null,
            saved.getStation(),
            saved.getLiters(),
            saved.getCost(),
            saved.getOdometerKm(),
            saved.getDriverId()
        );
    }
    
    /**
     * Update an existing fuel log
     */
    public FuelLogDto updateFuelLog(String id, FuelLogDto dto) {
        FuelLog existing = fuelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fuel log not found with id " + id));

        // 🔹 Update fields if provided, else keep existing values
        if (dto.getDate() != null) {
            existing.setDate(Timestamp.valueOf(dto.getDate())); 
        }
        existing.setStation(dto.getStation() != null ? dto.getStation() : existing.getStation());
        existing.setLiters(dto.getLiters() != 0 ? dto.getLiters() : existing.getLiters());
        existing.setCost(dto.getCost() != 0 ? dto.getCost() : existing.getCost());
        existing.setOdometerKm(dto.getOdometerKm() != 0 ? dto.getOdometerKm() : existing.getOdometerKm());
        existing.setDriverId(dto.getDriverId() != null ? dto.getDriverId() : existing.getDriverId());

        FuelLog updated = fuelRepository.save(existing);

        return new FuelLogDto(
                (updated.getDate() != null) ? updated.getDate().toString() : null,
                updated.getStation(),
                updated.getLiters(),
                updated.getCost(),
                updated.getOdometerKm(),
                updated.getDriverId()
        );
    }

    /**
     * Delete a fuel log
     */
    public void deleteFuelLog(String id) {
        if (!fuelRepository.existsById(id)) {
            throw new RuntimeException("Fuel log not found with id " + id);
        }
        fuelRepository.deleteById(id);
    }



}
