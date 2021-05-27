package com.feecalculator.abstractioncode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculationUtilsTest {

    @Test(expected = IllegalArgumentException.class)
    public void roundWithInvalidArguments() {
        double unroundedDouble = 1.452;
        int invalidPlaces = -4;
        double actualRoundedToTwoPlaces = CalculationUtils.round(unroundedDouble, invalidPlaces);

    }

    @Test
    public void roundOff() {
        double unroundedDouble = 1.452;
        double expectedRoundedToTwoPlaces = 1.45;
        double actualRoundedToTwoPlaces = CalculationUtils.round(unroundedDouble, 2);

        assertEquals(expectedRoundedToTwoPlaces, actualRoundedToTwoPlaces, 1e-6);
    }

    @Test
    public void roundUp() {
        double unroundedDouble = 1.456;
        double expectedRoundedToTwoPlaces = 1.46;
        double actualRoundedToTwoPlaces = CalculationUtils.round(unroundedDouble, 2);

        assertEquals(expectedRoundedToTwoPlaces, actualRoundedToTwoPlaces, 1e-6);
    }

    //TODO: add more test cases
}
