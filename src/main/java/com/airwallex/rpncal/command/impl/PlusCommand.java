package com.airwallex.rpncal.command.impl;

import java.math.BigDecimal;

/**
 * Command for add/plus
 */
public class PlusCommand extends TwoOperandsCommand{
    @Override
    protected BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber) {
        return firstNumber.add(secondNumber);
    }
}
