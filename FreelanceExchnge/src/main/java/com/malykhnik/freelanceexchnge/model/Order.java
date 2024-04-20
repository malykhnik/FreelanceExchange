package com.malykhnik.freelanceexchnge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_name")
    private String orderName;

    private String description;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'Active'")
    private String status = "Active";
}
