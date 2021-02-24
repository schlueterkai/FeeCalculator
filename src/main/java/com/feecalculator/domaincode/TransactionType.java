package com.feecalculator.domaincode;

import java.util.Currency;

public enum TransactionType {

    PAYPAL_PERSONAL_TRANSACTION(),
    PAYPAL_DONATION(),
    PAYPAL_SERVICE_PAYMENT(),
    PAYPAL_SELLER_CONDITION_LOW_VOLUME(),
    PAYPAL_SELLER_CONDITION_MEDIUM_VOLUME(),
    PAYPAL_SELLER_CONDITION_HIGH_VOLUME(),
    PAYPAL_SELLER_CONDITION_VERY_HIGH_VOLUME(),
    PAYPAL_MICRO_PAYMENT();

}
