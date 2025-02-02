package com.example.payment.strategy;

import org.springframework.stereotype.Component;

@Component
public class CreditCard implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println(amount + " is paid through Credit Card");
    }
}
