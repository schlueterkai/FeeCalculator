package com.feecalculator.adapters;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.feecalculator.domaincode.Transaction;

public class TransactionRenderModel {

    public static Map<String, String> renderTransaction(Transaction transaction) {
         HashMap<String, String> transactionModel = new HashMap<>();
         transactionModel.put("transactionVolume", "");
         transactionModel.put("transactionFee", "");
         transactionModel.put("transactionType", "");
        return transactionModel;
    }

}
