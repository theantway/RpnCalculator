package com.airwallex.rpncal;

import com.airwallex.rpncal.calculator.Calculator;
import com.airwallex.rpncal.command.Command;
import com.airwallex.rpncal.command.CommandManager;
import com.airwallex.rpncal.command.CommandReader;
import com.airwallex.rpncal.printer.CalculatorPrinter;
import com.airwallex.rpncal.printer.OutputStreamCalculatorPrinter;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * The Main class. read from file or console for commands
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Charset utf8 = Charset.forName("utf-8");

        CommandReader commandReader = new CommandReader(new InputStreamReader(fileOrConsole(args), utf8));
        CalculatorPrinter printer = new OutputStreamCalculatorPrinter(new OutputStreamWriter(System.out, utf8));
        CommandManager commandManager = new CommandManager(printer);
        Calculator calculator = new Calculator();

        while (true) {
            List<Command> commands = commandReader.nextCommands();

            if (commands == null) {
                break;
            }

            commandManager.execute(calculator, commands);
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