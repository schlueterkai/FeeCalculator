package com.feecalculator.domaincode;

import java.util.Currency;

public enum TransactionType {

    PAYPAL_PERSONAL_TRANSACTION(0.0, new Amount(0, Currency.getInstance("EUR"))),
    PAYPAL_DONATION(1.5, new Amount(0.35, Currency.getInstance("EUR"))),
    PAYPAL_SERVICE_PAYMENT(2.49, new Amount(0.35, Currency.getInstance("EUR")));

    private final double transactionFeeRate;
    private final Amount transactionAmount;
    //TODO: Lukas wegen Koharenz fragen

    private TransactionType(double transactionFeeRate, Amount transactionAmount) {
        this.transactionFeeRate = transactionFeeRate;
        this.transactionAmount = transactionAmount;
    }
}
