package com.example.inventory.service;

import com.example.inventory.dto.NotificationDto;
import com.example.inventory.dto.OrderDto;
import com.example.inventory.exception.ItemNotFoundException;
import com.example.inventory.exception.OrderNotFoundException;
import com.example.inventory.model.Item;
import com.example.inventory.model.Order;
import com.example.inventory.repository.ItemRepository;
import com.example.inventory.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderPlacingService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    NotificationService notificationService;

    @Transactional
    public void processOrder(OrderDto orderDto) {
        try{
            Long orderId = orderDto.getOrderId();
            Order order = orderRepository.findByOrderId(orderId).orElse(null);
            if (order == null) {
                kafkaTemplate.send("orderNotFound", notificationService.sendNotification(
                        orderDto.getUserId(),"Order not found for this id: " + orderDto.getOrderId().toString()));
                throw new OrderNotFoundException("Order not found");
            }
            Item item = itemRepository.findById(orderDto.getItemId()).orElse(null);
            if (item == null || item.getQuantity() < orderDto.getQuantity()) {
                kafkaTemplate.send("outOfStock", notificationService.sendNotification(
                        orderDto.getUserId(),"Item out of stock for the order " + order.getOrderId().toString()));
                throw new ItemNotFoundException("Item not found");
            }
            order.setTotalAmount(item.getPrice() * orderDto.getQuantity());
            order.setStatus("CONFIRMED");
            item.setQuantity(item.getQuantity() - orderDto.getQuantity());
            itemRepository.save(item);
            orderRepository.save(order);
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setUserId(orderDto.getUserId());
            notificationDto.setMessage("Your order has been confirmed with order id: " + orderDto.getOrderId().toString());
            kafkaTemplate.send("orderConfirmed", notificationDto);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
