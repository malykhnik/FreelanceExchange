package com.malykhnik.freelanceexchnge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchase_request")
@Data
@NoArgsConstructor
public class PurchaseRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_from")
    private User userFrom;

    @ManyToOne
    @JoinColumn(name = "user_to")
    private User userTo;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'NONE'")
    private String status;

    public PurchaseRequest(User from, User to, Order order, String status) {
        this.userTo = to;
        this.userFrom = from;
        this.order = order;
        this.status = status;
    }

}
