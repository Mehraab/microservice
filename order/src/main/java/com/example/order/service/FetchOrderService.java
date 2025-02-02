package com.example.order.service;

import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;
import com.example.order.utils.ParseClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FetchOrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ParseClaims parseClaims;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public List<Order> getForOne(String token) {
        String userId = parseClaims.getClaims(token);
        return orderRepository.findByUserId(userId);
    }

    public Order getById(Long orderId) {
        return orderRepository.findByOrderId(orderId).orElse(null);
    }
}
