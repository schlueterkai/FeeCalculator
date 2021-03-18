package com.feecalculator.exception;

public class NotSupportedCurrencyException extends RuntimeException {

    public NotSupportedCurrencyException(String message) {
        super(message);
    }
}
