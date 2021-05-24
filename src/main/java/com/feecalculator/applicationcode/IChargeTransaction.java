package com.feecalculator.applicationcode;

import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Transaction;

public interface IChargeTransaction {

    public Amount forTransaction(Transaction transaction);
}
