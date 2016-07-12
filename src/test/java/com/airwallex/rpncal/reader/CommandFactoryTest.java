package com.airwallex.rpncal.reader;

import com.airwallex.rpncal.command.impl.*;
import org.testng.annotations.Test;

import static com.airwallex.rpncal.reader.CommandFactory.commandFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;

@Test
public class CommandFactoryTest {
    public void should_map_command_to_string_representation() {
        assertThat(new PlusCommand().getOperator(), is("+"));
        assertThat(new MinusCommand().getOperator(), is("-"));
        assertThat(new MultiplyCommand().getOperator(), is("*"));
        assertThat(new DivideCommand().getOperator(), is("/"));
        assertThat(new UndoCommand().getOperator(), is("undo"));
        assertThat(new ClearCommand().getOperator(), is("clear"));
        assertThat(new SqrtCommand().getOperator(), is("sqrt"));
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