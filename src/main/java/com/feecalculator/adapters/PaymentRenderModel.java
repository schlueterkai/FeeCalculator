package com.feecalculator.adapters;

import java.util.HashMap;
import java.util.Map;

import com.feecalculator.abstractioncode.CalculationUtils;
import com.feecalculator.domaincode.Payment;
import com.feecalculator.domaincode.TransactionType;

public class PaymentRenderModel {

    public static Map<String, String> renderPayment(Payment payment, TransactionType bestTransactionType) {
        HashMap<String, String> paymentModel = new HashMap<>();
        paymentModel.put("transactionFees", CalculationUtils.round(payment.getTransactionFees()
                .getValue(), 2) + " " + payment.getTransactionFees()
                .getCurrency());
        paymentModel.put("bestTransactionType", getTransactionTypeName(bestTransactionType));
        return paymentModel;
    }

    private static String getTransactionTypeName(TransactionType transactionType) {
        switch (transactionType) {
            case PAYPAL_PERSONAL:
                return "Paypal: Zahlung an Freunde und Familie";
            case PAYPAL_DONATION:
                return "Paypal: Spenden sammeln";
            case PAYPAL_SERVICE:
                return "Paypal: Waren oder Dienstleistungen bezahlen";
            case PAYPAL_SELLER_CONDITION_LOW_VOLUME:
                return "Paypal: Zahlung mit Händerkonditionen zwischen 2000,01 und 5000,00€";
            case PAYPAL_SELLER_CONDITION_MEDIUM_VOLUME:
                return "Paypal: Zahlung mit Händerkonditionen zwischen 5000,01 und 25000,00€";
            case PAYPAL_SELLER_CONDITION_HIGH_VOLUME:
                return "Paypal: Zahlung mit Händerkonditionen zwischen 25000,01 und 100000,00€";
            case PAYPAL_SELLER_CONDITION_VERY_HIGH_VOLUME:
                return "Paypal: Zahlung mit Händerkonditionen mehr als 100000,01€";
            case PAYPAL_MICRO_PAYMENT:
                return "Paypal: Mikrozahlung";
            case VISA_WALLEE:
                return "Visa mit Concardis-Konditionen";
            case VISA_CONCARDIS:
                return "Visa mit Wallee-Konditionen";
            default:
                return "Es ist ein Fehler aufgetreten. Bitte versuchen Sie es erneut oder kontaktieren Sie den Webseiteninhaber";
        }
    }
}
