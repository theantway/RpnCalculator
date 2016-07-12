package com.airwallex.rpncal.command;

import com.airwallex.rpncal.calculator.Calculator;

/**
 * Command to execute or undo.
 */
public interface Command {
    /**
     * the operator for command, e.g. + - * /
     * @return
     */
    String getOperator();

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
    int getPositionOfInput();

    /**
     * set position of this command in input
     * @param pos
     * @return
     */
    Command setPositionOfInput(int pos);

    /**
     * how manu operands does this command requires
     * @return
     */
    int requiredOperands();
}
