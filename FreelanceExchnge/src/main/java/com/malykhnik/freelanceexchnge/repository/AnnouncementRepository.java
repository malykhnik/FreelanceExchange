package com.malykhnik.freelanceexchnge.repository;

import com.malykhnik.freelanceexchnge.model.FreelanceAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<FreelanceAnnouncement, Long> {
}
