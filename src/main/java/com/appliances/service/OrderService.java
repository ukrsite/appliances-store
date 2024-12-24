package com.appliances.service;

import com.appliances.model.Orders;

public interface OrderService {
    Iterable<Orders> getAllOrders();
    Orders getOrder(long id);
    Orders save(Orders order);
}
