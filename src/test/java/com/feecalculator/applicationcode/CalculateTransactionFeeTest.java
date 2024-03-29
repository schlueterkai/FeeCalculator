package com.feecalculator.applicationcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Payment;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;
import com.feecalculator.exception.NotSupportedCurrencyException;
import com.feecalculator.exception.NotSupportedTransactionTypeException;

public class CalculateTransactionFeeTest {


    private UUID defaultUuid = UUID.fromString("123e4567-e89b-42d3-a456-556642440000");
    private CalculateTransactionFee calculateTransactionFee;

    @Before
    public void init() {
        this.calculateTransactionFee = new CalculateTransactionFee(initializeTransactionTypes(), initializeCurrencies());
    }

    @Test
    public void calculatePaymentTransactionFee() {
        List<Transaction> transactions = new ArrayList<>();

        Amount amount1 = new Amount(100, Currency.getInstance("EUR"));
        Transaction transaction1 = new Transaction(amount1, TransactionType.PAYPAL_DONATION);

        Amount amount2 = new Amount(200, Currency.getInstance("EUR"));
        Transaction transaction2 = new Transaction(amount2, TransactionType.PAYPAL_DONATION);

        transactions.add(transaction1);
        transactions.add(transaction2);
        Payment payment = new Payment(transactions);

        Amount expectedPaymentTransactionFee = new Amount(30.2, Currency.getInstance("EUR"));

        performPaymentTransactionFeeTestWith(payment, Currency.getInstance("EUR"), TransactionType.PAYPAL_MICRO_PAYMENT, expectedPaymentTransactionFee);
    }

    @Test
    public void calculatePaymentTransactionFeeWithDifferentCurrencies() {
        List<Transaction> transactions = new ArrayList<>();

        Amount amount1 = new Amount(100, Currency.getInstance("EUR"));
        Transaction transaction1 = new Transaction(amount1, TransactionType.PAYPAL_MICRO_PAYMENT);

        Amount amount2 = new Amount(200, Currency.getInstance("EUR"));
        Transaction transaction2 = new Transaction(amount2, TransactionType.PAYPAL_MICRO_PAYMENT);

        Amount amountWithDifferentCurrency =  new Amount(100, Currency.getInstance("USD"));
        Transaction transactionWithDifferentCurrency = new Transaction(amountWithDifferentCurrency, TransactionType.PAYPAL_MICRO_PAYMENT);

        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transactionWithDifferentCurrency);
        Payment payment = new Payment(transactions);

        Amount expectedPaymentTransactionFee = new Amount(30.2, Currency.getInstance("EUR"));

