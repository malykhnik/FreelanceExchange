package com.malykhnik.freelanceexchnge.repository;

import com.malykhnik.freelanceexchnge.model.EventCatcher;
import jakarta.persistence.OrderBy;
import org.junit.jupiter.api.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventCatcherRepository extends JpaRepository<EventCatcher, Long> {
    List<EventCatcher> findByOrderByDateDesc();
}
