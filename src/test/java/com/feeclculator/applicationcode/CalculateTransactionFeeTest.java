package com.feeclculator.applicationcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.feecalculator.applicationcode.CalculateTransactionFee;
import com.feecalculator.domaincode.Amount;
import com.feecalculator.domaincode.Transaction;
import com.feecalculator.domaincode.TransactionType;
import com.feecalculator.exception.NotSupportedCurrencyException;
import com.feecalculator.exception.NotSupportedTransactionTypeException;

public class CalculateTransactionFeeTest {

    private CalculateTransactionFee calculateTransactionFee;
    //TODO: check if UUID should be mocked

    @Before
    public void init() {
        this.calculateTransactionFee = new CalculateTransactionFee(initializeTransactionTypes(), initializeCurrencies());
    }

    @Test
    public void calculatePersonalTransactionFees() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction personalTransaction = new Transaction(UUID.randomUUID(), transactionVolume, TransactionType.PAYPAL_PERSONAL);

        Amount expectedPersonalTransactionFee = new Amount(0, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(personalTransaction, expectedPersonalTransactionFee);
    }

    @Test
    public void calculateDonationTransactionFees() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction donationTransaction = new Transaction(UUID.randomUUID(), transactionVolume, TransactionType.PAYPAL_DONATION);

        Amount expectedDonationTransactionFee = new Amount(1.85, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(donationTransaction, expectedDonationTransactionFee);

    }

    @Test
    public void calculateServiceTransactionFees() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction serviceTransaction = new Transaction(UUID.randomUUID(), transactionVolume, TransactionType.PAYPAL_SERVICE);

        Amount expectedServiceTransactionFee = new Amount(2.84, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(serviceTransaction, expectedServiceTransactionFee);

    }

    @Test
    public void calculateLowVolumeSellerConditionTransactionFees() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction lowSellerConditionTransaction = new Transaction(UUID.randomUUID(), transactionVolume, TransactionType.PAYPAL_SELLER_CONDITION_LOW_VOLUME);

        Amount expectedLowSellerConditionTransactionFee = new Amount(2.54, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(lowSellerConditionTransaction, expectedLowSellerConditionTransactionFee);
    }

    @Test
    public void calculateMediumVolumeSellerConditionTransactionFees() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction mediumSellerConditionTransaction = new Transaction(UUID.randomUUID(), transactionVolume, TransactionType.PAYPAL_SELLER_CONDITION_MEDIUM_VOLUME);

        Amount expectedMediumSellerConditionTransactionFee = new Amount(2.34, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(mediumSellerConditionTransaction, expectedMediumSellerConditionTransactionFee);
    }

    @Test
    public void calculateHighVolumeSellerConditionTransactionFees() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction highSellerConditionTransaction = new Transaction(UUID.randomUUID(), transactionVolume, TransactionType.PAYPAL_SELLER_CONDITION_HIGH_VOLUME);

        Amount expectedHighSellerConditionTransactionFee = new Amount(2.14, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(highSellerConditionTransaction, expectedHighSellerConditionTransactionFee);
    }

    @Test
    public void calculateVeryHighVolumeSellerConditionTransactionFees() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction veryHighSellerConditionTransaction = new Transaction(UUID.randomUUID(), transactionVolume, TransactionType.PAYPAL_SELLER_CONDITION_VERY_HIGH_VOLUME);

        Amount expectedVeryHighSellerConditionTransactionFee = new Amount(1.84, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(veryHighSellerConditionTransaction, expectedVeryHighSellerConditionTransactionFee);
    }

    @Test
    public void calculateMicroPaymentTransactionFees() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction micropaymentTransaction = new Transaction(UUID.randomUUID(), transactionVolume, TransactionType.PAYPAL_MICRO_PAYMENT);

        Amount expectedMicropaymentTransactionFee = new Amount(10.10, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(micropaymentTransaction, expectedMicropaymentTransactionFee);
    }

    @Test
    public void calculateWalleeTransactionFees() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction walleeTransaction = new Transaction(UUID.randomUUID(), transactionVolume, TransactionType.VISA_WALLEE);

        Amount expectedWalleeTransactionFee = new Amount(1.5, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(walleeTransaction, expectedWalleeTransactionFee);
    }

    @Test
    public void calculateConcardisTransactionFees() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction concardisTransaction = new Transaction(UUID.randomUUID(), transactionVolume, TransactionType.VISA_CONCARDIS);

        Amount expectedConcardisTransactionFee = new Amount(1.09, Currency.getInstance("EUR"));

        performTransactionFeeTestWith(concardisTransaction, expectedConcardisTransactionFee);
    }

    @Test(expected = NotSupportedCurrencyException.class)
    public void testNotSupportedCurrency() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("USD"));
        Transaction notSupportedCurrencyTransaction = new Transaction(UUID.randomUUID(), transactionVolume, TransactionType.PAYPAL_PERSONAL);

        calculateTransactionFee.forTransaction(notSupportedCurrencyTransaction);
    }

    @Test(expected = NotSupportedTransactionTypeException.class)
    public void testNotSupportedTransactionType() {
        Amount transactionVolume = new Amount(100, Currency.getInstance("EUR"));
        Transaction invalidTransactionType = new Transaction(UUID.randomUUID(), transactionVolume, null);

        calculateTransactionFee.forTransaction(invalidTransactionType);
    }

    private void performTransactionFeeTestWith(Transaction transaction, Amount expectedTransactionFee) {
        Amount actualTransactionFee = calculateTransactionFee.forTransaction(transaction);
        //Double Inaccury is smaller than one cent
        assertEquals(expectedTransactionFee.getValue(), actualTransactionFee.getValue(), 0.001);
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
