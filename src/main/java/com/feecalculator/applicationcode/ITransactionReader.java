package com.feecalculator.applicationcode;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.feecalculator.domaincode.Transaction;
import com.feecalculator.exception.InvalidTransactionException;

public interface ITransactionReader {

    public List<Transaction> readTransactions(HttpServletRequest request) throws InvalidTransactionException;

}
