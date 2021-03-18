package com.feecalculator.domaincode;

import java.util.Currency;

public class ConfigurationProperties {

    public static final Amount FRIENDS_FEE = new Amount(0, Currency.getInstance("EUR"));
    public static final Amount SELLER_FEE = new Amount(0.35, Currency.getInstance("EUR"));
    public static final Amount MICROPAYMENT_FEE = new Amount(0.1, Currency.getInstance("EUR"));
    public static final Amount WALLEE_FEE = new Amount(0.1, Currency.getInstance("EUR"));
    public static final Amount CONCARDIS_FEE = new Amount(0.1, Currency.getInstance("EUR"));

}
