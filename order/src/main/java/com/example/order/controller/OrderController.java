package com.example.order.controller;

import com.example.order.dto.OrderDto;
import com.example.order.model.Order;
import com.example.order.service.FetchOrderService;
import com.example.order.service.OrderService;
import com.example.order.utils.ParseClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    FetchOrderService fetchOrderService;

    @GetMapping("/getAll")
    public List<Order> order() {
        return fetchOrderService.getAll();
    }

    @GetMapping("/get-for-one-user")
    public List<Order> getForOneUser(@RequestHeader("Authorization") String token) {
        return fetchOrderService.getForOne(token);
    }

    @GetMapping("/get-one-order")
    public Order getByOrderId(@RequestParam Long orderId) {
        return fetchOrderService.getById(orderId);
    }

    @PostMapping("/place")
    public String placeOrder(@RequestHeader("Authorization") String token, @RequestBody OrderDto orderDto) {
        return orderService.placeOrder(token, orderDto);
    }
}
