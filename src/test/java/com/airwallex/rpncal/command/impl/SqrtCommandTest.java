package com.airwallex.rpncal.command.impl;

import com.airwallex.rpncal.calculator.Calculator;
import org.testng.annotations.Test;

import static com.airwallex.rpncal.calculator.CalculatorHelper.calculatorWithStack;
import static com.airwallex.rpncal.calculator.CalculatorHelper.numberList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@Test
public class SqrtCommandTest {
    public void should_execute_command() {
        SqrtCommand command = new SqrtCommand();
        Calculator calculator = calculatorWithStack("9");

        command.execute(calculator);
        assertThat(calculator.stack(), equalTo(numberList("3.00000000000000000000")));

        command.undo(calculator);
        assertThat(calculator.stack(), equalTo(numberList("9")));
    }

    public void should_calculate_sqrt_of_infinite_numbers() {
        SqrtCommand command = new SqrtCommand();
        Calculator calculator = calculatorWithStack("2");

        command.execute(calculator);
        assertThat(calculator.stack(), equalTo(numberList("1.41421356237309504880")));
    }
}