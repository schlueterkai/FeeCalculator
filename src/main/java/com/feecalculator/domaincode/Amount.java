package com.feecalculator.domaincode;

import java.util.Currency;

public class Amount {

    private double amount;
    private Currency currency;

    public Amount(double amount, Currency currency) {
        //TODO: fragen, was der Lindner von Logik im Konstruktor hält; hier auf negativen Betrag ggf. prüfen
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public Currency getCurrency() {
        return currency;
    }
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

}
