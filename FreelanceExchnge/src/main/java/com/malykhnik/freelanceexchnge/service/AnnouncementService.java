package com.malykhnik.freelanceexchnge.service;

import com.malykhnik.freelanceexchnge.model.FreelanceAnnouncement;

import java.util.List;
import java.util.Optional;

public interface AnnouncementService {
    void saveNewAnnouncement(FreelanceAnnouncement freelanceAnnouncement);
    List<FreelanceAnnouncement> getAllAnnouncements();
    List<FreelanceAnnouncement> findAnnouncementByUsername(String username);
    Optional<FreelanceAnnouncement> findAnnouncementById(Long id);
    void deleteAnnouncementById(Long id);
}
