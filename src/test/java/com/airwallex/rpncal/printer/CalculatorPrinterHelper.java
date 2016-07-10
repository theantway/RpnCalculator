package com.airwallex.rpncal.printer;

import com.airwallex.rpncal.calculator.Calculator;
import com.airwallex.rpncal.command.Command;

import java.io.IOException;

public final class CalculatorPrinterHelper {
    public static CalculatorPrinter noopPrinter() {
        return new CalculatorPrinter(){
            @Override
            public void print(Calculator calculator) throws IOException {
            }

            @Override
            public void printInsufficientError(Command command) {
            }
        };
    }
}
