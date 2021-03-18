package com.feecalculator.exception;

public class UnkownTransactionType extends RuntimeException {

    public UnkownTransactionType(String message) {
        super(message);
    }

}
