package com.epam.rd.autocode.assessment.appliances.controller;

import com.epam.rd.autocode.assessment.appliances.model.Orders;
import com.epam.rd.autocode.assessment.appliances.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String listOrders(Model model) {
        List<Orders> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "order/orders";
    }

    @GetMapping("/{id}")
    public String viewOrder(@PathVariable Long id, Model model) {
        Orders order = orderService.findById(id);
        model.addAttribute("order", order);
        return "order/editOrder";
    }

    @GetMapping("/add")
    public String newOrder(Model model) {
        model.addAttribute("order", new Orders());
        model.addAttribute("clients", orderService.findAllClients());
        model.addAttribute("employees", orderService.findAllEmployees());
        model.addAttribute("appliances", orderService.findAllAppliances());
        return "order/newOrder";
    }

    @GetMapping("/edit/choice-appliance")
    public String showAppliances(@RequestParam("ordersId") Long ordersId, Model model) {
        model.addAttribute("appliances", orderService.findAllAppliances());
        model.addAttribute("ordersId", ordersId); // Pass the ordersId to the view
        return "order/choiceAppliance";
    }

    @PostMapping("/add-order")
    public String createOrder(@ModelAttribute Orders order) {
        orderService.save(order);
        return "redirect:/orders";
    }

    @PostMapping("/edit/add-into-order")
    public String addIntoOrder(
            @RequestParam("ordersId") Long orderId,
            @RequestParam("applianceId") Long applianceId,
            @RequestParam("numbers") Integer quantity,
            @RequestParam("price") BigDecimal price,
            Model model) {
        System.out.println("Appliance added to order -> " + orderId);
        try {
            orderService.addApplianceToOrder(orderId, applianceId, quantity, price);
            return "redirect:/orders/edit/" + orderId; // Redirect to order details or order list
        } catch (Exception e) {
            model.addAttribute("error", "Failed to add appliance to order: " + e.getMessage());
            return "generalError"; // Replace with your error page template
        }
    }

    @GetMapping("/edit/{id}")
    public String editOrderForm(@PathVariable Long id, Model model) {
        Orders order = orderService.findById(id);
        model.addAttribute("order", order);
        model.addAttribute("clients", orderService.findAllClients());
        model.addAttribute("employees", orderService.findAllEmployees());
        model.addAttribute("appliances", orderService.findAllAppliances());
        return "order/editOrder";
    }

    @PostMapping("/update")
    public String updateOrder(@ModelAttribute Orders order) {
        orderService.update(order);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return "redirect:/orders";
    }
}
