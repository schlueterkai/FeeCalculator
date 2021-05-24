package com.feecalculator.applicationcode;

import java.util.Currency;
import java.util.List;

import com.feecalculator.abstractioncode.CalculationUtils;
import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Payment;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;
import com.feecalculator.exception.NotSupportedCurrencyException;
import com.feecalculator.exception.NotSupportedTransactionTypeException;

public class CalculateTransactionFee implements IChargeTransaction, IChargePayment{

    private final List<TransactionType> supportedTransactionTypes;
    private final List<Currency> supportedCurrencies;

    public CalculateTransactionFee(List<TransactionType> transactionTypes, List<Currency> supportedCurrencies) {
        this.supportedTransactionTypes = transactionTypes;
        this.supportedCurrencies = supportedCurrencies;
    }

    /*
        Calculates the transaction fees for a payment (list of transaction). Only the fees of the transaction with the passed currency are considered. For every transaction the same transaction
        type is used.
     */
    public Amount forPayment(Payment payment, Currency currency, TransactionType transactionType) {
        //TODO: refactor the method
        List<Transaction> transactions = payment.getTransactions();
        double transactionFees = 0;
        for (Currency supportedCurrency : supportedCurrencies) {
            if (supportedCurrency.equals(currency)) {
                for (TransactionType supportedTransactionType : supportedTransactionTypes) {
                    if (supportedTransactionType == transactionType) {
                        for (Transaction transaction : transactions) {
                            if (transaction.getTransactionVolume()
                                    .getCurrency() == currency) {
                                transactionFees = transactionFees + calculateFeeWith(transaction.getTransactionVolume(), transactionType);
                            }
                        }
                        return new Amount(transactionFees, currency);
                    }
                }
                throw new NotSupportedTransactionTypeException("It is not supported by the system to calculate the transaction fee for the transaction type  " + transactionType + ".");
            }
        }
        throw new NotSupportedCurrencyException("\"It is not supported by the system to calculate the payment fee for the currency " + currency + ".");
    }

    /*
        Calculates the transaction fee for an given transaction based on the transaction volume and the transaction type.
     */
    public Amount forTransaction(Transaction transaction) {
        //Open-Closed Principle
        for (Currency supportedCurrency : supportedCurrencies) {
            if (transaction.getTransactionVolume()
                    .getCurrency()
                    .equals(supportedCurrency)) {
                //Open-Closed Principle
                for (TransactionType supportedTransactionType : supportedTransactionTypes) {
                    if (transaction.getTransactionType() == supportedTransactionType) {
                        return createAmountFor(transaction);
                    }
                }
                throw new NotSupportedTransactionTypeException(
                        "It is not supported by the system to calculate the transaction fee for the transaction type  " + transaction.getTransactionType() + ".");
            }
        }
        throw new NotSupportedCurrencyException("\"It is not supported by the system to calculate the transaction fee for the currency " + transaction.getTransactionVolume()
                .getCurrency() + ".");

    }

    /*
        Creates the amount for a transaction
     */
    private Amount createAmountFor(Transaction transaction) {
        return new Amount(calculateFeeWith(transaction.getTransactionVolume(), transaction.getTransactionType()), transaction.getTransactionVolume()
                .getCurrency());
    }

    /*
        Calculates the transaction fee based on the formular: fixed fee + transaction volume * variable fee
     */
    private double calculateFeeWith(Amount transactionVolume, TransactionType transactionType) {
        return CalculationUtils.round(transactionType.getFixedFee()
                .getValue() + transactionVolume.getValue() * transactionType.getVariableFee(), 2);
    }

}
