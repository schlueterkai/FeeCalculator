package com.feecalculator.plugins.serlvets;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feecalculator.applicationcode.IFeeComparision;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.exception.InvalidTransactionException;
import com.feecalculator.plugins.reader.CsvTransactionReader;
import com.feecalculator.plugins.reader.ITransactionReader;

@WebServlet("/CompareFeeServlet")
public class CompareFeeServlet extends HttpServlet {

    //TODO: remove transactionReader etc. as variable -> does not make sense
    private ITransactionReader transactionReader;
    private IFeeComparision feeComparision;

    public CompareFeeServlet (ITransactionReader transactionReader, IFeeComparision feeComparision) {
        this.transactionReader = transactionReader;
        this.feeComparision = feeComparision;
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        transactionReader = new CsvTransactionReader();
        try {
            List<Transaction> transactions = transactionReader.readTransactions(request);


        } catch (InvalidTransactionException e) {
            //TODO: exception handling
            e.printStackTrace();
        }
    }
}
