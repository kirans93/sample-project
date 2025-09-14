package com.nimblix.driverdashboard.controller;

import com.nimblix.driverdashboard.service.AnnouncementService;
import com.nimblix.driverdashboard.dto.AnnouncementInfoDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    // ---------------- GET ----------------

    /** Get latest 5 announcements */
    @GetMapping("/recent")
    public ResponseEntity<List<AnnouncementInfoDto>> getRecentAnnouncements() {
        return ResponseEntity.ok(announcementService.getRecentAnnouncements());
    }

    /** Get all announcements */
    @GetMapping
    public ResponseEntity<List<AnnouncementInfoDto>> getAllAnnouncements() {
        return ResponseEntity.ok(announcementService.getAllAnnouncements());
    }

    // ---------------- POST ----------------

    /** Create a new announcement */
    @PostMapping
    public ResponseEntity<AnnouncementInfoDto> createAnnouncement(@RequestBody AnnouncementInfoDto dto) {
        AnnouncementInfoDto saved = announcementService.createAnnouncement(dto);
        return ResponseEntity.ok(saved);
    }
    
 // ---------------- PUT ----------------

    /** Update an announcement by ID */
    @PutMapping("/{id}")
    public ResponseEntity<AnnouncementInfoDto> updateAnnouncement(
            @PathVariable String id,
            @RequestBody AnnouncementInfoDto dto) {
        AnnouncementInfoDto updated = announcementService.updateAnnouncement(id, dto);
        return ResponseEntity.ok(updated);
    }

    // ---------------- DELETE ----------------

    /** Delete an announcement by ID */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable String id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.noContent().build();
    }

}
