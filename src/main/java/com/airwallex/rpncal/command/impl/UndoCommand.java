package com.airwallex.rpncal.command.impl;

import com.airwallex.rpncal.calculator.Calculator;

public class UndoCommand extends AbstractCommand {
    @Override
    public int requiredOprands() {
        return 0;
    }

    @Override
    public void execute(Calculator calculator) {
    }

    @Override
    public void undo(Calculator calculator) {
    }
}
