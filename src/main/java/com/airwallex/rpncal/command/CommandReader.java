package com.airwallex.rpncal.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Read command from input and parse into list of Commands
 */
public class CommandReader {
    private static final Logger logger = LoggerFactory.getLogger(CommandReader.class);

    private final BufferedReader reader;

    public CommandReader(Reader inputReader) {
        reader = new BufferedReader(inputReader);
    }

    /**
     * read a line and parse into list of Commands
     * @return list of Commands
     * @throws IOException
     */
    public List<Command> nextCommands() throws IOException {
        String line = null;
        try {
            line = reader.readLine();
            if (line == null) {
                //end of file
                return null;
            }

            return parseLine(line.trim());
        } catch (IOException | IllegalArgumentException e) {
            logger.warn("exception which occurred during read command: " + line);

            throw new RuntimeException(e);
        }
    }

    /**
     * parse line, split by whitespace and return parsed Commands
     * @param line
     * @return
     */
    private List<Command> parseLine(String line) {
        List<Command> commands = new ArrayList<>();
        int pos;
        int startPos = 0;

        for (pos = 0; pos < line.length(); pos++) {
            if (line.charAt(pos) == ' ') {
                if (startPos != pos) {
                    commands.add(parseCommand(line, startPos, pos));
                }

                startPos = pos + 1;
            }
        }

        commands.add(parseCommand(line, startPos, pos));
        return commands;
    }

    private Command parseCommand(String line, int startPos, int endPos) {
        String commandStr = line.substring(startPos, endPos);
        Command command = CommandFactory.commandFor(commandStr);
        command.setPos(startPos);

        return command;
    }
}
