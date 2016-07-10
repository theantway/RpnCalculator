package com.airwallex.rpncal.command.impl;

import com.airwallex.rpncal.calculator.Calculator;

public class NoopCommand extends AbstractCommand{
    public NoopCommand() {
    }

    @Override
    public void execute(Calculator calculator) {
    }

    @Override
    public void undo(Calculator calculator) {
    }

    @Override
    public int requiredOprands() {
        return 0;
    }
}
