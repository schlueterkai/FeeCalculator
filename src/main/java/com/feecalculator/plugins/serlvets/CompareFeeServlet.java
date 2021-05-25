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
import com.feecalculator.exception.EmptyPaymentException;
import com.feecalculator.exception.InvalidTransactionException;
import com.feecalculator.plugins.reader.CsvTransactionReader;
import com.feecalculator.plugins.reader.ITransactionReader;
import com.feecalculator.utils.PropertiesUtils;

@WebServlet("/CompareFeeServlet")
@MultipartConfig
public class CompareFeeServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ITransactionReader transactionReader = new CsvTransactionReader();
        IFeeComparision feeComparision = initalizeFeeComparision();
        IChargePayment feeCalculator = initializeFeeCalculator();
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try {
            List<Transaction> transactions = transactionReader.readTransactions(request);
            Payment payment = new Payment(transactions);
            TransactionType bestTransactionType = feeComparision.findBestTransactionTypeForPayment(payment);
            Amount transactionFees = feeCalculator.forPayment(payment, Currency.getInstance("EUR"), bestTransactionType);
            payment.setTransactionFees(transactionFees);
            writer.print(generateOutput(payment, bestTransactionType));
        } catch (InvalidTransactionException e) {
            response.setContentType("text/html");
            writer = response.getWriter();
            writer.print(generateErrorCodeInvalid());
            e.printStackTrace();
        } catch (EmptyPaymentException e) {
            response.setContentType("text/html");
            writer = response.getWriter();
            writer.print(generateErrorCodeEmpty());
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

    private String generateOutput(Payment payment, TransactionType bestTransactionType) {
        Map<String, String> paymentModel = PaymentRenderModel.renderPayment(payment, bestTransactionType);
        return HtmlCodeSnippets.BASIC_STRUCTURE_WITH_NAVIGATION_BAR + String.format(HtmlCodeSnippets.COMPARE_STRUCTURE, paymentModel.get("bestTransactionType"), paymentModel.get("transactionFees")) +
                HtmlCodeSnippets.BASIC_STRUCTURE_END;
    }

    private String generateErrorCodeInvalid() {
        return HtmlCodeSnippets.BASIC_STRUCTURE_WITH_NAVIGATION_BAR + HtmlCodeSnippets.COMPARE_ERROR_INVALID + HtmlCodeSnippets.BASIC_STRUCTURE_END;

    }

    private String generateErrorCodeEmpty() {
        return HtmlCodeSnippets.BASIC_STRUCTURE_WITH_NAVIGATION_BAR + HtmlCodeSnippets.COMPARE_ERROR_EMPTY + HtmlCodeSnippets.BASIC_STRUCTURE_END;
    }
}
