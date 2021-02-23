package com.feecalculator.domaincode;

import java.util.Currency;

public class Amount {

    private final double amount;
    private final Currency currency;

    public Amount(double amount, Currency currency) {
        //TODO: fragen, was der Lindner von Logik im Konstruktor hält; hier auf negativen Betrag ggf. prüfen
        this.amount = amount;
        this.currency = currency;
    }

}
