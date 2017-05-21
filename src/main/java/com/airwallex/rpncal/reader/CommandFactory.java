package com.airwallex.rpncal.reader;

import com.airwallex.rpncal.command.Command;
import com.airwallex.rpncal.command.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Create Command object from string
 */
public class CommandFactory {
    private static final Logger logger = LoggerFactory.getLogger(CommandFactory.class);

    /**
     * map for supported commands, key is command operator, value is concrete Command class
     */
    private static Map<String, Class<? extends Command>> supportedCommands = new HashMap<>();

    static {
        addSupportedCommand(PlusCommand.class);
        addSupportedCommand(MinusCommand.class);
        addSupportedCommand(MultiplyCommand.class);
        addSupportedCommand(DivideCommand.class);

        addSupportedCommand(SqrtCommand.class);
        addSupportedCommand(ClearCommand.class);
        addSupportedCommand(UndoCommand.class);
    }

    private static void addSupportedCommand(Class<? extends Command> clazz) {
        supportedCommands.put(getCommandOperator(clazz), clazz);
    }

    /**
     * Create command from string
     *
     * @param command
     * @return
     */
    public static Command commandFor(String command) {
        Class<? extends Command> clazz = supportedCommands.get(command.toLowerCase(Locale.ENGLISH));
        if (clazz != null) {
            return createOperatorCommand(clazz);
        } else {
            return createPlaceCommand(command);
        }
    }

    private static Command createPlaceCommand(String command) {
        try {
            return new PlaceCommand(new BigDecimal(command));
        } catch (NumberFormatException formatException) {
            logger.error("Unknown command: {}", command);
            return new NoopCommand();
        }
    }

    private static String getCommandOperator(Class<? extends Command> clazz) {
        return createOperatorCommand(clazz).getOperator();
    }

    private static Command createOperatorCommand(Class<? extends Command> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("Unable to create command object", e);
            throw new RuntimeException("Unable to create command object: " + clazz.getName(), e);
        }
    }
}
