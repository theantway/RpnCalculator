package com.airwallex.rpncal.reader;

import com.airwallex.rpncal.command.impl.*;
import org.testng.annotations.Test;

import static com.airwallex.rpncal.reader.CommandFactory.commandFor;
import static com.airwallex.rpncal.reader.CommandFactory.commandToString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;

@Test
public class CommandFactoryTest {
    public void should_map_command_to_string_representation() {
        assertThat(commandToString(new PlusCommand()), is("+"));
        assertThat(commandToString(new MinusCommand()), is("-"));
        assertThat(commandToString(new MultiplyCommand()), is("*"));
        assertThat(commandToString(new DivideCommand()), is("/"));
        assertThat(commandToString(new UndoCommand()), is("undo"));
        assertThat(commandToString(new ClearCommand()), is("clear"));
        assertThat(commandToString(new SqrtCommand()), is("sqrt"));
    }

    public void should_map_string_to_command() {
        assertThat(commandFor("+"), instanceOf(PlusCommand.class));
        assertThat(commandFor("-"), instanceOf(MinusCommand.class));
        assertThat(commandFor("*"), instanceOf(MultiplyCommand.class));
        assertThat(commandFor("/"), instanceOf(DivideCommand.class));
        assertThat(commandFor("undo"), instanceOf(UndoCommand.class));
        assertThat(commandFor("clear"), instanceOf(ClearCommand.class));
        assertThat(commandFor("sqrt"), instanceOf(SqrtCommand.class));
        assertThat(commandFor("invalid"), instanceOf(NoopCommand.class));
        assertThat(commandFor("123.456"), instanceOf(PlaceCommand.class));
    }
}