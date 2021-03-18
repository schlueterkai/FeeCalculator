package com.feecalculator.domaincode;

import java.util.Currency;
import java.util.Objects;

public final class Amount {

    private final double value;
    private final Currency currency;

    public Amount(double amount, Currency currency) {
        if(amount < 0 ) {
            throw new IllegalArgumentException("Cannot create negative amount: " + amount);
        }
        this.value = amount;
        this.currency = currency;
    }

    public double getValue() {
        return this.value;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Amount)) {
            return false;
        }
        Amount a = (Amount) obj;
        return this.value == a.value && this.currency == a.currency;
    }
}
