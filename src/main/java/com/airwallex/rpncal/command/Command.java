package com.airwallex.rpncal.command;

import com.airwallex.rpncal.calculator.Calculator;

/**
 * Command to execute or undo.
 */
public interface Command {
    /**
     * execute this command on given calculator
     * @param calculator
     */
    boolean execute(Calculator calculator);

    /**
     * undo last command on this calculator
     * @param calculator
     */
    void undo(Calculator calculator);

    /**
     * get position of this command in input
     * @return
     */
    int getPos();

    /**
     * set position of this command in input
     * @param pos
     * @return
     */
    Command setPos(int pos);

    /**
     * how manu operands does this command requires
     * @return
     */
    int requiredOperands();
}
