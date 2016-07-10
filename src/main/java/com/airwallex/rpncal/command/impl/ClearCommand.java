package com.airwallex.rpncal.command.impl;

import com.airwallex.rpncal.calculator.Calculator;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

public class ClearCommand extends AbstractCommand{
    private Deque<BigDecimal> clearedValues = new ArrayDeque<>();

    @Override
    public int requiredOprands() {
        return 0;
    }

    @Override
    public void execute(Calculator calculator) {
        while (calculator.hasMore()) {
            clearedValues.push(calculator.pop());
        }
    }

    @Override
    public void undo(Calculator calculator) {
        while (clearedValues.size() > 0) {
            calculator.push(clearedValues.pop());
        }
    }
}