package com.feecalculator.domaincode;

public enum TransactionType {

    PAYPAL_PERSONAL(ConfigurationProperties.FRIENDS_FEE, 0, 0),
    PAYPAL_DONATION(ConfigurationProperties.SELLER_FEE, 0.015, 0),
    PAYPAL_SERVICE(ConfigurationProperties.SELLER_FEE, 0.0249, 0),
    PAYPAL_SELLER_CONDITION_LOW_VOLUME(ConfigurationProperties.SELLER_FEE, 0.0219, 2001),
    PAYPAL_SELLER_CONDITION_MEDIUM_VOLUME(ConfigurationProperties.SELLER_FEE, 0.0199, 5001),
    PAYPAL_SELLER_CONDITION_HIGH_VOLUME(ConfigurationProperties.SELLER_FEE, 0.0179, 25001),
    PAYPAL_SELLER_CONDITION_VERY_HIGH_VOLUME(ConfigurationProperties.SELLER_FEE, 0.0149, 100001),
    PAYPAL_MICRO_PAYMENT(ConfigurationProperties.MICROPAYMENT_FEE, 0.1, 0),
    VISA_WALLEE(ConfigurationProperties.WALLEE_FEE, 0.014, 0),
    VISA_CONCARDIS(ConfigurationProperties.CONCARDIS_FEE, 0.0099, 0);

    private Amount fixedFee;
    private double variableFee;
    private double minimalTransactionVolume;

    private TransactionType(Amount fixedFee, double variableFee, double minimalTransactionVolume) {
        this.fixedFee = fixedFee;
        this.variableFee = variableFee;
        this.minimalTransactionVolume = minimalTransactionVolume;
    }

    public Amount getFixedFee() {
        return fixedFee;
    }

    public double getVariableFee() {
        return variableFee;
    }

    public double getMinimalTransactionVolume  () {
        return  minimalTransactionVolume;
    }
}
