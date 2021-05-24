package com.feecalculator.plugins.reader;

import java.util.List;

import com.feecalculator.domaincode.Transaction;

public interface ITransactionReader {

    public List<Transaction> readTransactions(List<String> lines);
}
