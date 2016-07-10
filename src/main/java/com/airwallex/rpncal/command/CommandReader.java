package com.airwallex.rpncal.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class CommandReader {
    private static final Logger logger = LoggerFactory.getLogger(CommandReader.class);

    private final BufferedReader reader;

    public CommandReader(Reader inputReader) {
        reader = new BufferedReader(inputReader);
    }

    /**
     * @return returns command for MOVE, LEFT, RIGHT, REPORT and PLACE in normal case.
     * returns NoopCommand for invalid command or exception
     * returns PowerOffCommand when no more data available in reader.
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
