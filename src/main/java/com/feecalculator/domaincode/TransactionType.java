package com.feecalculator.domaincode;

public enum TransactionType {

    PAYPAL_PERSONAL_TRANSACTION(ConfigurationProperties.FRIENDS_FEE, 0),
    PAYPAL_DONATION(ConfigurationProperties.SELLER_FEE, 0.015),
    PAYPAL_SELLER_CONDITION_LOW_VOLUME(ConfigurationProperties.SELLER_FEE, 0.0219),
    PAYPAL_SELLER_CONDITION_MEDIUM_VOLUME(ConfigurationProperties.SELLER_FEE, 0.0199),
    PAYPAL_SELLER_CONDITION_HIGH_VOLUME(ConfigurationProperties.SELLER_FEE, 0.0179),
    PAYPAL_SELLER_CONDITION_VERY_HIGH_VOLUME(ConfigurationProperties.SELLER_FEE, 0.0149),
    PAYPAL_MICRO_PAYMENT(ConfigurationProperties.MICROPAYMENT_FEE, 0.1),
    VISA_WALLEE(ConfigurationProperties.WALLEE_FEE, 0.014),
    VISA_CONCARDIS(ConfigurationProperties.CONCARDIS_FEE, 0.0099);

    private Amount fixedFee;
    private double variableFee;

    private TransactionType(Amount fixedFee, double variableFee) {
        this.fixedFee = fixedFee;
        this.variableFee = variableFee;
    }

    public Amount getFixedFee() {
        return fixedFee;
    }

    public double getVariableFee() {
        return variableFee;
    }
}
