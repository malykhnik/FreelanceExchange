package com.malykhnik.freelanceexchnge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "freelance_announcement")
public class FreelanceAnnouncement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
