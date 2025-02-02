package com.example.payment.process;

import com.example.payment.strategy.PaymentStrategy;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    public void paymentProcess(double amount) {
        paymentStrategy.pay(amount);
    }
}
