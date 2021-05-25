package com.feecalculator.applicationcode;

import com.feecalculator.domaincode.Payment;
import com.feecalculator.domaincode.TransactionType;
import com.feecalculator.exception.EmptyPaymentException;

public interface IFeeComparision {

    public TransactionType findBestTransactionTypeForPayment(Payment payment) throws EmptyPaymentException;

}
