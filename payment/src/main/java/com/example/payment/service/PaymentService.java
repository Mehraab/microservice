package com.example.payment.service;

import com.example.payment.dto.PaymentDto;
import com.example.payment.exception.IllegalPaymentRequestException;
import com.example.payment.factory.PaymentStrategyCreationFactory;
import com.example.payment.model.Order;
import com.example.payment.model.Payment;
import com.example.payment.process.PaymentProcessor;
import com.example.payment.repository.OrderRepository;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.utils.ParseClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {
    @Autowired
    ParseClaims parseClaims;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    PaymentProcessor paymentProcessor;
    @Autowired
    PaymentStrategyCreationFactory strategyCreationFactory;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;
    @Autowired
    NotificationService notificationService;

    public void doPayment(String token, PaymentDto paymentDto) {
        String parsedUserId = parseClaims.getClaims(token);
        Order order = orderRepository.findByOrderId(paymentDto.getOrderId()).orElse(null);
        if(order == null || !order.getUserId().equals(parsedUserId)) {
            kafkaTemplate.send("invalidRequest", notificationService.sendNotification(parsedUserId,"Invalid Order Id or User Id"));
            throw new IllegalPaymentRequestException("Invalid Order Id or User Id");
        } else if(order.getTotalAmount() == null) {
            kafkaTemplate.send("unconfirmedOrder", notificationService.sendNotification(parsedUserId,"Your order has not confirmed yet"));
            throw new IllegalPaymentRequestException("Your order has not confirmed yet");
        } else if (order.getTotalAmount() > paymentDto.getAmount()) {
            kafkaTemplate.send("insufficientAmount", notificationService.sendNotification(
                    parsedUserId,"Insufficient amount in order. Please add more " + (order.getTotalAmount() - paymentDto.getAmount()) + " amount"));
            throw new IllegalPaymentRequestException("Insufficient amount in order. Please add more " + (order.getTotalAmount() - paymentDto.getAmount()) + " amount");
        } else if(order.getStatus().equals("IN_SHIPMENT")) {
            kafkaTemplate.send("alreadyPaid", notificationService.sendNotification(parsedUserId,"The payment for this order " + order.getOrderId().toString() + "has already done."));
            throw new IllegalPaymentRequestException("Your order has not confirmed yet");
        }
        String paymentMethod = paymentDto.getPaymentMethod();
        if(paymentMethod == null || paymentMethod.isEmpty()) {
            kafkaTemplate.send("missingPaymentMethod", notificationService.sendNotification(
                    parsedUserId,"Payment method is required for order " + order.getOrderId().toString()));
            throw new IllegalPaymentRequestException("Payment method is required");
        }
        paymentProcessor.setPaymentStrategy(strategyCreationFactory.getPaymentStrategy(paymentMethod));
        paymentProcessor.paymentProcess(paymentDto.getAmount());
        Payment payment = new Payment();
        payment.setOrderId(paymentDto.getOrderId());
        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setUserId(parsedUserId);
        order.setStatus("IN_SHIPMENT");
        paymentRepository.save(payment);
        orderRepository.save(order);
        kafkaTemplate.send("paymentSuccess", notificationService.sendNotification(
                parsedUserId,
                "Payment success for order "
                        + order.getOrderId().toString()
                        + " of amount "
                        + order.getTotalAmount().toString()
                        + " via " + paymentDto.getPaymentMethod()
        ));
    }
}
