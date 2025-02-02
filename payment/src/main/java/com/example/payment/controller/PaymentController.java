package com.example.payment.controller;

import com.example.payment.dto.PaymentDto;
import com.example.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @PostMapping("/create")
    public void doPayment(@RequestHeader("Authorization") String token, @RequestBody PaymentDto paymentDto) {
        paymentService.doPayment(token, paymentDto);
    }
}
