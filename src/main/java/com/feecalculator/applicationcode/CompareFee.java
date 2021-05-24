package com.feecalculator.applicationcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Payment;
import com.feecalculator.domaincode.TransactionType;
import com.feecalculator.exception.EmptyPaymentException;

public class CompareFee {

    private CalculateTransactionFee calculateTransactionFee;

    //TODO: add interface for class compareable o.ä.
    //TODO: dependency injection by replacing CalculateTransactionFee with Interface
    public CompareFee(CalculateTransactionFee calculateTransactionFee) {
        this.calculateTransactionFee = calculateTransactionFee;
    }

    //TODO: implement logic and add interface

    /*
        Returns the transaction types with the best conditions for a payment. Only supported for payments and transactions that uses euros.
     */
    public TransactionType findBestConditionForPayments(Payment payment) {
        List<TransactionType> validTransactionTypes = getPossibleTransactionTypes(payment);
        List<Amount> calculatedAmounts = new ArrayList<>();

        if (payment.getTransactions()
                .size() > 0) {
            for (TransactionType transactionType : validTransactionTypes) {
                calculatedAmounts.add(calculateTransactionFee.forPayment(payment, Currency.getInstance("EUR"), transactionType));
            }

            //TODO: refactor and extract to own method
            List<Double> paymentTransactionFees = new ArrayList<>();
            for (Amount amount : calculatedAmounts) {
                paymentTransactionFees.add(amount.getValue());
            }
            int indexOfLowestFee = paymentTransactionFees.indexOf(Collections.min(paymentTransactionFees));

            return validTransactionTypes.get(indexOfLowestFee);
        } else {
            throw new EmptyPaymentException("The passed payment had no transaction included. Therefore it was not possible to calculate the best condition.");
        }
    }

    /*
        Returns a list of valid transaction types that can could be used for a payment. Transaction type paypal personal and paypal donation is never added to this list. Currently only supported
        for euros.
     */
    private List<TransactionType> getPossibleTransactionTypes(Payment payment) {
        Amount paymentVolume = payment.getPaymentVolume(Currency.getInstance("EUR"));
        List<TransactionType> validTransactionTypes = new ArrayList<>();
        for (TransactionType type : TransactionType.values()) {
            if (type != TransactionType.PAYPAL_PERSONAL && type != TransactionType.PAYPAL_DONATION && paymentVolume.getValue() > type.getMinimalTransactionVolume()) {
                validTransactionTypes.add(type);
            }
        }
        return validTransactionTypes;
    }

    public CalculateTransactionFee getCalculateTransactionFee() {
        return calculateTransactionFee;
    }

    public void setCalculateTransactionFee(CalculateTransactionFee calculateTransactionFee) {
        this.calculateTransactionFee = calculateTransactionFee;
    }
}
