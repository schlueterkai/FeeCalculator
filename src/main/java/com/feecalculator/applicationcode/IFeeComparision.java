package com.feecalculator.applicationcode;

import com.feecalculator.domaincode.Payment;
import com.feecalculator.domaincode.TransactionType;

public interface IFeeComparision {

    public TransactionType findBestTransactionTypeForPayment(Payment payment);

}
