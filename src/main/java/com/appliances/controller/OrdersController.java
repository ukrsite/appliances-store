package com.appliances.controller;

import com.appliances.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String showOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "order/orders";
    }
}
