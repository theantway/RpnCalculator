package com.airwallex.rpncal.command.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.airwallex.rpncal.Constant.NUMBER_OF_DECIMAL_PLACES;

/**
 * Command for divide two numbers
 */
public class DivideCommand extends TwoOperandsCommand{
    @Override
    protected BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber) {
        return firstNumber.divide(secondNumber, NUMBER_OF_DECIMAL_PLACES, RoundingMode.HALF_UP);
    }
}
