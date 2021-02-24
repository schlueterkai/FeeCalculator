package com.feecalculator.applicationcode;

import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Transaction;

public class TansactionFeeCalculator {

    private final Transaction transaction;

    public TansactionFeeCalculator(Transaction transaction) {
        this.transaction = transaction;
    }

    private Amount calculateTransactionFee(){
        return null;
    }
}
