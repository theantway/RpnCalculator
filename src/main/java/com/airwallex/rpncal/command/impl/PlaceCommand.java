package com.airwallex.rpncal.command.impl;

import com.airwallex.rpncal.calculator.Calculator;

import java.math.BigDecimal;

/**
 * The command to put a number to stack
 */
public class PlaceCommand extends AbstractCommand {
    private BigDecimal number;

    public PlaceCommand(BigDecimal number) {
        this.number = number;
    }

    @Override
    public int requiredOperands() {
        return 0;
    }

    @Override
    public void execute(Calculator calculator) {
        calculator.push(number);
    }

    @Override
    public void undo(Calculator calculator) {
        calculator.pop();
    }
}
