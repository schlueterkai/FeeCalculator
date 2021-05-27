package com.feecalculator.applicationcode;

import java.util.Currency;
import java.util.List;

import com.feecalculator.abstractioncode.CalculationUtils;
import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;
import com.feecalculator.exception.NotSupportedCurrencyException;
import com.feecalculator.exception.NotSupportedTransactionTypeException;

public class CalculateReverseFee implements IChargeTransaction {

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
                throw new NotSupportedTransactionTypeException("It is not supported by the system to calculate the reverse fee for the transaction type" + transaction.getTransactionType() + ".");
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
        Calcuate the new Fee with approximation because there is no exact formular for the problem. Before returning the new value round the the second decimal number
     */
    private double calculateFeeWith(Amount transactionVolume, TransactionType transactionType) {
        double amount = transactionVolume.getValue();
        double transactionfee = transactionType.getFixedFee()
                .getValue();
        double transactionrate = transactionType.getVariableFee();
        double fee = 0;
        for (int i = 0; i < 10; i++) {
            fee = fee + (amount + transactionfee) * Math.pow(transactionrate, i);
        }
        return CalculationUtils.round(fee, 2);

    }

}
