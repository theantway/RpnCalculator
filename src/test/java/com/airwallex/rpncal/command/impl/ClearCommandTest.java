package com.airwallex.rpncal.command.impl;

import com.airwallex.rpncal.calculator.Calculator;
import org.testng.annotations.Test;

import static com.airwallex.rpncal.calculator.CalculatorHelper.calculatorWithStack;
import static com.airwallex.rpncal.calculator.CalculatorHelper.numberList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@Test
public class ClearCommandTest {
    public void should_execute_command() {
        ClearCommand command = new ClearCommand();
        Calculator calculator = calculatorWithStack("1", "10");

        command.execute(calculator);
        assertThat(calculator.stack(), equalTo(numberList()));

        command.undo(calculator);
        assertThat(calculator.stack(), equalTo(numberList("10", "1")));
    }
}