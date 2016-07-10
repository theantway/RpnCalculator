package com.airwallex.rpncal.command.impl;

import com.airwallex.rpncal.calculator.Calculator;

/**
 * NoopCommand used when invalid command found, instead of null, use this command avoids check null
 */
public class NoopCommand extends AbstractCommand{
    public NoopCommand() {
    }

    @Override
    public boolean execute(Calculator calculator) {
        return true;
    }

    @Override
    public void undo(Calculator calculator) {
    }

    @Override
    public int requiredOperands() {
        return 0;
    }
}
