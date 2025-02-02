package com.example.payment.exception;

public class IllegalPaymentRequestException extends RuntimeException{
    public IllegalPaymentRequestException(String message) {
        super(message);
    }
}
