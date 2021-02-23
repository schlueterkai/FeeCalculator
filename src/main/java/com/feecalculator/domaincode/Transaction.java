package com.feecalculator.domaincode;

public class Transaction {

    private final Amount amount;
    private final TransactionType type;

    public Transaction(Amount amount, TransactionType type) {
        this.amount = amount;
        this.type = type;
    }
}
