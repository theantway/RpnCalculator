package com.airwallex.rpncal.command.impl;

import java.math.BigDecimal;

public class MinusCommand extends TwoOperandsCommand{
    @Override
    protected BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber) {
        return firstNumber.subtract(secondNumber);
    }
}
