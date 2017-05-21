package com.airwallex.rpncal.executor;

import com.airwallex.rpncal.calculator.Calculator;
import com.airwallex.rpncal.command.Command;
import com.airwallex.rpncal.command.impl.UndoCommand;
import com.airwallex.rpncal.exception.InvalidCommandException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Execute commands
 */
public class CommandExecutor {
    private final Calculator calculator;
    private final Deque<Command> commandsHistory = new ArrayDeque<>();

    public CommandExecutor(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * execute a list of commands
     * @param commands
     * @throws IOException
     */
    public void execute(List<Command> commands) throws IOException, InvalidCommandException {
        for (Command command : commands) {
            execute(command);
        }
    }

    /**
     * Execute a command and save command to executed history
     * @param command
     * @return
     * @throws IOException
     */
    public void execute(Command command) throws IOException, InvalidCommandException {
        if (command instanceof UndoCommand) {
            if (commandsHistory.size() > 0) {
                Command lastCommand = commandsHistory.pop();
                lastCommand.undo(calculator);
            }

            return;
        }

        validateCommand(command);

        //only add command to undo stack if it executed successful
        if (command.execute(calculator)) {
            commandsHistory.push(command);
        }
    }

    private void validateCommand(Command command) throws IOException, InvalidCommandException {
        int requiredOprands = command.requiredOperands();

        if (requiredOprands > calculator.size()) {
            throw new InvalidCommandException(command, "insufficient parameters");
        }
    }

    public List<BigDecimal> currentStack() {
        return calculator.stack();
    }
}
