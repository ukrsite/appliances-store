package com.appliances.service.impl;

import com.appliances.model.Orders;
import com.appliances.repository.OrdersRepository;
import com.appliances.service.OrderService;
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
