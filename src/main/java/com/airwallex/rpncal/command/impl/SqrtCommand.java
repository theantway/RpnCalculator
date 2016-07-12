package com.airwallex.rpncal.command.impl;

import com.airwallex.rpncal.Constant;
import com.airwallex.rpncal.calculator.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Command to calculate sqrt of number, this implementation using Babylonian method.
 */
public class SqrtCommand extends AbstractCommand{
    private static final BigDecimal TWO = new BigDecimal("2");
    private static final int MAX_ITERATION_TIMES = 100;

    private BigDecimal number;

    @Override
    public int requiredOperands() {
        return 1;
    }

    @Override
    public String getOperator() {
        return "sqrt";
    }

    @Override
    public boolean execute(Calculator calculator) {
        number = calculator.pop();

        BigDecimal result = sqrt(number, Constant.NUMBER_OF_DECIMAL_PLACES);
        calculator.push(result);

        return true;
    }

    @Override
    public void undo(Calculator calculator) {
        calculator.pop();

        calculator.push(number);
    }

    /**
     * Babylonian method to calculate sqrt,
     * @see <a href="https://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Babylonian_method">Trie</a>,
     *
     * To describe the algorithm in a short:
     * X0 ≈ sqrt(number)
     *
     * Xn+1 = (Xn + number/Xn)/2
     *
     *
     * @param number
     * @param scale
     * @return
     */
    private BigDecimal sqrt(BigDecimal number, int scale) {
        BigDecimal x0 = BigDecimal.ZERO;
        BigDecimal x1 = new BigDecimal(Math.sqrt(number.doubleValue()));

        int times = 0;
        while (!x0.equals(x1) && times ++ < MAX_ITERATION_TIMES) {
            x0 = x1;
            x1 = x0.add(number.divide(x0, scale, RoundingMode.HALF_UP)).divide(TWO, scale, RoundingMode.HALF_UP);
        }

        return x1;
    }
}
