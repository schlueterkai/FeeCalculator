package com.feecalculator.adapters;

import java.util.HashMap;
import java.util.Map;

import com.feecalculator.abstractioncode.CalculationUtils;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;

public class TransactionRenderModel {

    public static Map<String, String> renderTransaction(Transaction transaction) {
        HashMap<String, String> transactionModel = new HashMap<>();
        transactionModel.put("transactionVolume", transaction.getTransactionVolume()
                .getValue() + " " + transaction.getTransactionVolume()
                .getCurrency());
        transactionModel.put("transactionFee", transaction.getTransactionFee()
                .getValue() + " " + transaction.getTransactionVolume()
                .getCurrency());
        transactionModel.put("transactionType", getTransactionTypeName(transaction.getTransactionType()));
        transactionModel.put("proportion", calculateFeeProportion(transaction.getTransactionVolume()
                .getValue(), transaction.getTransactionFee()
                .getValue()) + " %");
        return transactionModel;
    }

    private static double calculateFeeProportion(double transactionVolume, double transactionFee) {
        if (transactionVolume > transactionFee) {
            return CalculationUtils.round(transactionFee / transactionVolume * 100 , 2) ;
        } else {
            double difference = transactionFee - transactionVolume;
            return CalculationUtils.round(difference / transactionFee * 100, 2) ;
        }
    }

    //TODO: abstract base render model for DRY
    private static String getTransactionTypeName(TransactionType transactionType) {
        switch (transactionType) {
            case PAYPAL_PERSONAL:
                return "Paypal: Zahlung an Freunde und Familie";
            case PAYPAL_DONATION:
                return "Paypal: Spenden sammeln";
            case PAYPAL_SERVICE:
                return "Paypal: Waren oder Dienstleistungen bezahlen";
            case PAYPAL_SELLER_CONDITION_LOW_VOLUME:
                return "Paypal: Zahlung mit Händerkonditionen zwischen 2000,01 und 5000,00 EUR";
            case PAYPAL_SELLER_CONDITION_MEDIUM_VOLUME:
                return "Paypal: Zahlung mit Händerkonditionen zwischen 5000,01 und 25000,00 EUR";
            case PAYPAL_SELLER_CONDITION_HIGH_VOLUME:
                return "Paypal: Zahlung mit Händerkonditionen zwischen 25000,01 und 100000,00 EUR";
            case PAYPAL_SELLER_CONDITION_VERY_HIGH_VOLUME:
                return "Paypal: Zahlung mit Händerkonditionen mehr als 100000,01 EUR";
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
