package com.feecalculator.plugins.serlvets;

import javax.servlet.http.HttpServlet;

import com.feecalculator.domaincode.TransactionType;

public abstract class AbstractChargeTransactionServlet extends HttpServlet {

    protected TransactionType getTransactionType(String radiobuttonValue) {
        switch (radiobuttonValue) {
            case "paypalFriends":
                return TransactionType.PAYPAL_PERSONAL;
            case "paypalServices":
                return TransactionType.PAYPAL_SERVICE;
            case "paypalDonation":
                return TransactionType.PAYPAL_DONATION;
            case "paypalMicro":
                return TransactionType.PAYPAL_MICRO_PAYMENT;
            case "paypalLowSellerCondition":
                return TransactionType.PAYPAL_SELLER_CONDITION_LOW_VOLUME;
            case "paypalMediumSellerCondition":
                return TransactionType.PAYPAL_SELLER_CONDITION_MEDIUM_VOLUME;
            case "paypalHighSellerCondition":
                return TransactionType.PAYPAL_SELLER_CONDITION_HIGH_VOLUME;
            case "paypalVeryHighSellerCondition":
                return TransactionType.PAYPAL_SELLER_CONDITION_VERY_HIGH_VOLUME;
            case "visaConcardis":
                return TransactionType.VISA_CONCARDIS;
            case "visaWallee":
                return TransactionType.VISA_WALLEE;
            default:
                return null;
        }
    }

}
