package com.malykhnik.freelanceexchnge.model;

import com.malykhnik.freelanceexchnge.service.EventCatcherService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_catcher")
public class EventCatcher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String action;
    private LocalDateTime date;
}
