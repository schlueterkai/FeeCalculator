package com.feecalculator.applicationcode;

import java.util.Currency;

import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;
import com.feecalculator.exception.NotSupportedCurrencyException;
import com.feecalculator.exception.UnkownTransactionType;

public class TansactionFeeCalculator {

    //Sichbarkeitsstufe 2: Domain Driven Design

    public static Amount calculateFeeFor(Transaction transaction) {

        if (transaction.getTransactionVolume()
                .getCurrency() == Currency.getInstance("EUR")) {
            if (transaction.getTransactionType() == TransactionType.PAYPAL_PERSONAL_TRANSACTION) {
                double transactionFee = transaction.getTransactionType()
                        .getFixedFee()
                        .getValue() + transaction.getTransactionVolume()
                        .getValue() * transaction.getTransactionType()
                        .getVariableFee();
                return new Amount(transactionFee, Currency.getInstance("EUR"));
            }
            if (transaction.getTransactionType() == TransactionType.PAYPAL_DONATION) {
                double transactionFee = transaction.getTransactionType()
                        .getFixedFee()
                        .getValue() + transaction.getTransactionVolume()
                        .getValue() * transaction.getTransactionType()
                        .getVariableFee();
                return new Amount(transactionFee, Currency.getInstance("EUR"));
            }
            if (transaction.getTransactionType() == TransactionType.PAYPAL_MICRO_PAYMENT) {
                double transactionFee = transaction.getTransactionType()
                        .getFixedFee()
                        .getValue() + transaction.getTransactionVolume()
                        .getValue() * transaction.getTransactionType()
                        .getVariableFee();
                return new Amount(transactionFee, Currency.getInstance("EUR"));
            }
            if (transaction.getTransactionType() == TransactionType.PAYPAL_MICRO_PAYMENT) {
                double transactionFee = transaction.getTransactionType()
                        .getFixedFee()
                        .getValue() + transaction.getTransactionVolume()
                        .getValue() * transaction.getTransactionType()
                        .getVariableFee();
                return new Amount(transactionFee, Currency.getInstance("EUR"));
            }
            if (transaction.getTransactionType() == TransactionType.PAYPAL_MICRO_PAYMENT) {
                double transactionFee = transaction.getTransactionType()
                        .getFixedFee()
                        .getValue() + transaction.getTransactionVolume()
                        .getValue() * transaction.getTransactionType()
                        .getVariableFee();
                return new Amount(transactionFee, Currency.getInstance("EUR"));
            }
            if (transaction.getTransactionType() == TransactionType.PAYPAL_MICRO_PAYMENT) {
                double transactionFee = transaction.getTransactionType()
                        .getFixedFee()
                        .getValue() + transaction.getTransactionVolume()
                        .getValue() * transaction.getTransactionType()
                        .getVariableFee();
                return new Amount(transactionFee, Currency.getInstance("EUR"));
            }
            if (transaction.getTransactionType() == TransactionType.PAYPAL_MICRO_PAYMENT) {
                double transactionFee = transaction.getTransactionType()
                        .getFixedFee()
                        .getValue() + transaction.getTransactionVolume()
                        .getValue() * transaction.getTransactionType()
                        .getVariableFee();
                return new Amount(transactionFee, Currency.getInstance("EUR"));
            }
            if (transaction.getTransactionType() == TransactionType.PAYPAL_SELLER_CONDITION_LOW_VOLUME) {
                double transactionFee = transaction.getTransactionType()
                        .getFixedFee()
                        .getValue() + transaction.getTransactionVolume()
                        .getValue() * transaction.getTransactionType()
                        .getVariableFee();
                return new Amount(transactionFee, Currency.getInstance("EUR"));
            }
            if (transaction.getTransactionType() == TransactionType.PAYPAL_SELLER_CONDITION_MEDIUM_VOLUME) {
                double transactionFee = transaction.getTransactionType()
                        .getFixedFee()
                        .getValue() + transaction.getTransactionVolume()
                        .getValue() * transaction.getTransactionType()
                        .getVariableFee();
                return new Amount(transactionFee, Currency.getInstance("EUR"));
            }
            if (transaction.getTransactionType() == TransactionType.PAYPAL_SELLER_CONDITION_HIGH_VOLUME) {
                double transactionFee = transaction.getTransactionType()
                        .getFixedFee()
                        .getValue() + transaction.getTransactionVolume()
                        .getValue() * transaction.getTransactionType()
                        .getVariableFee();
                return new Amount(transactionFee, Currency.getInstance("EUR"));
            }
            if (transaction.getTransactionType() == TransactionType.PAYPAL_SELLER_CONDITION_VERY_HIGH_VOLUME) {
                double transactionFee = transaction.getTransactionType()
                        .getFixedFee()
                        .getValue() + transaction.getTransactionVolume()
                        .getValue() * transaction.getTransactionType()
                        .getVariableFee();
                return new Amount(transactionFee, Currency.getInstance("EUR"));
            }
            if (transaction.getTransactionType() == TransactionType.VISA_CONCARDIS) {
                double transactionFee = transaction.getTransactionType()
                        .getFixedFee()
                        .getValue() + transaction.getTransactionVolume()
                        .getValue() * transaction.getTransactionType()
                        .getVariableFee();
                return new Amount(transactionFee, Currency.getInstance("EUR"));
            }
            if (transaction.getTransactionType() == TransactionType.VISA_WALLEE) {
                double transactionFee = transaction.getTransactionType()
                        .getFixedFee()
                        .getValue() + transaction.getTransactionVolume()
                        .getValue() * transaction.getTransactionType()
                        .getVariableFee();
                return new Amount(transactionFee, Currency.getInstance("EUR"));
            }
            throw new UnkownTransactionType("The transaction type " + transaction.getTransactionType() + " is unkown to the system.");
        } else {
            throw new NotSupportedCurrencyException("The Currency " + transaction.getTransactionVolume()
                    .getCurrency() + " is not supported.");
        }
    }

}
