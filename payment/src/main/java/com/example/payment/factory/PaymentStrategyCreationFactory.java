package com.example.payment.factory;

import com.example.payment.strategy.CreditCard;
import com.example.payment.strategy.DebitCard;
import com.example.payment.strategy.PaymentStrategy;
import com.example.payment.strategy.Paypal;
import org.springframework.stereotype.Component;

@Component
public class PaymentStrategyCreationFactory {
    public PaymentStrategy getPaymentStrategy(String paymentMethod) {
        switch (paymentMethod) {
            case "credit_card":
                return new CreditCard();
            case "paypal":
                return new Paypal();
            case "debit_card":
                return new DebitCard();
            default:
                throw new  IllegalArgumentException("Invalid payment method: ");
        }
    }
}
