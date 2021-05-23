package com.feecalculator.applicationcode;

import java.util.Currency;
import java.util.List;

import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;
import com.feecalculator.exception.NotSupportedCurrencyException;

public class CalculateReverseFee {

    private final List<TransactionType> supportedTransactionTypes;
    private final List<Currency> supportedCurrencies;

    public CalculateReverseFee(List<TransactionType> supportedTransactionTypes, List<Currency> supportedCurrencies) {
        this.supportedTransactionTypes = supportedTransactionTypes;
        this.supportedCurrencies = supportedCurrencies;
    }

    /*
        Calculates the new amount of a transaction volume based on the amount that the seller wants to get plus the transaction fees of the platform
     */
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
                //DRY: similar coding to CalculateTransactionFee. Only exception message is different
                throw new NotSupportedCurrencyException("It is not supported by the system to calculate the reverse fee for the transaction type" + transaction.getTransactionType() + ".");
            }
        }
        throw new NotSupportedCurrencyException("It is not supported by the system to calculate the reverse for the currency " + transaction.getTransactionVolume()
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
        Calcuate the new Fee with the formular: (amount + transactionfee) + (amount + transactionfee) * transactionrate
     */
    private double calculateFeeWith(Amount transactionVolume, TransactionType transactionType) {
        return 0;
    }

}
