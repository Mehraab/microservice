package com.example.inventory.kafka.listeners;

import com.example.inventory.dto.OrderDto;
import com.example.inventory.service.OrderPlacingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumerService {
    @Autowired
    OrderPlacingService orderPlacingService;
    @KafkaListener(topics = "orderPlaced", groupId = "inventory")
    public void consume(OrderDto orderDto) {
        try{
            orderPlacingService.processOrder(orderDto);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
