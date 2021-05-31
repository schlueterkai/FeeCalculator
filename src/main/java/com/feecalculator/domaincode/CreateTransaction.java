package com.feecalculator.domaincode;

import java.util.Currency;
import java.util.UUID;

public final class CreateTransaction {

    private UUID id;
    private Amount transactionVolume; //required
    private Amount transactionFee;
    private TransactionType transactionType;

    private CreateTransaction(Amount transactionVolume) {
        this.transactionVolume = transactionVolume;
    }

    public static CreateTransaction completed(Amount transactionVolume) {
        return new CreateTransaction(transactionVolume);
    }

    public static CreateTransaction completed(double transactionVolume, Currency currency) {
        return new CreateTransaction(new Amount(transactionVolume, currency));
    }

    public static CreateTransaction completed(double transactionVolume, String currency) {
        return new CreateTransaction(new Amount(transactionVolume, Currency.getInstance("currency")));
    }

    public CreateTransaction setTransactioFee(Amount transactionFee) {
        this.transactionFee = transactionFee;
        return this;
    }

    public CreateTransaction setTransactioFee(double transactionFee, String currency) {
        this.transactionFee = new Amount(transactionFee, Currency.getInstance(currency));
        return this;
    }

    public CreateTransaction setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public CreateTransaction setId(UUID id) {
        this.id = id;
        return this;
    }

    public CreateTransaction setId(String id) {
        this.id = UUID.fromString(id);
        return this;
    }

    public Transaction build() {
        return new Transaction(this.id, this.transactionVolume, this.transactionVolume, this.transactionType);
    }
}
