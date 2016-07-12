package com.airwallex.rpncal;

import com.airwallex.rpncal.calculator.Calculator;
import com.airwallex.rpncal.command.Command;
import com.airwallex.rpncal.command.impl.UndoCommand;
import com.airwallex.rpncal.printer.CalculatorPrinter;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Execute commandsHistory on calculator
 */
public class CommandManager {
    private final Calculator calculator;
    private final CalculatorPrinter printer;
    private final Deque<Command> commandsHistory = new ArrayDeque<>();

    public CommandManager(Calculator calculator, CalculatorPrinter printer) {
        this.calculator = calculator;
        this.printer = printer;
    }

    /**
     * execute a list of commands
     * @param commands
     * @throws IOException
     */
    public void executeCommands(List<Command> commands) throws IOException {
        for (Command command : commands) {
            if (!execute(command)) {
                break;
            }
        }

        printer.print(calculator);
    }

    /**
     * Execute a command and save command to executed history
     * @param command
     * @return
     * @throws IOException
     */
    public boolean execute(Command command) throws IOException {
        if (command instanceof UndoCommand) {
            if (commandsHistory.size() > 0) {
                Command undoCommand = commandsHistory.pop();
                undoCommand.undo(calculator);
            }

            return true;
        }

        if (!validate(command)) {
            return false;
        }

        //only add command to undo stack if it executed successful
        if (command.execute(calculator)) {
            commandsHistory.push(command);
        }

        return true;
    }

    private boolean validate(Command command) throws IOException {
        int requiredOprands = command.requiredOperands();

        if (requiredOprands > calculator.size()) {
            printer.printInsufficientError(command);
            return false;
        }

        return true;
    }
}
