package com.epam.rd.autocode.assessment.appliances.controller.rest;

import com.epam.rd.autocode.assessment.appliances.aspect.Loggable;
import com.epam.rd.autocode.assessment.appliances.model.Order;
import com.epam.rd.autocode.assessment.appliances.model.enums.Status;
import com.epam.rd.autocode.assessment.appliances.service.OrderService;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class RestOrderController {
    private final OrderService orderService;

    public RestOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Loggable
    @GetMapping
    public ResponseEntity<Page<Order>> getAllOrders(
            @PageableDefault(size = 5, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(orderService.getAllOrders(pageable));
    }

    @Loggable
    @PostMapping("/create")
    public ResponseEntity<Order> addOrder() {
        return ResponseEntity.ok(orderService.createOrder());
    }

    @Loggable
    @PatchMapping("/cancel")
    public ResponseEntity<Void> cancelOrder(@RequestParam("id") @Min(1) Long id) {
        orderService.updateStatusById(id, Status.CANCELLED);
        return ResponseEntity.ok().build();
    }

    @Loggable
    @PatchMapping("/update-status")
    public ResponseEntity<Void> updateStatus(@RequestParam("id") @Min(1) Long id, @RequestParam("status") Status status) {
        orderService.updateStatusById(id, status);
        return ResponseEntity.ok().build();
    }
}
