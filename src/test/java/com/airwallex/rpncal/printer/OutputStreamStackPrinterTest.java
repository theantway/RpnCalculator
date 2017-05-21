package com.airwallex.rpncal.printer;

import com.airwallex.rpncal.calculator.Calculator;
import org.hamcrest.core.Is;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import static com.airwallex.rpncal.calculator.CalculatorHelper.calculatorWithStack;
import static org.hamcrest.MatcherAssert.assertThat;

@Test
public class OutputStreamStackPrinterTest {
    public void should_print_stack() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        StackPrinter printer = new OutputStreamStackPrinter(new PrintWriter(outputStream));

        Calculator calculator = calculatorWithStack("1000000000.00", "1", "10.000", "1.1234567891123", "1.1234567891523");
        printer.printStack(calculator.stack());

        assertThat(outputStream.toString(), Is.is("stack: 1000000000 1 10 1.1234567891 1.1234567892\n"));
    }
}