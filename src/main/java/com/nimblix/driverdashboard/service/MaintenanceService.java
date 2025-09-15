package com.nimblix.driverdashboard.service;

import com.nimblix.driverdashboard.dto.MaintenanceItem;
import com.nimblix.driverdashboard.dto.MaintenanceSummary;
import com.nimblix.driverdashboard.exception.DeleteFailedException;
import com.nimblix.driverdashboard.exception.ResourceNotFoundException;
import com.nimblix.driverdashboard.exception.SaveFailedException;
import com.nimblix.driverdashboard.exception.UpdateFailedException;
import com.nimblix.driverdashboard.model.MaintenanceItemEntity;
import com.nimblix.driverdashboard.repository.MaintenanceItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceItemRepository repository;

    /**
     * Add new maintenance item
     */
    public MaintenanceItem addMaintenanceItem(MaintenanceItem dto) {
        try {
            MaintenanceItemEntity entity = new MaintenanceItemEntity(
                    UUID.randomUUID().toString(),   // Generate unique ID
                    dto.getTitle(),
                    dto.getPriority(),
                    dto.getCategory(),
                    dto.getServiceType(),
                    dto.getAction(),
                    dto.getStatus(),
                    dto.getEstimatedCost()
            );

            MaintenanceItemEntity saved = repository.save(entity);

            return new MaintenanceItem(
                    saved.getTitle(),
                    saved.getPriority(),
                    saved.getCategory(),
                    saved.getServiceType(),
                    saved.getAction(),
                    saved.getStatus(),
                    saved.getEstimatedCost()
            );
        } catch (Exception e) {
            throw new SaveFailedException("Failed to save maintenance item: " + e.getMessage());
        }
    }

    /**
     * Get all maintenance items
     */
    public List<MaintenanceItem> getAllMaintenanceItems() {
        return repository.findAll().stream()
                .map(entity -> new MaintenanceItem(
                        entity.getTitle(),
                        entity.getPriority(),
                        entity.getCategory(),
                        entity.getServiceType(),
                        entity.getAction(),
                        entity.getStatus(),
                        entity.getEstimatedCost()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Get summary (Urgent / Scheduled / Completed)
     */
    public MaintenanceSummary getSummary() {
        List<MaintenanceItemEntity> items = repository.findAll();

        long urgent = items.stream()
                .filter(i -> "High".equalsIgnoreCase(i.getPriority())
                          && !"Completed".equalsIgnoreCase(i.getStatus()))
                .count();

        long scheduled = items.stream()
                .filter(i -> "Scheduled".equalsIgnoreCase(i.getStatus()))
                .count();

        long completed = items.stream()
                .filter(i -> "Completed".equalsIgnoreCase(i.getStatus()))
                .count();

        return new MaintenanceSummary((int) urgent, (int) scheduled, (int) completed);
    }

    /**
     * Update an existing maintenance item
     */
    public MaintenanceItem updateMaintenanceItem(String id, MaintenanceItem dto) {
        MaintenanceItemEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance item not found with id " + id));

        try {
            existing.setTitle(dto.getTitle() != null ? dto.getTitle() : existing.getTitle());
            existing.setPriority(dto.getPriority() != null ? dto.getPriority() : existing.getPriority());
            existing.setCategory(dto.getCategory() != null ? dto.getCategory() : existing.getCategory());
            existing.setServiceType(dto.getServiceType() != null ? dto.getServiceType() : existing.getServiceType());
            existing.setAction(dto.getAction() != null ? dto.getAction() : existing.getAction());
            existing.setStatus(dto.getStatus() != null ? dto.getStatus() : existing.getStatus());
            existing.setEstimatedCost(dto.getEstimatedCost() != 0 ? dto.getEstimatedCost() : existing.getEstimatedCost());

            MaintenanceItemEntity updated = repository.save(existing);

            return new MaintenanceItem(
                    updated.getTitle(),
                    updated.getPriority(),
                    updated.getCategory(),
                    updated.getServiceType(),
                    updated.getAction(),
                    updated.getStatus(),
                    updated.getEstimatedCost()
            );
        } catch (Exception e) {
            throw new UpdateFailedException("Failed to update maintenance item: " + e.getMessage());
        }
    }

    /**
     * Delete a maintenance item
     */
    public void deleteMaintenanceItem(String id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Maintenance item not found with id " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new DeleteFailedException("Failed to delete maintenance item with id " + id);
        }
    }
}
