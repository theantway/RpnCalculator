package com.airwallex.rpncal.reader;

import com.airwallex.rpncal.command.Command;
import com.airwallex.rpncal.command.impl.*;
import org.hamcrest.core.Is;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

@Test
public class CommandReaderTest {
    public void should_parse_commands() throws IOException {
        CommandReader reader = new CommandReader(new StringReader("123.4567891352"));

        List<Command> commands = reader.nextCommands();

        assertThat(commands.size(), Is.is(1));
        assertThat(commands.get(0).getPos(), Is.is(0));
    }

    public void should_parse_commands_case_insensitive() throws IOException {
        CommandReader reader = new CommandReader(new StringReader("CLEAR unDO"));

        List<Command> commands = reader.nextCommands();

        assertThat(commands.size(), Is.is(2));
        assertThat(commands.get(0), instanceOf(ClearCommand.class));
        assertThat(commands.get(0).getPos(), Is.is(0));
        assertThat(commands.get(1), instanceOf(UndoCommand.class));
        assertThat(commands.get(1).getPos(), Is.is(6));
    }

    public void should_ignore_whitespaces() throws IOException {
        CommandReader reader = new CommandReader(new StringReader("  CLEAR   unDO   "));

        List<Command> commands = reader.nextCommands();

        assertThat(commands.size(), Is.is(2));
        assertThat(commands.get(0), instanceOf(ClearCommand.class));
        assertThat(commands.get(1), instanceOf(UndoCommand.class));
    }

    public void should_parse_multiple_commands() throws IOException {
        CommandReader reader = new CommandReader(new StringReader("1.1 2.2 + 1 - undo clear * /"));

        List<Command> commands = reader.nextCommands();

        assertThat(commands.size(), Is.is(9));
        assertThat(commands.get(0), instanceOf(PlaceCommand.class));
        assertThat(commands.get(1), instanceOf(PlaceCommand.class));
        assertThat(commands.get(2), instanceOf(PlusCommand.class));
        assertThat(commands.get(3), instanceOf(PlaceCommand.class));
        assertThat(commands.get(4), instanceOf(MinusCommand.class));
        assertThat(commands.get(5), instanceOf(UndoCommand.class));
        assertThat(commands.get(6), instanceOf(ClearCommand.class));
        assertThat(commands.get(7), instanceOf(MultiplyCommand.class));
        assertThat(commands.get(8), instanceOf(DivideCommand.class));
    }
}