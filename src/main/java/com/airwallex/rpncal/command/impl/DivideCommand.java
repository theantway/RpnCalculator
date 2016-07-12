package com.airwallex.rpncal.command.impl;

import com.airwallex.rpncal.calculator.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.airwallex.rpncal.Constant.NUMBER_OF_DECIMAL_PLACES;

/**
 * Command for divide two numbers
 */
public class DivideCommand extends TwoOperandsCommand{
    private static final Logger logger = LoggerFactory.getLogger(DivideCommand.class);

    @Override
    public boolean execute(Calculator calculator) {
        if (calculator.peek().signum() == 0) {
            logger.warn("Ignored Command to divide by zero");
            return false;
        }

        secondNumber = calculator.pop();
        firstNumber = calculator.pop();

        calculator.push(calculate(firstNumber, secondNumber));

        return true;
    }

    @Override
    protected BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber) {
        if (firstNumber.signum() == 0) {
            return BigDecimal.ZERO;
        } else{
            return firstNumber.divide(secondNumber, NUMBER_OF_DECIMAL_PLACES, RoundingMode.HALF_UP);
        }
    }

    @Override
    public String getOperator() {
        return "/";
    }
}
