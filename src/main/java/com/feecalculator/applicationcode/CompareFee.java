package com.feecalculator.applicationcode;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Payment;
import com.feecalculator.domaincode.TransactionType;

public class CompareFee {

    private CalculateTransactionFee calculateTransactionFee;

    //TODO: add interface for class compareable o.ä.
    //TODO: dependency injection by replacing CalculateTransactionFee with Interface
    public CompareFee(CalculateTransactionFee calculateTransactionFee) {
        this.calculateTransactionFee = calculateTransactionFee;
    }

    //TODO: implement logic and add interface

    public TransactionType findBestConditionForPayments(Payment payment) {
        List<TransactionType> validTransactionTypes = getPossibleTransactionTypes(payment);

        for (TransactionType type : validTransactionTypes) {

        }

        //check for possible transactions -> where are limitations

        //calculate fee for each transaction of the payment

        return null;
    }

    /*
        Returns a list of valid transaction types that can could be used for a payment. Transaction type paypal personal is never added to this list. Currently only supported for euros.
     */
    private List<TransactionType> getPossibleTransactionTypes(Payment payment) {
        Amount paymentVolume = payment.getPaymentVolume(Currency.getInstance("EUR"));
        List<TransactionType> validTransactionTypes = new ArrayList<>();
        for (TransactionType type : TransactionType.values()) {
            if (type != TransactionType.PAYPAL_PERSONAL && paymentVolume.getValue() > type.getMinimalTransactionVolume()) {
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
