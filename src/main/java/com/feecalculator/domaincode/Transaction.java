package com.feecalculator.domaincode;

public class Transaction {

    private Amount amount;
    private Amount transactionFee;
    private TransactionType type;

    public Transaction(Amount amount, TransactionType type, Amount transactionFee) {
        this.amount = amount;
        this.type = type;
        this.transactionFee = transactionFee;
    }

    public Amount getAmount() {
        return amount;
    }
    public void setAmount(Amount amount) {
        this.amount = amount;
    }
    public Amount getTransactionFee() {
        return transactionFee;
    }
    public void setTransactionFee(Amount transactionFee) {
        this.transactionFee = transactionFee;
    }
    public TransactionType getType() {
        return type;
    }
    public void setType(TransactionType type) {
        this.type = type;
    }

}
