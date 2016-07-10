package com.airwallex.rpncal.printer;

import com.airwallex.rpncal.calculator.Calculator;
import com.airwallex.rpncal.command.Command;

import java.io.IOException;

public interface CalculatorPrinter {
    void print(Calculator calculator) throws IOException;
    void printInsufficientError(Command command) throws IOException;
}
