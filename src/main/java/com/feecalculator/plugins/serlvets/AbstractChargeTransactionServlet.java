package com.feecalculator.plugins.serlvets;

import java.util.Currency;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;

public abstract class AbstractChargeTransactionServlet extends HttpServlet {

    protected Transaction createTransactionFromRequest(HttpServletRequest request) {
        TransactionType transactionType = getTransactionType(request.getParameter("transactionType"));
        Double transactionVolume = Double.parseDouble(request.getParameter("inputAmount"));
        Amount transactionAmount = new Amount(transactionVolume, Currency.getInstance("EUR"));
        return new Transaction(transactionAmount, transactionType);
    }

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
