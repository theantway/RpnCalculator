package com.airwallex.rpncal.command.impl;

import com.airwallex.rpncal.calculator.Calculator;

import java.math.BigDecimal;

public abstract class TwoOperandsCommand extends AbstractCommand{
    protected BigDecimal firstNumber;
    protected BigDecimal secondNumber;

    @Override
    public int requiredOperands() {
        return 2;
    }

    @Override
    public boolean execute(Calculator calculator) {
        secondNumber = calculator.pop();
        firstNumber = calculator.pop();

        BigDecimal result = calculate(firstNumber, secondNumber);
        calculator.push(result);

        return true;
    }

    protected abstract BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber);

    @Override
    public void undo(Calculator calculator) {
        calculator.pop();

        calculator.push(firstNumber);
        calculator.push(secondNumber);
    }
}
