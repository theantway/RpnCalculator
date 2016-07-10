package com.airwallex.rpncal.command.impl;

import com.airwallex.rpncal.calculator.Calculator;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Command for clear stack
 */
public class ClearCommand extends AbstractCommand{
    private Deque<BigDecimal> clearedValues = new ArrayDeque<>();

    @Override
    public int requiredOperands() {
        return 0;
    }

    @Override
    public boolean execute(Calculator calculator) {
        while (calculator.hasMore()) {
            clearedValues.push(calculator.pop());
        }

        return true;
    }

    @Override
    public void undo(Calculator calculator) {
        while (clearedValues.size() > 0) {
            calculator.push(clearedValues.pop());
        }
    }
}
