package com.airwallex.rpncal.printer;

import com.airwallex.rpncal.calculator.Calculator;
import com.airwallex.rpncal.command.CommandFactory;
import com.airwallex.rpncal.command.Command;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import static com.airwallex.rpncal.Constant.PRINT_SCALE;

public class OutputStreamCalculatorPrinter implements CalculatorPrinter {
    private final static DecimalFormat format = new DecimalFormat("#0.##########");

    private OutputStreamWriter writer;

    public OutputStreamCalculatorPrinter(OutputStreamWriter writer) {
        this.writer = writer;
    }

    @Override
    public void print(Calculator calculator) throws IOException {
        StringBuilder builder = new StringBuilder("stack: ");

        List<BigDecimal> numbers = calculator.stack();

        for (int i = numbers.size() - 1; i > 0; i--) {
            builder.append(format(numbers.get(i))).append(" ");
        }

        if (numbers.size() > 0) {
            builder.append(format(numbers.get(0))).append(System.lineSeparator());

            writer.append(builder);
            writer.flush();
        }
    }

    @Override
    public void printInsufficientError(Command command) throws IOException {
        writer.append("operator ")
                .append(CommandFactory.commandToString(command))
                .append(" (position: ")
                .append(String.valueOf(command.getPos()))
                .append("): insufficient parameters")
                .append(System.lineSeparator())
        ;
        writer.flush();
    }

    private String format(BigDecimal number) {
        return format.format(number.setScale(PRINT_SCALE, RoundingMode.HALF_UP));
    }
}
