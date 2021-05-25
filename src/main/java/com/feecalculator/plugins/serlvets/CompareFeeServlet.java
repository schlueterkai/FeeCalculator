package com.feecalculator.plugins.serlvets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feecalculator.adapters.PaymentRenderModel;
import com.feecalculator.applicationcode.CalculateTransactionFee;
import com.feecalculator.applicationcode.CompareFee;
import com.feecalculator.applicationcode.IChargePayment;
import com.feecalculator.applicationcode.IFeeComparision;
import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Payment;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;
import com.feecalculator.exception.InvalidTransactionException;
import com.feecalculator.plugins.reader.CsvTransactionReader;
import com.feecalculator.plugins.reader.ITransactionReader;
import com.feecalculator.utils.PropertiesUtils;

@WebServlet("/CompareFeeServlet")
@MultipartConfig
public class CompareFeeServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        ITransactionReader transactionReader = new CsvTransactionReader();
        IFeeComparision feeComparision = initalizeFeeComparision();
        IChargePayment feeCalculator = initializeFeeCalculator();
        try {
            List<Transaction> transactions = transactionReader.readTransactions(request);
            Payment payment = new Payment(transactions);
            TransactionType bestTransactionType = feeComparision.findBestTransactionTypeForPayment(payment);
            Amount transactionFees = feeCalculator.forPayment(payment, Currency.getInstance("EUR"), bestTransactionType);
            payment.setTransactionFees(transactionFees);

            Map<String, String> paymentModel = PaymentRenderModel.renderPayment(payment, bestTransactionType);

            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.print(generateOutput(paymentModel));
        } catch (InvalidTransactionException e) {
            //TODO: exception handling
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private IFeeComparision initalizeFeeComparision() {
        IChargePayment calculateTransactionFee = new CalculateTransactionFee(PropertiesUtils.initializeTransactionTypes(), PropertiesUtils.initializeCurrencies());
        return new CompareFee(calculateTransactionFee);
    }

    private IChargePayment initializeFeeCalculator() {
        return new CalculateTransactionFee(PropertiesUtils.initializeTransactionTypes(), PropertiesUtils.initializeCurrencies());
    }

    private String generateOutput(Map<String, String> paymentModel){
        return HtmlCodeSnippets.basicStructureWithNavigationBar + HtmlCodeSnippets.basicStructureEnd;
    }
}
