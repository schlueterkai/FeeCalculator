package com.feecalculator.applicationcode;

import java.util.Currency;

import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Payment;
import com.feecalculator.domaincode.TransactionType;

public interface IChargePayment {

    public Amount forPayment(Payment payment, Currency currency, TransactionType transactionType);
}
