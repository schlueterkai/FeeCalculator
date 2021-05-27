package com.feecalculator.plugins.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.feecalculator.applicationcode.ITransactionReader;
import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.exception.InvalidTransactionException;

public class CsvTransactionReader implements ITransactionReader {

    public List<Transaction> readTransactions(HttpServletRequest request) throws InvalidTransactionException {
        List<Transaction> transactions = new ArrayList<>();
        try {
            Part csvFilePart = request.getPart("csvFile");
            InputStream fileContent = csvFilePart.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(fileContent, StandardCharsets.UTF_8));

            String line;
            List<String> lines = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            //TODO: refactoring
            //TODO: use builder

            //do not read the header line
            for (int i = 1; i < lines.size(); i ++) {
                String transactionLine = lines.get(i);
                String parts[] = transactionLine.split(";");
                UUID uuid = UUID.fromString(parts[0]);
                Double volume = Double.parseDouble(parts[1]);
                Currency currency = Currency.getInstance(parts[2]);

                Amount amount = new Amount(volume, currency);
                Transaction transaction = new Transaction(uuid, amount);
                transactions.add(transaction);
            }
        } catch (IOException | ServletException e) {
            throw new InvalidTransactionException("An error occurred while processing the file. Please make sure that you have the file in the correct format.");
        }

        return transactions;
    }

}
