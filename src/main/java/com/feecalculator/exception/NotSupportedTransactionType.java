package com.feecalculator.exception;

public class NotSupportedTransactionType extends RuntimeException {

    public NotSupportedTransactionType(String message) {
        super(message);
    }

}
