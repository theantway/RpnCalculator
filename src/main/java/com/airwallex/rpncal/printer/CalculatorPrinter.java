package com.airwallex.rpncal.printer;

import com.airwallex.rpncal.calculator.Calculator;
import com.airwallex.rpncal.command.Command;

import java.io.IOException;

/**
 * print stack of calculator and error messages for a command
 */
public interface CalculatorPrinter {
    void printCalculatorStack(Calculator calculator) throws IOException;
    void printCommandError(Command command, String errorMessage) throws IOException;
}
