package com.airwallex.rpncal.executor;

import com.airwallex.rpncal.calculator.Calculator;
import com.airwallex.rpncal.command.Command;
import com.airwallex.rpncal.command.impl.*;
import com.airwallex.rpncal.printer.CalculatorPrinter;
import com.airwallex.rpncal.printer.OutputStreamCalculatorPrinter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.List;

import static com.airwallex.rpncal.calculator.CalculatorHelper.numberList;
import static com.airwallex.rpncal.printer.CalculatorPrinterHelper.noopPrinter;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class CommandExecutorTest {
    public void should_execute_command() throws IOException {
        Calculator calculator = new Calculator();
        new CommandExecutor(calculator, noopPrinter()).execute(asList(
                new PlaceCommand(new BigDecimal("123.45")),
                new PlaceCommand(new BigDecimal("1.1")),
                new PlusCommand()
        ));

        assertThat(calculator.stack(), equalTo(numberList("124.55")));
    }

    public void should_execute_command_and_undo() throws IOException {
        Calculator calculator = new Calculator();
        new CommandExecutor(calculator, noopPrinter()).execute(asList(
                new PlaceCommand(new BigDecimal("123.45")),
                new PlaceCommand(new BigDecimal("1.1")),
                new PlusCommand(),
                new UndoCommand()
        ));

        assertThat(calculator.stack(), equalTo(numberList("1.1", "123.45")));
    }

    public void should_ignore_undo_command_when_no_history() throws IOException {
        Calculator calculator = new Calculator();
        boolean result = new CommandExecutor(calculator, noopPrinter()).execute(
                new UndoCommand()
        );

        assertTrue(result);
        assertThat(calculator.stack(), equalTo(emptyList()));
    }

    public void should_support_undo_only_when_execute_success() throws IOException {
        Calculator calculator = new Calculator();
        CommandExecutor commandExecutor = new CommandExecutor(calculator, noopPrinter());
        commandExecutor.execute(asList(
                new PlaceCommand(new BigDecimal("123.45")),
                new PlaceCommand(new BigDecimal("0")),
                new DivideCommand()
        ));

        assertThat(calculator.stack(), equalTo(numberList("0", "123.45")));
        commandExecutor.execute(new UndoCommand());
        assertThat(calculator.stack(), equalTo(numberList("123.45")));
    }

    public void should_ignore_commands_and_print_stack_if_no_enough_operands() throws IOException {
        Calculator calculator = new Calculator();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        CalculatorPrinter printer = new OutputStreamCalculatorPrinter(new OutputStreamWriter(stream));

        new CommandExecutor(calculator, printer).execute(asList(
                new PlaceCommand(new BigDecimal("123.45")),
                new PlusCommand(),
                new PlaceCommand(new BigDecimal("1.1")),
                new PlaceCommand(new BigDecimal("1.1"))
        ));

        assertThat(calculator.stack(), equalTo(numberList("123.45")));
        assertThat(stream.toString(), containsString("insufficient parameters"));
    }

    @DataProvider(name = "insufficientOperands")
    public Object[][] commandOperands() {
        return new Object[][]{
                new Object[]{new PlusCommand().setPositionOfInput(1), emptyList()},
                new Object[]{new PlusCommand().setPositionOfInput(2), asList("1")},
                new Object[]{new MinusCommand().setPositionOfInput(2), emptyList()},
                new Object[]{new MinusCommand().setPositionOfInput(2), asList("1")},
                new Object[]{new MultiplyCommand().setPositionOfInput(2), emptyList()},
                new Object[]{new MultiplyCommand().setPositionOfInput(2), asList("1")},
                new Object[]{new DivideCommand().setPositionOfInput(2), emptyList()},
                new Object[]{new DivideCommand().setPositionOfInput(2), asList("1")},
                new Object[]{new SqrtCommand().setPositionOfInput(2), emptyList()},
        };
    }

    @Test(dataProvider = "insufficientOperands")
    public void should_check_operand_count_before_execute_command(Command command, List<String> numbers) throws IOException {
        int pos = command.getPositionOfInput();
        String operator = command.getOperator();

        Calculator calculator = new Calculator();
        for (String number : numbers) {
            calculator.push(new BigDecimal(number));
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        CalculatorPrinter printer = new OutputStreamCalculatorPrinter(new OutputStreamWriter(stream));

        boolean result = new CommandExecutor(calculator, printer).execute(command);

        assertFalse(result);
        assertThat(stream.toString(), containsString("operator " + operator + " (position: " + pos + "): insufficient parameters"));
    }
}