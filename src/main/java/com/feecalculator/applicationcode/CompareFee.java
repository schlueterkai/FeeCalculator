package com.feecalculator.applicationcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Payment;
import com.feecalculator.domaincode.TransactionType;
import com.feecalculator.exception.EmptyPaymentException;

public class CompareFee implements IFeeComparision {

    private IChargePayment calculateTransactionFee;

    public CompareFee(IChargePayment calculateTransactionFee) {
        this.calculateTransactionFee = calculateTransactionFee;
    }

    /*
        Returns the transaction types with the best conditions for a payment. Only supported for payments and transactions that uses euros.
     */
    public TransactionType findBestTransactionTypeForPayment(Payment payment) throws EmptyPaymentException {
        List<TransactionType> validTransactionTypes = getPossibleTransactionTypes(payment);
        List<Amount> calculatedTransactionFees = new ArrayList<>();

        if (!payment.getTransactions()
                .isEmpty()) {
            for (TransactionType transactionType : validTransactionTypes) {
                calculatedTransactionFees.add(calculateTransactionFee.forPayment(payment, Currency.getInstance("EUR"), transactionType));
            }

            int indexOfLowestFee = getIndexOfLowestTransactionFee(calculatedTransactionFees);

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

    public int getIndexOfLowestTransactionFee(List<Amount> calculatedTransactionFees) {
        List<Double> paymentTransactionFees = new ArrayList<>();
        for (Amount amount : calculatedTransactionFees) {
            paymentTransactionFees.add(amount.getValue());
        }
        int indexOfLowestFee = paymentTransactionFees.indexOf(Collections.min(paymentTransactionFees));
        return indexOfLowestFee;
    }

    public IChargePayment getCalculateTransactionFee() {
        return calculateTransactionFee;
    }

    public void setCalculateTransactionFee(IChargePayment calculateTransactionFee) {
        this.calculateTransactionFee = calculateTransactionFee;
    }
}
