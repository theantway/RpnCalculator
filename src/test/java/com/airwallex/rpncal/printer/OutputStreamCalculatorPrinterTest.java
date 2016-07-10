package com.airwallex.rpncal.printer;

import com.airwallex.rpncal.calculator.Calculator;
import org.hamcrest.core.Is;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static com.airwallex.rpncal.CalculatorHelper.calculatorWithStack;
import static org.hamcrest.MatcherAssert.assertThat;

@Test
public class OutputStreamCalculatorPrinterTest {
    public void should_print_stack() throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        CalculatorPrinter printer = new OutputStreamCalculatorPrinter(new OutputStreamWriter(stream));

        Calculator calculator = calculatorWithStack("1000000000.00", "1", "10.000", "1.1234567891123", "1.1234567891523");
        printer.print(calculator);

        assertThat(stream.toString(), Is.is("stack: 1000000000 1 10 1.1234567891 1.1234567892\n"));
    }
}