package com.feecalculator.plugins.serlvets;

import java.util.Currency;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.CreateTransaction;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;

public abstract class AbstractChargeTransactionServlet extends HttpServlet {

    protected Transaction createTransactionFromRequest(HttpServletRequest request) {
        return CreateTransaction.completed(Double.parseDouble(request.getParameter("inputAmount")), Currency.getInstance("EUR"))
                .setTransactionType(getTransactionType(request.getParameter("transactionType")))
                .build();
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
