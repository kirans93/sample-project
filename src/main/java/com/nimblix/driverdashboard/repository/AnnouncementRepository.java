package com.nimblix.driverdashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nimblix.driverdashboard.model.Announcement;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, String> {
    
    // Fetch the 5 most recent announcements
    List<Announcement> findTop5ByOrderByCreatedAtDesc();
}
