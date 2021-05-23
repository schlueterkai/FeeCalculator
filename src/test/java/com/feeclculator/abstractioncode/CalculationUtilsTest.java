package com.feeclculator.abstractioncode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.feecalculator.abstractioncode.CalculationUtils;

public class CalculationUtilsTest {

    @Test
    public void roundOff() {
        double unroundedDouble = 1.452;
        double expectedRoundedToTwoPlaces = 1.45;
        double actualRoundedToTwoPlaces = CalculationUtils.round(unroundedDouble, 2);

        assertEquals(expectedRoundedToTwoPlaces, actualRoundedToTwoPlaces, 0.0001);
    }

    @Test
    public void roundUp() {
        double unroundedDouble = 1.456;
        double expectedRoundedToTwoPlaces = 1.46;
        double actualRoundedToTwoPlaces = CalculationUtils.round(unroundedDouble, 2);

        assertEquals(expectedRoundedToTwoPlaces, actualRoundedToTwoPlaces, 0.0001);
    }

    //TODO: add more test cases
}
