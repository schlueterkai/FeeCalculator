package com.feecalculator.exception;

public class EmptyPaymentException extends RuntimeException {

    public EmptyPaymentException(String message) {
        super(message);
    }

}
