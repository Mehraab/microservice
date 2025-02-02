package com.example.order.service;

import com.example.order.dto.NotificationDto;
import com.example.order.dto.OrderDto;
import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;
import com.example.order.utils.ParseClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    @Autowired
    ParseClaims parseClaims;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    NotificationService notificationService;

    public String placeOrder(String token, OrderDto orderDto) {
        String userId = parseClaims.getClaims(token);
        orderDto.setUserId(userId);
        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        order.setItemId(orderDto.getItemId());
        order.setQuantity(orderDto.getQuantity());
        order.setStatus(orderDto.getStatus());
        order = orderRepository.save(order);
        orderDto.setOrderId(order.getOrderId());
        orderDto.setTotalAmount(0);
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setUserId(orderDto.getUserId());
        notificationDto.setMessage("Your order has been placed successfully");
        kafkaTemplate.send("orderPlaced", orderDto);
        kafkaTemplate.send("orderProcessed", notificationDto);
        return "order placed";
    }
}