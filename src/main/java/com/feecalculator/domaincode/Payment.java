package com.feecalculator.domaincode;

import java.util.List;

public class Payment {

    private List<Transaction> transactions;
    private Amount transactionFees;

    public Payment(List<Transaction> transactions, Amount transactionFees) {
        this.transactions = transactions;
        this.transactionFees = transactionFees;
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
