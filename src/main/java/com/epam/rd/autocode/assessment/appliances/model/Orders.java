package com.epam.rd.autocode.assessment.appliances.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<OrderRow> orderRowSet = new HashSet<>();

    @Column(name = "approved")
    private boolean approved;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Utility methods for managing the bidirectional relationship
    public void addOrderRow(OrderRow orderRow) {
        orderRowSet.add(orderRow);
        orderRow.setOrder(this);
    }

    public void removeOrderRow(OrderRow orderRow) {
        orderRowSet.remove(orderRow);
        orderRow.setOrder(null);
    }

}
