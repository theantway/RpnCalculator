package com.airwallex.rpncal;

import com.airwallex.rpncal.calculator.Calculator;
import com.airwallex.rpncal.command.Command;
import com.airwallex.rpncal.exception.InvalidCommandException;
import com.airwallex.rpncal.executor.CommandExecutor;
import com.airwallex.rpncal.printer.OutputStreamStackPrinter;
import com.airwallex.rpncal.printer.StackPrinter;
import com.airwallex.rpncal.reader.CommandReader;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * The Main class. read from file or console for commands
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Charset utf8 = Charset.forName("utf-8");

        CommandExecutor commandExecutor = new CommandExecutor(new Calculator());
        CommandReader commandReader = new CommandReader(new InputStreamReader(fileOrConsole(args), utf8));
        PrintWriter outputWriter = new PrintWriter(new OutputStreamWriter(System.out, utf8));
        StackPrinter stackPrinter = new OutputStreamStackPrinter(outputWriter);

        while (true) {
            List<Command> commands = commandReader.nextCommands();

            if (commands == null) {
                break;
            }

            try {
                commandExecutor.execute(commands);
            } catch (InvalidCommandException e) {
                outputWriter.println(e.getMessage());
            }

            stackPrinter.printStack(commandExecutor.currentStack());
        }
    }

    private static InputStream fileOrConsole(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            return System.in;
        } else {
            return new FileInputStream(args[0]);
        }
    }
}
