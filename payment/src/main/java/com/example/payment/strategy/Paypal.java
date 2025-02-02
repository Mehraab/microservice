package com.example.payment.strategy;

import org.springframework.stereotype.Component;

@Component
public class Paypal implements PaymentStrategy {
    @Override
    public void pay(double amount) {

    }
}
