package com.epam.rd.autocode.assessment.appliances.service.impl;

import com.epam.rd.autocode.assessment.appliances.model.*;
import com.epam.rd.autocode.assessment.appliances.repository.ApplianceRepository;
import com.epam.rd.autocode.assessment.appliances.repository.ClientRepository;
import com.epam.rd.autocode.assessment.appliances.repository.EmployeeRepository;
import com.epam.rd.autocode.assessment.appliances.repository.OrdersRepository;
import com.epam.rd.autocode.assessment.appliances.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ApplianceRepository applianceRepository;

    @Override
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders findById(Long id) {
        return ordersRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public void save(Orders order) {
        ordersRepository.save(order);
    }

    @Override
    public void update(Orders order) {
        if (!ordersRepository.existsById(order.getId())) {
            throw new RuntimeException("Order not found");
        }
        ordersRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        if (!ordersRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        ordersRepository.deleteById(id);
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Appliance> findAllAppliances() {
        return applianceRepository.findAll();
    }

    @Transactional
    public void addApplianceToOrder(Long orderId, Long applianceId, Integer quantity, BigDecimal price) {
        // Fetch the order
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        // Fetch the appliance
        Appliance appliance = applianceRepository.findById(applianceId)
                .orElseThrow(() -> new IllegalArgumentException("Appliance not found"));

        // Create a new OrderRow
        OrderRow orderRow = new OrderRow();
        orderRow.setOrder(order);
        orderRow.setAppliance(appliance);
        orderRow.setQuantity(quantity);
        orderRow.setAmount(price);

        System.out.println("orderRow = " + orderRow);

        // Add the OrderRow to the order
        order.getOrderRowSet().add(orderRow);

        // Save the updated order
        ordersRepository.save(order);
    }
}
