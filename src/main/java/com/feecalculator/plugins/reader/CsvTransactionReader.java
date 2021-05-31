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
import com.feecalculator.domaincode.CreateTransaction;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.exception.InvalidTransactionException;

public class CsvTransactionReader implements ITransactionReader {

    public List<Transaction> readTransactions(HttpServletRequest request) throws InvalidTransactionException {
        List<Transaction> transactions = new ArrayList<>();
        try {
            Part csvFilePart = request.getPart("csvFile");
            InputStream fileContent = csvFilePart.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(fileContent, StandardCharsets.UTF_8));

            List<String> lines = readLinesFromCsv(br);

            transactions = createTransactionsFromListOfStrings(lines);

        } catch (IOException | ServletException e) {
            throw new InvalidTransactionException("An error occurred while processing the file. Please make sure that you have the file in the correct format.");
        }

        return transactions;
    }

    private List<String> readLinesFromCsv(BufferedReader bufferedReader) throws IOException {
        String line;
        List<String> lines = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        return  lines;
    }

    private List<Transaction> createTransactionsFromListOfStrings(List<String> lines) {
        List<Transaction> transactions = new ArrayList<>();
        //do not read the header line
        for (int i = 1; i < lines.size(); i ++) {
            String line = lines.get(i);
            transactions.add(createTransactionFromLine(line));
        }
        return transactions;
    }

    private Transaction createTransactionFromLine(String line) {
        String parts[] = line.split(";");
        String uuidString = parts[0];
        Double volume = Double.parseDouble(parts[1]);
        Currency currency = Currency.getInstance(parts[2]);
        return CreateTransaction.completed(volume, currency).setId(uuidString).build();
    }
}
