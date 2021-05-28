package com.feecalculator.domaincode;

import java.util.Currency;
import java.util.List;
import java.util.UUID;

public class Payment {

    private UUID id;
    private List<Transaction> transactions; //required
    private Amount transactionFees;

    //uuid only needed for future data persistence
    public Payment(UUID id, List<Transaction> transactions, Amount transactionFees) {
        this.id = id;
        this.transactions = transactions;
        this.transactionFees = transactionFees;
    }

    public Payment(List<Transaction> transactions, Amount transactionFees) {
        this.transactions = transactions;
        this.transactionFees = transactionFees;
    }

    public Payment(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Amount getTransactionFees() {
        return transactionFees;
    }

    public void setTransactionFees(Amount transactionFees) {
        this.transactionFees = transactionFees;
    }

    /*
        Returns the volume of all transactions with the passed currency.
     */
    public Amount getPaymentVolume(Currency currency) {
        double volume = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionVolume()
                    .getCurrency()
                    .equals(currency)) {
                volume = volume + transaction.getTransactionVolume()
                        .getValue();
            }
        }
        return new Amount(volume, currency);
    }

}
