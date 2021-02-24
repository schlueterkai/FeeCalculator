package com.feecalculator.applicationcode;

import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;

public class TansactionFeeCalculator {

    private final Transaction transaction;

    public TansactionFeeCalculator(Transaction transaction) {
        this.transaction = transaction;
    }

    private Amount calculateTransactionFee(Transaction transaction) {
        if (transaction.getType() == TransactionType.PAYPAL_PERSONAL_TRANSACTION) {
            double transactionfee = 0.00 * transaction.getAmount().getAmount() + 0.0;
            return new Amount(transactionfee, transaction.getAmount().getCurrency());
        }
        if (transaction.getType() == TransactionType.PAYPAL_DONATION) {
            double transactionfee = 0.015 * transaction.getAmount().getAmount() + 0.35;
            return new Amount(transactionfee, transaction.getAmount().getCurrency());
        }
        if (transaction.getType() == TransactionType.PAYPAL_SERVICE_PAYMENT) {
            double transactionfee = 0.0249 * transaction.getAmount().getAmount() + 0.35;
            return new Amount(transactionfee, transaction.getAmount().getCurrency());
        }
        if (transaction.getType() == TransactionType.PAYPAL_MICRO_PAYMENT) {
            double transactionfee = 0.1 * transaction.getAmount().getAmount() + 0.1;
            return new Amount(transactionfee, transaction.getAmount().getCurrency());
        }
        if (transaction.getType() == TransactionType.PAYPAL_SELLER_CONDITION_LOW_VOLUME) {
            double transactionfee = 0.0219 * transaction.getAmount().getAmount() + 0.35;
            return new Amount(transactionfee, transaction.getAmount().getCurrency());
        }
        if (transaction.getType() == TransactionType.PAYPAL_SELLER_CONDITION_MEDIUM_VOLUME) {
            double transactionfee = 0.0199 * transaction.getAmount().getAmount() + 0.35;
            return new Amount(transactionfee, transaction.getAmount().getCurrency());
        }
        if (transaction.getType() == TransactionType.PAYPAL_SELLER_CONDITION_HIGH_VOLUME) {
            double transactionfee = 0.0179 * transaction.getAmount().getAmount() + 0.35;
            return new Amount(transactionfee, transaction.getAmount().getCurrency());
        }
        if (transaction.getType() == TransactionType.PAYPAL_SELLER_CONDITION_VERY_HIGH_VOLUME) {
            double transactionfee = 0.0149 * transaction.getAmount().getAmount() + 0.35;
            return new Amount(transactionfee, transaction.getAmount().getCurrency());
        }
        return null;
    }
}
