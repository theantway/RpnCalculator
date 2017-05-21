package com.airwallex.rpncal.printer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * print calculator stack
 */
public interface StackPrinter {
    void printStack(List<BigDecimal> calculatorStack) throws IOException;
}
