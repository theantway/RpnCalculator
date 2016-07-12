package com.airwallex.rpncal.command.impl;

import com.airwallex.rpncal.calculator.Calculator;

/**
 * undo last command
 */
public class UndoCommand extends AbstractCommand {
    @Override
    public int requiredOperands() {
        return 0;
    }

    @Override
    public String getOperator() {
        return "undo";
    }

    @Override
    public boolean execute(Calculator calculator) {
        return true;
    }

    @Override
    public void undo(Calculator calculator) {
    }
}
