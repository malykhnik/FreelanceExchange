package com.malykhnik.freelanceexchnge.repository;

import com.malykhnik.freelanceexchnge.model.FreelanceAnnouncement;
import com.malykhnik.freelanceexchnge.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<FreelanceAnnouncement, Long> {
    @Query("SELECT a FROM FreelanceAnnouncement a JOIN a.user u WHERE u.username = :username")
    List<FreelanceAnnouncement> findAnnouncementByUsername(@Param("username") String username);
}
