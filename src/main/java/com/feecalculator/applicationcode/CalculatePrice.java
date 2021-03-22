package com.feecalculator.applicationcode;

import java.util.Currency;
import java.util.List;

import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;

public class CalculatePrice {

    private final List<TransactionType> supportedTransactionTypes;
    private final List<Currency> supportedCurrencies;

    public CalculatePrice(List<TransactionType> supportedTransactionTypes, List<Currency> supportedCurrencies) {
        this.supportedTransactionTypes = supportedTransactionTypes;
        this.supportedCurrencies = supportedCurrencies;
    }

    //Restbetrag: (Restbetrag + Transaktionsgebühr) + (Restbetrag + Transaktionsgebühr) * Transaktionsrate
}
