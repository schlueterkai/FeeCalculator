package com.feecalculator.abstractioncode;

public class CalculationUtils {


    /*
        Round a double to a to specified number of decimal places
     */
    public static double round(double number, int places){
        if (places < 0) {
            throw new IllegalArgumentException("Cannot round to a negative number of places");
        }
        long factor = (long) Math.pow(10, places);
        number = number * factor;
        long tmp = Math.round(number);
        return (double) tmp / factor;
    }
}
