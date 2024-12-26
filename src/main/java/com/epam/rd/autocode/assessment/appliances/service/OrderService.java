package com.epam.rd.autocode.assessment.appliances.service;

import com.epam.rd.autocode.assessment.appliances.model.Appliance;
import com.epam.rd.autocode.assessment.appliances.model.Client;
import com.epam.rd.autocode.assessment.appliances.model.Employee;
import com.epam.rd.autocode.assessment.appliances.model.Orders;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Orders> findAll();
    Orders findById(Long id);
    void save(Orders order);
    void update(Orders order);
    void deleteById(Long id);

    List<Client> findAllClients();
    List<Employee> findAllEmployees();
    List<Appliance> findAllAppliances();

    void addApplianceToOrder(Long orderId, Long applianceId, Integer quantity, BigDecimal price);

    Optional<Orders> findByIdWithRows(@Param("orderId") Long orderId);
    boolean setApprovalStatus(Long id, boolean status);
}
