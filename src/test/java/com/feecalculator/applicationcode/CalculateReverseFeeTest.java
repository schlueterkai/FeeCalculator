package com.feecalculator.applicationcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;
import com.feecalculator.exception.NotSupportedCurrencyException;
import com.feecalculator.exception.NotSupportedTransactionTypeException;

public class CalculateReverseFeeTest {

    private UUID defaultUuid = UUID.fromString("123e4567-e89b-42d3-a456-556642440000");
    private CalculateReverseFee calculateReverseFee;

    @Before
    public void init() {
        this.calculateReverseFee = new CalculateReverseFee(initializeTransactionTypes(), initializeCurrencies());
    }

    @Test
    public void calculatePersonalReverseFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction personalTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_PERSONAL);

        Amount expectedPersonalReverseFee = new Amount(100, Currency.getInstance("EUR"));

        performReverseFeeTestWith(personalTransaction, expectedPersonalReverseFee);
    }

    @Test
    public void calculateDonationReverseFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction donationTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_DONATION);

        Amount expectedDonationReverseFee = new Amount(101.88, Currency.getInstance("EUR"));

        performReverseFeeTestWith(donationTransaction, expectedDonationReverseFee);
    }

    @Test
    public void calculateServiceReverseFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction serviceTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_SERVICE);

        Amount expectedServiceReverseFee = new Amount(102.91, Currency.getInstance("EUR"));

        performReverseFeeTestWith(serviceTransaction, expectedServiceReverseFee);
    }

    @Test
    public void calculateLowVolumeSellerConditionReverseFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction lowSellerConditionTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_SELLER_CONDITION_LOW_VOLUME);

        Amount expectedLowSellerConditionReverseFee = new Amount(102.60, Currency.getInstance("EUR"));

        performReverseFeeTestWith(lowSellerConditionTransaction, expectedLowSellerConditionReverseFee);
    }

    @Test
    public void calculateMediumVolumeSellerConditionReverseFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction mediumSellerConditionTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_SELLER_CONDITION_MEDIUM_VOLUME);

        Amount expectedMediumSellerConditionReverseFee = new Amount(102.39, Currency.getInstance("EUR"));

        performReverseFeeTestWith(mediumSellerConditionTransaction, expectedMediumSellerConditionReverseFee);
    }

    @Test
    public void calculateHighVolumeSellerConditionReverseFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction highSellerConditionTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_SELLER_CONDITION_HIGH_VOLUME);

        Amount expectedHighSellerConditionReverseFee = new Amount(102.18, Currency.getInstance("EUR"));

        performReverseFeeTestWith(highSellerConditionTransaction, expectedHighSellerConditionReverseFee);
    }

    @Test
    public void calculateVeryHighVolumeSellerConditionReverseFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction veryHighSellerConditionTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_SELLER_CONDITION_VERY_HIGH_VOLUME);

        Amount expectedVeryHighSellerConditionReverseFee = new Amount(101.87, Currency.getInstance("EUR"));

        performReverseFeeTestWith(veryHighSellerConditionTransaction, expectedVeryHighSellerConditionReverseFee);

    }

    @Test
    public void calculateMicroPaymentReverseFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction micropaymentTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_MICRO_PAYMENT);

        Amount expectedMicroPaymentReverseFee = new Amount(111.22, Currency.getInstance("EUR"));

        performReverseFeeTestWith(micropaymentTransaction, expectedMicroPaymentReverseFee);
    }

    @Test
    public void calculateWalleeReverseFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction walleeTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.VISA_WALLEE);

        Amount expectedWalleeReverseFee = new Amount(101.52, Currency.getInstance("EUR"));

        performReverseFeeTestWith(walleeTransaction, expectedWalleeReverseFee);

    }

    @Test
    public void calculateConcardisReverseFee() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction concardisTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.VISA_CONCARDIS);

        Amount expectedConcardisReverseFee = new Amount(101.10, Currency.getInstance("EUR"));

        performReverseFeeTestWith(concardisTransaction, expectedConcardisReverseFee);
    }

    @Test(expected = NotSupportedCurrencyException.class)
    public void testNotSupportedCurrency() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("USD"));
        Transaction notSupportedCurrencyTransaction = new Transaction(defaultUuid, transactionVolume, TransactionType.PAYPAL_PERSONAL);

        calculateReverseFee.forTransaction(notSupportedCurrencyTransaction);
    }

    @Test(expected = NotSupportedTransactionTypeException.class)
    public void testNotSupportedTransactionType() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction invalidTransactionType = new Transaction(defaultUuid, transactionVolume, null);

        calculateReverseFee.forTransaction(invalidTransactionType);
    }

    private void performReverseFeeTestWith(Transaction transaction, Amount exceptedNewAmount) {
        Amount actualNewAmount = calculateReverseFee.forTransaction(transaction);
        //Inaccury because of double is smaller than one cent
        assertEquals(exceptedNewAmount.getValue(), actualNewAmount.getValue(), 1e-4);
        assertEquals(exceptedNewAmount.getCurrency(), actualNewAmount.getCurrency());
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