        performPaymentTransactionFeeTestWith(payment, Currency.getInstance("EUR"), TransactionType.PAYPAL_MICRO_PAYMENT, expectedPaymentTransactionFee);
    }

    @Test(expected = NotSupportedCurrencyException.class)
    public void testNotSupportedCurrencyForPayment(){
        List<Transaction> transactions = new ArrayList<>();

        Amount amount1 = new Amount(100, Currency.getInstance("EUR"));
        Transaction transaction1 = new Transaction(amount1, TransactionType.PAYPAL_MICRO_PAYMENT);

        Amount amount2 = new Amount(200, Currency.getInstance("EUR"));
        Transaction transaction2 = new Transaction(amount2, TransactionType.PAYPAL_MICRO_PAYMENT);

        transactions.add(transaction1);
        transactions.add(transaction2);
        Payment payment = new Payment(transactions);

        Currency notSupportedCurrency = Currency.getInstance("USD");

        calculateTransactionFee.forPayment(payment, notSupportedCurrency, TransactionType.PAYPAL_MICRO_PAYMENT);
    }

    @Test(expected = NotSupportedTransactionTypeException.class)
    public void testNotSupportedTransactionTypeForPayment(){
        List<Transaction> transactions = new ArrayList<>();

        Amount amount1 = new Amount(100, Currency.getInstance("EUR"));
        Transaction transaction1 = new Transaction(amount1, TransactionType.PAYPAL_DONATION);

        Amount amount2 = new Amount(200, Currency.getInstance("EUR"));
        Transaction transaction2 = new Transaction(amount2, TransactionType.PAYPAL_DONATION);

        transactions.add(transaction1);
        transactions.add(transaction2);
        Payment payment = new Payment(transactions);

        calculateTransactionFee.forPayment(payment, Currency.getInstance("EUR"), null);
    }

    @Test
    public void calculatePersonalTransactionFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction personalTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_PERSONAL);

        Amount expectedPersonalTransactionFee = new Amount(0, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(personalTransaction, expectedPersonalTransactionFee);
    }

    @Test
    public void calculateDonationTransactionFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction donationTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_DONATION);

        Amount expectedDonationTransactionFee = new Amount(1.85, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(donationTransaction, expectedDonationTransactionFee);

    }

    @Test
    public void calculateServiceTransactionFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction serviceTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_SERVICE);

        Amount expectedServiceTransactionFee = new Amount(2.84, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(serviceTransaction, expectedServiceTransactionFee);

    }

    @Test
    public void calculateLowVolumeSellerConditionTransactionFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction lowSellerConditionTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_SELLER_CONDITION_LOW_VOLUME);

        Amount expectedLowSellerConditionTransactionFee = new Amount(2.54, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(lowSellerConditionTransaction, expectedLowSellerConditionTransactionFee);
    }

    @Test
    public void calculateMediumVolumeSellerConditionTransactionFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction mediumSellerConditionTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_SELLER_CONDITION_MEDIUM_VOLUME);

        Amount expectedMediumSellerConditionTransactionFee = new Amount(2.34, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(mediumSellerConditionTransaction, expectedMediumSellerConditionTransactionFee);
    }

    @Test
    public void calculateHighVolumeSellerConditionTransactionFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction highSellerConditionTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_SELLER_CONDITION_HIGH_VOLUME);

        Amount expectedHighSellerConditionTransactionFee = new Amount(2.14, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(highSellerConditionTransaction, expectedHighSellerConditionTransactionFee);
    }

    @Test
    public void calculateVeryHighVolumeSellerConditionTransactionFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction veryHighSellerConditionTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_SELLER_CONDITION_VERY_HIGH_VOLUME);

        Amount expectedVeryHighSellerConditionTransactionFee = new Amount(1.84, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(veryHighSellerConditionTransaction, expectedVeryHighSellerConditionTransactionFee);
    }

    @Test
    public void calculateMicroPaymentTransactionFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction micropaymentTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_MICRO_PAYMENT);

        Amount expectedMicropaymentTransactionFee = new Amount(10.10, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(micropaymentTransaction, expectedMicropaymentTransactionFee);
    }

    @Test
    public void calculateWalleeTransactionFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction walleeTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.VISA_WALLEE);

        Amount expectedWalleeTransactionFee = new Amount(1.5, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(walleeTransaction, expectedWalleeTransactionFee);
    }

    @Test
    public void calculateConcardisTransactionFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction concardisTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.VISA_CONCARDIS);

        Amount expectedConcardisTransactionFee = new Amount(1.09, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(concardisTransaction, expectedConcardisTransactionFee);
    }

    @Test(expected = NotSupportedCurrencyException.class)
    public void testNotSupportedCurrencyForTransactionCalculation() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("USD"));
        Transaction notSupportedCurrencyTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_PERSONAL);

        calculateTransactionFee.forTransaction(notSupportedCurrencyTransaction);
    }

    @Test(expected = NotSupportedTransactionTypeException.class)
    public void testNotSupportedTransactionTypeForTransactionCalculation() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction invalidTransactionType = new Transaction(defaultUuid, transactionVolume, null);

        calculateTransactionFee.forTransaction(invalidTransactionType);
    }

    private void performPaymentTransactionFeeTestWith(Payment payment, Currency currency, TransactionType transactionType, Amount expectedPaymentFee) {
        Amount actualPaymentFee = calculateTransactionFee.forPayment(payment, currency, transactionType);
        //Double Inaccury is smaller than one cent
        assertEquals(expectedPaymentFee.getValue(), actualPaymentFee.getValue(), 1e-4);
        assertEquals(expectedPaymentFee.getCurrency(), actualPaymentFee.getCurrency());
    }

    private void performTransactionFeeTestWith(Transaction transaction, Amount expectedTransactionFee) {
        Amount actualTransactionFee = calculateTransactionFee.forTransaction(transaction);
        //Double Inaccury is smaller than one cent
        assertEquals(expectedTransactionFee.getValue(), actualTransactionFee.getValue(), 1e-4);
        assertEquals(expectedTransactionFee.getCurrency(), actualTransactionFee.getCurrency());
    }

    private List<Currency> initializeCurrencies() {
        ArrayList<Currency> supportedCurrencies = new ArrayList<>();
        supportedCurrencies.add(Currency.getInstance("EUR"));
        return supportedCurrencies;
    }

    private List<TransactionType> initializeTransactionTypes() {
        ArrayList<TransactionType> supportedTransactionTypes = new ArrayList<>();
        supportedTransactionTypes.add(TransactionType.PAYPAL_PERSONAL);
        supportedTransactionTypes.add(TransactionType.PAYPAL_DONATION);
        supportedTransactionTypes.add(TransactionType.PAYPAL_SERVICE);
        supportedTransactionTypes.add(TransactionType.PAYPAL_SELLER_CONDITION_LOW_VOLUME);
        supportedTransactionTypes.add(TransactionType.PAYPAL_SELLER_CONDITION_MEDIUM_VOLUME);
        supportedTransactionTypes.add(TransactionType.PAYPAL_SELLER_CONDITION_HIGH_VOLUME);
        supportedTransactionTypes.add(TransactionType.PAYPAL_SELLER_CONDITION_VERY_HIGH_VOLUME);
        supportedTransactionTypes.add(TransactionType.PAYPAL_MICRO_PAYMENT);
        supportedTransactionTypes.add(TransactionType.VISA_WALLEE);
        supportedTransactionTypes.add(TransactionType.VISA_CONCARDIS);
        return supportedTransactionTypes;
    }

}
