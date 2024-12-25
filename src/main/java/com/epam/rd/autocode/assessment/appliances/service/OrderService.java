package com.epam.rd.autocode.assessment.appliances.service;

import com.epam.rd.autocode.assessment.appliances.model.Orders;

public interface OrderService {
    Iterable<Orders> getAllOrders();
    Orders getOrder(long id);
    Orders save(Orders order);
}
