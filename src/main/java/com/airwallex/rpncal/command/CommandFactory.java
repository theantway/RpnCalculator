package com.airwallex.rpncal.command;

import com.airwallex.rpncal.command.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommandFactory {
    private static final Logger logger = LoggerFactory.getLogger(CommandFactory.class);
    private static final String INVALID_COMMAND = "INVALID-COMMAND";

    private static Map<String, Class<? extends Command>> commandMap = new HashMap<>();
    static {
        commandMap.put("+", PlusCommand.class);
        commandMap.put("-", MinusCommand.class);
        commandMap.put("*", MultiplyCommand.class);
        commandMap.put("/", DivideCommand.class);

        commandMap.put("sqrt", SqrtCommand.class);
        commandMap.put("clear", ClearCommand.class);
        commandMap.put("undo", UndoCommand.class);
    }

    public static String commandToString(Command command) {
        for (Map.Entry<String, Class<? extends Command>> entry : commandMap.entrySet()) {
            if (entry.getValue().isInstance(command)) {
                return entry.getKey();
            }
        }

        return INVALID_COMMAND;
    }

    public static Command commandFor(String str) {
        Class<? extends Command> clazz = commandMap.get(str.toLowerCase(Locale.ENGLISH));
        try {
            if (clazz != null) {
                return clazz.newInstance();
            } else {
                try {
                    return new PlaceCommand(new BigDecimal(str));
                } catch (NumberFormatException formatException) {
                    logger.error("Unknown command: {}", str);
                    return new NoopCommand();
                }
            }
        } catch (InstantiationException|IllegalAccessException e) {
            logger.error("Unable to create command object", e);
            throw new RuntimeException("Unable to create command object: " + str, e);
        }
    }
}
