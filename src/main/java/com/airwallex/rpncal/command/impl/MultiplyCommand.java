package com.airwallex.rpncal.command.impl;

import java.math.BigDecimal;

/**
 * Command for multiply
 */
public class MultiplyCommand extends TwoOperandsCommand{
    @Override
    protected BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber) {
        return firstNumber.multiply(secondNumber);
    }

    @Override
    public String getOperator() {
        return "*";
    }
}
