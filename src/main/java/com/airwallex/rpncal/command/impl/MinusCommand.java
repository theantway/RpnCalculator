package com.airwallex.rpncal.command.impl;

import java.math.BigDecimal;

/**
 * Command for subtract/minus
 */
public class MinusCommand extends TwoOperandsCommand{
    @Override
    protected BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber) {
        return firstNumber.subtract(secondNumber);
    }

    @Override
    public String getOperator() {
        return "-";
    }
}
