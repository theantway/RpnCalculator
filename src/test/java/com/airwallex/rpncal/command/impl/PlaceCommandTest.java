package com.airwallex.rpncal.command.impl;

import com.airwallex.rpncal.calculator.Calculator;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static com.airwallex.rpncal.calculator.CalculatorHelper.numberList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@Test
public class PlaceCommandTest {
    public void should_execute_command() {
        Calculator calculator = new Calculator();
        PlaceCommand command = new PlaceCommand(new BigDecimal("100"));

        command.execute(calculator);
        assertThat(calculator.stack(), equalTo(numberList("100")));

        command.undo(calculator);
        assertThat(calculator.stack(), equalTo(numberList()));
    }
}