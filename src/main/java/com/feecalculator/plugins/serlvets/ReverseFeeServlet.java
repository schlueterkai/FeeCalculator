package com.feecalculator.plugins.serlvets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Currency;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feecalculator.adapters.TransactionRenderModel;
import com.feecalculator.applicationcode.CalculateReverseFee;
import com.feecalculator.applicationcode.IChargeTransaction;
import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;
import com.feecalculator.utils.PropertiesUtils;

@WebServlet("/ReverseFeeServlet")
@MultipartConfig
public class ReverseFeeServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        IChargeTransaction calculateReverseFee = new CalculateReverseFee(PropertiesUtils.initializeTransactionTypes(), PropertiesUtils.initializeCurrencies());
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        //TODO: refactor
        TransactionType transactionType = getTransactionType(request.getParameter("transactionType"));
        Double transactionVolume = Double.parseDouble(request.getParameter("inputAmount"));
        Amount transactionAmount = new Amount(transactionVolume, Currency.getInstance("EUR"));
        Transaction transaction = new Transaction(transactionAmount, transactionType);

        //TODO: own method
        Amount transactionFee = calculateReverseFee.forTransaction(transaction);
        transaction.setTransactionFee(transactionFee);

        writer.print(generateOutput(transaction));
    }

    private String generateOutput(Transaction transaction) {
        Map<String, String> transactionRenderModel = TransactionRenderModel.renderTransaction(transaction);
        return HtmlCodeSnippets.BASIC_STRUCTURE_WITH_NAVIGATION_BAR +
                String.format(HtmlCodeSnippets.TRANSACTION_REVERSE_STRUCTURE, transactionRenderModel.get("transactionType"), transactionRenderModel.get("transactionVolume"),
                        transactionRenderModel.get("transactionFee"), transactionRenderModel.get("proportion")) + HtmlCodeSnippets.BASIC_STRUCTURE_END;
    }

    //TODO: think about DRY because of TransactionFeeServlet
    private TransactionType getTransactionType(String radiobuttonValue) {
        switch (radiobuttonValue) {
            case "paypalFriends":
                return TransactionType.PAYPAL_PERSONAL;
            case "paypalServices":
                return TransactionType.PAYPAL_SERVICE;
            case "paypalDonation":
                return TransactionType.PAYPAL_DONATION;
            case "paypalMicro":
                return TransactionType.PAYPAL_MICRO_PAYMENT;
            case "paypalLowSellerCondition":
                return TransactionType.PAYPAL_SELLER_CONDITION_LOW_VOLUME;
            case "paypalMediumSellerCondition":
                return TransactionType.PAYPAL_SELLER_CONDITION_MEDIUM_VOLUME;
            case "paypalHighSellerCondition":
                return TransactionType.PAYPAL_SELLER_CONDITION_HIGH_VOLUME;
            case "paypalVeryHighSellerCondition":
                return TransactionType.PAYPAL_SELLER_CONDITION_VERY_HIGH_VOLUME;
            case "visaConcardis":
                return TransactionType.VISA_CONCARDIS;
            case "visaWallee":
                return TransactionType.VISA_WALLEE;
            default:
                return null;
        }
    }
}
