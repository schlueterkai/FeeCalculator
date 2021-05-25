package com.feecalculator.applicationcode;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import org.junit.Test;

import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Payment;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;
import com.feecalculator.exception.EmptyPaymentException;

public class CompareFeeTest {

    @Test
    public void findBestConditonForPaymentWithLowVolume() throws EmptyPaymentException {
        List<Transaction> transactions = new ArrayList<>();
        Currency euro = Currency.getInstance("EUR");

        Amount amount1 = new Amount(100, euro);
        Transaction transaction1 = new Transaction(amount1, TransactionType.PAYPAL_DONATION);

        Amount amount2 = new Amount(100, euro);
        Transaction transaction2 = new Transaction(amount2, TransactionType.PAYPAL_DONATION);

        transactions.add(transaction1);
        transactions.add(transaction2);
        Payment payment = new Payment(transactions);

        CalculateTransactionFee calculateTransactionFee = mock(CalculateTransactionFee.class);
        when(calculateTransactionFee.forPayment(payment, euro, TransactionType.PAYPAL_SERVICE)).thenReturn(new Amount(5.68, euro));
        when(calculateTransactionFee.forPayment(payment, euro, TransactionType.PAYPAL_MICRO_PAYMENT)).thenReturn(new Amount(20.20, euro));
        when(calculateTransactionFee.forPayment(payment, euro, TransactionType.VISA_WALLEE)).thenReturn(new Amount(2.8, euro));
        when(calculateTransactionFee.forPayment(payment, euro, TransactionType.VISA_CONCARDIS)).thenReturn(new Amount(1.98, euro));

        CompareFee compareFee = new CompareFee(calculateTransactionFee);
        TransactionType acutalTransactionType = compareFee.findBestTransactionTypeForPayment(payment);
        assertEquals(TransactionType.VISA_CONCARDIS, acutalTransactionType);
    }

    @Test
    public void findBestConditionForPaymentForVeryHighVolume() throws EmptyPaymentException {
        List<Transaction> transactions = new ArrayList<>();
        Currency euro = Currency.getInstance("EUR");

        Amount amount1 = new Amount(100000, euro);
        Transaction transaction1 = new Transaction(amount1, TransactionType.PAYPAL_DONATION);

        Amount amount2 = new Amount(100000, euro);
        Transaction transaction2 = new Transaction(amount2, TransactionType.PAYPAL_DONATION);

        transactions.add(transaction1);
        transactions.add(transaction2);
        Payment payment = new Payment(transactions);

        CalculateTransactionFee calculateTransactionFee = mock(CalculateTransactionFee.class);
        when(calculateTransactionFee.forPayment(payment, euro, TransactionType.PAYPAL_SERVICE)).thenReturn(new Amount(4980.7, euro));
        when(calculateTransactionFee.forPayment(payment, euro, TransactionType.PAYPAL_MICRO_PAYMENT)).thenReturn(new Amount(20000.2, euro));
        when(calculateTransactionFee.forPayment(payment, euro, TransactionType.VISA_WALLEE)).thenReturn(new Amount(2800, euro));
        when(calculateTransactionFee.forPayment(payment, euro, TransactionType.VISA_CONCARDIS)).thenReturn(new Amount(1980, euro));
        when(calculateTransactionFee.forPayment(payment, euro, TransactionType.PAYPAL_SELLER_CONDITION_LOW_VOLUME)).thenReturn(new Amount(4380.7, euro));
        when(calculateTransactionFee.forPayment(payment, euro, TransactionType.PAYPAL_SELLER_CONDITION_MEDIUM_VOLUME)).thenReturn(new Amount(3980.7, euro));
        when(calculateTransactionFee.forPayment(payment, euro, TransactionType.PAYPAL_SELLER_CONDITION_HIGH_VOLUME)).thenReturn(new Amount(3580.7, euro));
        when(calculateTransactionFee.forPayment(payment, euro, TransactionType.PAYPAL_SELLER_CONDITION_VERY_HIGH_VOLUME)).thenReturn(new Amount(2980.7, euro));

        CompareFee compareFee = new CompareFee(calculateTransactionFee);
        TransactionType acutalTransactionType = compareFee.findBestTransactionTypeForPayment(payment);
        assertEquals(TransactionType.VISA_CONCARDIS, acutalTransactionType);
    }

    @Test(expected = EmptyPaymentException.class)
    public void testPaymentWithoutTransactions() throws EmptyPaymentException {
        List<Transaction> emptyTransactionList = new ArrayList<>();
        Payment paymentWithoutTransactions = new Payment(emptyTransactionList);

        CalculateTransactionFee calculateTransactionFee = mock(CalculateTransactionFee.class);
        CompareFee compareFee = new CompareFee(calculateTransactionFee);

        compareFee.findBestTransactionTypeForPayment(paymentWithoutTransactions);
    }
}
