package com.airwallex.rpncal.command;

import com.airwallex.rpncal.calculator.Calculator;

public interface Command {
    void execute(Calculator calculator);
    void undo(Calculator calculator);

    int getPos();
    Command setPos(int pos);
    int requiredOprands();
}
