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

//    @ManyToOne
//    @JoinColumn(name = "from_id")
    private Long userFrom;

    private Long userTo;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'NONE'")
    private String status;

    public PurchaseRequest(Long from, Long to, Order order, String status) {
        this.userTo = to;
        this.userFrom = from;
        this.order = order;
        this.status = status;
    }

}
