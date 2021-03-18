package com.feecalculator.domaincode;

import java.util.UUID;

public class Transaction implements IChargeable {

    private UUID id;
    private Amount transactionVolume;
    private Amount transactionFee;
    private TransactionType transactionType;

    public Transaction(UUID id, Amount transactionVolume, Amount transactionFee, TransactionType transactionType) {
        this.id = id;
        this.transactionVolume = transactionVolume;
        this.transactionFee = transactionFee;
        this.transactionType = transactionType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Amount getTransactionVolume() {
        return transactionVolume;
    }

    public void setTransactionVolume(Amount transactionVolume) {
        this.transactionVolume = transactionVolume;
    }

    public Amount getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(Amount transactionFee) {
        this.transactionFee = transactionFee;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
