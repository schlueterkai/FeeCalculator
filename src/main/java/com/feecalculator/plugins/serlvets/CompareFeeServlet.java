package com.feecalculator.plugins.serlvets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feecalculator.plugins.reader.ITransactionReader;

@WebServlet("/CompareFeeServlet")
public class CompareFeeServlet extends HttpServlet {

    private ITransactionReader transactionReader;

    public CompareFeeServlet (ITransactionReader transactionReader) {
        this.transactionReader = transactionReader;
    }

    //TODO: read data of http request
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
