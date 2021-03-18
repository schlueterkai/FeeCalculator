package com.feecalculator.domaincode;

import java.util.List;
import java.util.UUID;

public class Payment {

    private UUID id;
    private List<Transaction> transactions;
    private Amount transactionFees;

    //TODO: method for possible transaction types
    //Transaktionsvolumen
    public Payment(UUID id, List<Transaction> transactions, Amount transactionFees) {
        this.id = id;
        this.transactions = transactions;
        this.transactionFees = transactionFees;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Amount getTransactionFees() {
        return transactionFees;
    }

    public void setTransactionFees(Amount transactionFees) {
        this.transactionFees = transactionFees;
    }

}
