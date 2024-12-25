package com.epam.rd.autocode.assessment.appliances.service.impl;

import com.epam.rd.autocode.assessment.appliances.model.Orders;
import com.epam.rd.autocode.assessment.appliances.repository.OrdersRepository;
import com.epam.rd.autocode.assessment.appliances.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrdersRepository ordersRepository;

    public Iterable<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public Orders getOrder(long id) {
        return ordersRepository.getReferenceById(id);
    }

    public Orders save(Orders order) {
        return save(order);
    }
}
