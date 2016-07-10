package com.airwallex.rpncal.command.impl;

import com.airwallex.rpncal.calculator.Calculator;
import org.testng.annotations.Test;

import static com.airwallex.rpncal.CalculatorHelper.calculatorWithStack;
import static com.airwallex.rpncal.CalculatorHelper.numberList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@Test
public class DivideCommandTest {
    public void should_execute_command() {
        DivideCommand command = new DivideCommand();
        Calculator calculator = calculatorWithStack("10", "2");

        command.execute(calculator);
        assertThat(calculator.stack(), equalTo(numberList("5.00000000000000000000")));

        command.undo(calculator);
        assertThat(calculator.stack(), equalTo(numberList("2", "10")));
    }

    public void should_keep_number_of_decimal_places_to_20() {
        DivideCommand command = new DivideCommand();
        Calculator calculator = calculatorWithStack("10", "3");

        command.execute(calculator);
        assertThat(calculator.stack(), equalTo(numberList("3.33333333333333333333")));

        command.undo(calculator);
        assertThat(calculator.stack(), equalTo(numberList("3", "10")));
    }

    public void should_use_plain_numbers() {
        DivideCommand command = new DivideCommand();
        Calculator calculator = calculatorWithStack("1000", "10");

        command.execute(calculator);
        assertThat(calculator.stack(), equalTo(numberList("100.00000000000000000000")));
    }
}