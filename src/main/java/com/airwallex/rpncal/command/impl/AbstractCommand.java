package com.airwallex.rpncal.command.impl;

import com.airwallex.rpncal.command.Command;

/**
 * Base class for all commands
 */
public abstract class AbstractCommand implements Command {
    private int pos;

    @Override
    public int getPos() {
        return pos;
    }

    @Override
    public Command setPos(int pos) {
        this.pos = pos;
        return this;
    }
}
