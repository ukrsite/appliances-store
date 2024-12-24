package com.appliances.repository;

import com.appliances.model.OrderRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplianceInOrderRepository extends JpaRepository<OrderRow, Long> {

}
