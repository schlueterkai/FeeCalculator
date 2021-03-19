package com.feecalculator.applicationcode;

import java.util.Currency;
import java.util.List;

import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;
import com.feecalculator.exception.NotSupportedCurrencyException;
import com.feecalculator.exception.NotSupportedTransactionType;

public class CalculateTransactionFee {

    //Sichbarkeitsstufe 2: Domain Driven Design
    private final List<TransactionType> supportedTransactionTypes;
    private final List<Currency> supportedCurrencies;
    //TODO: rewrite for Open Closed Principle

    public CalculateTransactionFee(List<TransactionType> transactionTypes, List<Currency> supportedCurrencies) {
        this.supportedTransactionTypes = transactionTypes;
        this.supportedCurrencies = supportedCurrencies;
    }

    public Amount forTransaction(Transaction transaction) {
        for (Currency supportedCurrency : supportedCurrencies) {
            if (transaction.getTransactionVolume()
                    .getCurrency()
                    .equals(supportedCurrency)) {
                for (TransactionType supportedTransactionType : supportedTransactionTypes) {
                    if (transaction.getTransactionType() == supportedTransactionType) {
                        return createAmountFor(transaction);
                    }
                }
                throw new NotSupportedTransactionType("The transaction type " + transaction.getTransactionType() + " is not supported by the system.");
            }
        }
        throw new NotSupportedCurrencyException("The Currency " + transaction.getTransactionVolume()
                .getCurrency() + " is not supported.");

    }

    private Amount createAmountFor(Transaction transaction) {
        return new Amount(calculateFeeWith(transaction.getTransactionVolume(), transaction.getTransactionType()), transaction.getTransactionVolume()
                .getCurrency());
    }

    private double calculateFeeWith(Amount transactionVolume, TransactionType transactionType) {
        return transactionType.getFixedFee()
                .getValue() + transactionVolume.getValue() * transactionType.getVariableFee();
    }

}
