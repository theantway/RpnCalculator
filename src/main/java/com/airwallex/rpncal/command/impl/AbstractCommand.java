package com.airwallex.rpncal.command.impl;

import com.airwallex.rpncal.command.Command;

/**
 * Base class for all commands
 */
public abstract class AbstractCommand implements Command {
    private int pos;

    @Override
    public int getPositionOfInput() {
        return pos;
    }

    @Override
    public Command setPositionOfInput(int pos) {
        this.pos = pos;
        return this;
    }
}
