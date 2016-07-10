package com.airwallex.rpncal;

import com.airwallex.rpncal.calculator.Calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CalculatorHelper {
    public static List<BigDecimal> numberList(String... numbers) {
        List<BigDecimal> result = new ArrayList<>();

        for (String number : numbers) {
            result.add(new BigDecimal(number));
        }

        return result;
    }

    public static Calculator calculatorWithStack(String... numbers) {
        Calculator calculator = new Calculator();
        for (String number : numbers) {
            calculator.push(new BigDecimal(number));
        }

        return calculator;
    }
}
