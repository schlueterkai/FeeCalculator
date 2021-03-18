package com.feecalculator.applicationcode;

import java.util.Currency;

import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;
import com.feecalculator.exception.NotSupportedCurrencyException;
import com.feecalculator.exception.UnkownTransactionType;

public class TansactionFeeCalculator {

    //Sichbarkeitsstufe 2: Domain Driven Design

    public Amount calculateFeeFor(Transaction transaction) {

        if (transaction.getTransactionVolume()
                .getCurrency() == Currency.getInstance("EUR")) {
            if (transaction.getTransactionType() == TransactionType.PAYPAL_PERSONAL_TRANSACTION) {
                return createAmountFor(transaction);
            }
            if (transaction.getTransactionType() == TransactionType.PAYPAL_DONATION) {
                return createAmountFor(transaction);
            }
            if (transaction.getTransactionType() == TransactionType.PAYPAL_SELLER_CONDITION_LOW_VOLUME) {
                return createAmountFor(transaction);
            }
            if (transaction.getTransactionType() == TransactionType.PAYPAL_SELLER_CONDITION_MEDIUM_VOLUME) {
                return createAmountFor(transaction);
            }
            if (transaction.getTransactionType() == TransactionType.PAYPAL_SELLER_CONDITION_HIGH_VOLUME) {
                return createAmountFor(transaction);
            }
            if (transaction.getTransactionType() == TransactionType.PAYPAL_SELLER_CONDITION_VERY_HIGH_VOLUME) {
                return createAmountFor(transaction);
            }
            if (transaction.getTransactionType() == TransactionType.VISA_CONCARDIS) {
                return createAmountFor(transaction);
            }
            if (transaction.getTransactionType() == TransactionType.VISA_WALLEE) {
                return createAmountFor(transaction);
            }
            throw new UnkownTransactionType("The transaction type " + transaction.getTransactionType() + " is unkown to the system.");
        } else {
            throw new NotSupportedCurrencyException("The Currency " + transaction.getTransactionVolume()
                    .getCurrency() + " is not supported.");
        }
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
