package com.feecalculator.utils;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import com.feecalculator.domaincode.TransactionType;

public class PropertiesUtils {

    //supported Currencies and TransactionTypes can be changed at a centralized point

    public static List<Currency> initializeCurrencies() {
        ArrayList<Currency> supportedCurrencies = new ArrayList<>();
        supportedCurrencies.add(Currency.getInstance("EUR"));
        return supportedCurrencies;
    }

    public static List<TransactionType> initializeTransactionTypes() {
        ArrayList<TransactionType> supportedTransactionTypes = new ArrayList<>();
        supportedTransactionTypes.add(TransactionType.PAYPAL_PERSONAL);
        supportedTransactionTypes.add(TransactionType.PAYPAL_DONATION);
        supportedTransactionTypes.add(TransactionType.PAYPAL_SERVICE);
        supportedTransactionTypes.add(TransactionType.PAYPAL_SELLER_CONDITION_LOW_VOLUME);
        supportedTransactionTypes.add(TransactionType.PAYPAL_SELLER_CONDITION_MEDIUM_VOLUME);
        supportedTransactionTypes.add(TransactionType.PAYPAL_SELLER_CONDITION_HIGH_VOLUME);
        supportedTransactionTypes.add(TransactionType.PAYPAL_SELLER_CONDITION_VERY_HIGH_VOLUME);
        supportedTransactionTypes.add(TransactionType.PAYPAL_MICRO_PAYMENT);
        supportedTransactionTypes.add(TransactionType.VISA_WALLEE);
        supportedTransactionTypes.add(TransactionType.VISA_CONCARDIS);
        return supportedTransactionTypes;
    }

}
