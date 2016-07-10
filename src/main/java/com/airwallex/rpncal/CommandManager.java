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
 * Execute commands on calculator
 */
public class CommandManager {
    private final Deque<Command> commands = new ArrayDeque<>();
    private final CalculatorPrinter printer;

    public CommandManager(CalculatorPrinter printer) {
        this.printer = printer;
    }

    public void execute(Calculator calculator, List<Command> commands) throws IOException {
        for (Command command : commands) {
            if (!execute(calculator, command)) {
                break;
            }
        }

        printer.print(calculator);
    }

    public boolean execute(Calculator calculator, Command command) throws IOException {
        if (command instanceof UndoCommand) {
            if (commands.size() > 0) {
                Command undoCommand = commands.pop();
                undoCommand.undo(calculator);
            }

            return true;
        }

        int requiredOprands = command.requiredOperands();
        if (requiredOprands > calculator.size()) {
            printer.printInsufficientError(command);
            return false;
        }

        //only add command to undo stack if it executed successful
        if (command.execute(calculator)) {
            commands.push(command);
        }

        return true;
    }
}
