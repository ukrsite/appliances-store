package com.epam.rd.autocode.assessment.appliances.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appliance_id", nullable = false)
    private Appliance appliance;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal amount;
}
