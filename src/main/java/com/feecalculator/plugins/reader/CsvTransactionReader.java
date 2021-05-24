package com.feecalculator.plugins.reader;

import java.util.List;

import com.feecalculator.domaincode.Transaction;

public class CsvTransactionReader implements ITransactionReader {

    public List<Transaction> readTransactions(List<String> lines) {
        return null;
    }

    //maybe inheritance: oberklasse eine methode, die die Funktionalität readString bereitstellt
    //add interface
    //read payments out of csv file and throw error if the csv is false formatted

}
