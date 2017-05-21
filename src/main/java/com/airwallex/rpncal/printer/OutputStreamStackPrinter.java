package com.airwallex.rpncal.printer;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import static com.airwallex.rpncal.Constant.PRINT_SCALE;

/**
 * Print stack to output stream writer and print error messages to console
 */
public class OutputStreamStackPrinter implements StackPrinter {
    private final static DecimalFormat format = new DecimalFormat("#0.##########");

    private PrintWriter writer;

    public OutputStreamStackPrinter(PrintWriter writer) {
        this.writer = writer;
    }

    @Override
    public void printStack(List<BigDecimal> calculatorStack) throws IOException {
        StringBuilder builder = new StringBuilder("stack: ");

        for (int i = calculatorStack.size() - 1; i > 0; i--) {
            builder.append(format(calculatorStack.get(i))).append(" ");
        }

        if (calculatorStack.size() > 0) {
            builder.append(format(calculatorStack.get(0)));
        }

        writer.println(builder);
        writer.flush();
    }

    private String format(BigDecimal number) {
        return format.format(number.setScale(PRINT_SCALE, RoundingMode.HALF_UP));
    }
}
