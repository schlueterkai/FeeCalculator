package com.feecalculator.exception;

public class NotSupportedTransactionTypeException extends RuntimeException {

    public NotSupportedTransactionTypeException(String message) {
        super(message);
    }

}
