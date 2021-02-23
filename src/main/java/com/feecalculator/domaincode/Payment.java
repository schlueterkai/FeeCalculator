package com.feecalculator.domaincode;

import java.util.List;

public class Payment {

    private final List<Transaction> transactions;
    private final Amount transactionFees;

    public Payment(List<Transaction> transactions, Amount transactionFees) {
        this.transactions = transactions;
        this.transactionFees = transactionFees;
    }
}
