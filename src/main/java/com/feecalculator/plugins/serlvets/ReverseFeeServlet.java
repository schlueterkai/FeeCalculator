package com.feecalculator.plugins.serlvets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Currency;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
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
public class ReverseFeeServlet extends AbstractChargeTransactionServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        IChargeTransaction calculateReverseFee = new CalculateReverseFee(PropertiesUtils.initializeTransactionTypes(), PropertiesUtils.initializeCurrencies());
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        Transaction transaction = createTransactionFromRequest(request);

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

}
