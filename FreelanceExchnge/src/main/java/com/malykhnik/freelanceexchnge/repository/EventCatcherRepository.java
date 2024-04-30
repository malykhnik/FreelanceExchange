package com.malykhnik.freelanceexchnge.repository;

import com.malykhnik.freelanceexchnge.model.EventCatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventCatcherRepository extends JpaRepository<EventCatcher, Long> {
}
